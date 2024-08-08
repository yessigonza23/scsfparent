
package ec.gob.mdi.scsf.service;


import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.net.URL;
import java.security.interfaces.RSAPublicKey;


@Service
public class JwtService {

     @Value("${keycloak.jwk-set-uri}")
    private String jwksUrl;

    @Value("${keycloak.certs-id}")
    private String certsId;

   public void verifyToken(String token) throws Exception {
        DecodedJWT jwt = JWT.decode(token);
        JwkProvider provider = new UrlJwkProvider(new URL(jwksUrl));
        Jwk jwk = provider.get(jwt.getKeyId());
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwt.getIssuer())
                .build();
        verifier.verify(token);
    }
}

