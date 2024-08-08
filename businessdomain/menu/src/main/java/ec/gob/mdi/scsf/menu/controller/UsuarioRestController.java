package ec.gob.mdi.scsf.menu.controller;

import ec.gob.mdi.scsf.menu.entities.Usuario;
import ec.gob.mdi.scsf.menu.exception.BussinesRuleException;
import ec.gob.mdi.scsf.menu.repository.UsuarioRepository;
import ec.gob.mdi.scsf.menu.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Usuario API", description = "This Usuario server all functionality for management Users")
@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
 
    @Autowired
    UsuarioRepository repo;
   
    @Autowired
    IUsuarioService service;
    
    @Operation(description = "Return all users", summary ="Return 204 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
         @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> list = repo.findAll();
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }
     
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Usuario> obj = repo.findById(id);
        if(obj.isPresent()){
            //return new ResponseEntity<>(category.get(), HttpStatus.OK);
            return ResponseEntity.ok(obj);
        }else{
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Usuario input) {
        Usuario find = repo.findById(id).get();
        if (find != null) {
            find.setCargo(input.getCargo());
            find.setNombre(input.getNombre());
            find.setPassword(input.getPassword());
            find.setEstado(input.getEstado());
        }
        Usuario save = repo.save(input);
        return ResponseEntity.ok(save);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Usuario input) throws BussinesRuleException, UnknownHostException{
        Usuario save = repo.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Usuario find = repo.findById(id).get();
        if(find != null){
            repo.delete(find);
        }
        return ResponseEntity.ok().build();
    }
    
     @GetMapping("/identificacion")
    public ResponseEntity<Usuario> usuarioByUsername(
            @RequestParam(value = "username",required = true) String username){
        Usuario usuario = service.usuarioByUsuario(username);
        if(usuario != null){
            //return new ResponseEntity<>(category.get(), HttpStatus.OK);
            return ResponseEntity.ok(usuario);
        }else{
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/exists/{identificacion}")
    public ResponseEntity<Usuario> existsUsuario(
            @RequestParam(value = "identificacion",required = true) String identificacion){
        Usuario usuario = service.existsUsuario(identificacion);
         if(usuario != null){
            //return new ResponseEntity<>(category.get(), HttpStatus.OK);
            return ResponseEntity.ok(usuario);
        }else{
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
  
}
