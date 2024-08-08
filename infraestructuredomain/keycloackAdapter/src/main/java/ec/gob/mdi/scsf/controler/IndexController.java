package ec.gob.mdi.scsf.controler;

import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import ec.gob.mdi.scsf.exception.BussinesRuleException;
import ec.gob.mdi.scsf.service.JwtService;
import ec.gob.mdi.scsf.service.KeycloakRestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private KeycloakRestService restService;

    @Autowired
    private JwtService jwtService;

     @GetMapping("/roles")
    public ResponseEntity<?> getRoles(@RequestHeader("Authorization") String authHeader) throws BussinesRuleException {
            try {
        String token = authHeader.replace("Bearer", "").trim();
        
        // Verifica el token
        jwtService.verifyToken(token);
        
        DecodedJWT jwt = JWT.decode(token);
        
        // Log del contenido del token para depuración
        logger.info("Token claims: {}", jwt.getClaims());
        
        // Intenta obtener los roles de diferentes maneras
        List<String> roles = null;
        
        // Método 1: Usando realm_access.roles
        Object realmAccess = jwt.getClaim("realm_access").asMap();
        if (realmAccess instanceof Map) {
            roles = (List<String>) ((Map<String, Object>) realmAccess).get("roles");
        }
        
        // Método 2: Usando resource_access.<client_id>.roles
        if (roles == null || roles.isEmpty()) {
            Object resourceAccess = jwt.getClaim("resource_access").asMap();
            if (resourceAccess instanceof Map) {
                Map<String, Object> clientAccess = (Map<String, Object>) ((Map<String, Object>) resourceAccess).get("front-angular-access-app");
                if (clientAccess != null) {
                    roles = (List<String>) clientAccess.get("roles");
                }
            }
        }
        
        // Método 3: Usando un claim directo de roles (menos común)
        if (roles == null || roles.isEmpty()) {
            roles = jwt.getClaim("roles").asList(String.class);
        }
        
        if (roles == null || roles.isEmpty()) {
            logger.error("No roles found in the token. Token structure: {}", jwt.getClaims());
            throw new Exception("No roles found in the token");
        }
        
        // Verifica la fecha de expiración del token
        Date expiryDate = jwt.getExpiresAt();
        if (expiryDate.before(new Date())) {
            throw new Exception("Token is expired");
        }
        
        // Todas las validaciones pasaron
        HashMap<String, Integer> rolesMap = new HashMap<>();
        for (String role : roles) {
            rolesMap.put(role, role.length());
        }
        return ResponseEntity.ok(rolesMap);
    } catch (JWTVerificationException e) {
        logger.error("Token verification failed: {}", e.getMessage());
        throw new BussinesRuleException("01", "Token verification failed", HttpStatus.FORBIDDEN);
    } catch (Exception e) {
        logger.error("Exception: {}", e.getMessage());
        throw new BussinesRuleException("01", e.getMessage(), HttpStatus.FORBIDDEN);
    }
    }
    
    @GetMapping("/valid")
    public ResponseEntity<?> valid(@RequestHeader("Authorization") String authHeader) throws BussinesRuleException {
        try {
            restService.checkValidity(authHeader);
            return ResponseEntity.ok(new HashMap (){{
                put("is_valid", "true");
            }});
        } catch (Exception e) {
            logger.error("token is not valid, exception : {} ", e.getMessage());
           throw new BussinesRuleException("is_valid", "False",HttpStatus.FORBIDDEN);   
           
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
        String login = restService.login(username, password);
       return ResponseEntity.ok(login);
    }

    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout(@RequestParam(value = "refresh_token", name = "refresh_token") String refreshToken) throws BussinesRuleException {
        try {
            restService.logout(refreshToken);
            return ResponseEntity.ok(new HashMap (){{
                put("logout", "true");
            }});
        } catch (Exception e) {
            logger.error("unable to logout, exception : {} ", e.getMessage());
            throw new BussinesRuleException("logout", "False",HttpStatus.FORBIDDEN);   
        }
    }  
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refresh(@RequestParam(value = "refresh_token", name = "refresh_token") String refreshToken) throws BussinesRuleException {
        try {            
            return ResponseEntity.ok(restService.refresh(refreshToken));
        } catch (Exception e) {
            logger.error("unable to refresh, exception : {} ", e.getMessage());
            throw new BussinesRuleException("refresh", "False",HttpStatus.FORBIDDEN);   
        }
    }  
}
