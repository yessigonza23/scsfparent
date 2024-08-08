package ec.gob.mdi.scsf.menu.controller;

import ec.gob.mdi.scsf.menu.entities.MenuSiscyf;
import ec.gob.mdi.scsf.menu.exception.BussinesRuleException;
import ec.gob.mdi.scsf.menu.repository.MenuSiscyfRepository;
import ec.gob.mdi.scsf.menu.service.IMenuSiscyfService;
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

@Tag(name = "MenuSiscyf API", description = "This MenusSiscyf server all functionality for management Users")
@RestController
@RequestMapping("/menusiscyf")
public class MenuSiscyfRestController {
 
    @Autowired
    MenuSiscyfRepository repo;
   
    @Autowired
    IMenuSiscyfService service;
    
    @Operation(description = "Return all users", summary ="Return 204 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
         @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping
    public ResponseEntity<List<MenuSiscyf>> list() {
        List<MenuSiscyf> list = repo.findAll();
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }
     
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<MenuSiscyf> obj = repo.findById(id);
        if(obj.isPresent()){
            //return new ResponseEntity<>(category.get(), HttpStatus.OK);
            return ResponseEntity.ok(obj);
        }else{
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody MenuSiscyf input) {
        MenuSiscyf find = repo.findById(id).get();
        if (find != null) {
            find.setEstado(input.getEstado());
        }
        MenuSiscyf save = repo.save(input);
        return ResponseEntity.ok(save);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MenuSiscyf input) throws BussinesRuleException, UnknownHostException{
        MenuSiscyf save = repo.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        MenuSiscyf find = repo.findById(id).get();
        if(find != null){
            repo.delete(find);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/usuario")
    public ResponseEntity<List<MenuSiscyf>> listaMenuRolByUsuario(){
        List<MenuSiscyf> list = service.listaMenuRolByUsuario(""); 
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }
  
}
