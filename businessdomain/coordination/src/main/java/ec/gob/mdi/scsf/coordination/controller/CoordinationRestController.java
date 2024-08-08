package ec.gob.mdi.scsf.coordination.controller;

import ec.gob.mdi.scsf.coordination.entities.Coordination;
import ec.gob.mdi.scsf.coordination.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.coordination.repository.CoordinationRepository;

@Tag(name = "Coordination API", description = "This API server all functionality for management Coordinations")
@RestController
@RequestMapping("/coordination")
public class CoordinationRestController {
    
    @Autowired
    CoordinationRepository coordinationRepository;
    
    @Operation(description = "Return all coordination", summary ="Return 204 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
         @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping
    public List<Coordination> list() {
        return coordinationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Coordination> obj = coordinationRepository.findById(id);
        if(obj.isPresent()){
            //return new ResponseEntity<>(category.get(), HttpStatus.OK);
            return ResponseEntity.ok(obj);
        }else{
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Coordination input) {
        Coordination find = coordinationRepository.findById(id).get();
        if (find != null) {
            find.setAcronym(input.getAcronym());
            find.setAuthority(input.getAuthority());
            find.setAuthorityPosition(input.getAuthorityPosition());
            find.setEmail(input.getEmail());
            find.setName(input.getName());
        }
        Coordination save = coordinationRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Coordination input) throws BussinesRuleException, UnknownHostException{
        Coordination save = coordinationRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Coordination find = coordinationRepository.findById(id).get();
        if(find != null){
            coordinationRepository.delete(find);
        }
        return ResponseEntity.ok().build();
    }
}
