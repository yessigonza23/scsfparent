package ec.gob.mdi.scsf.location.controller;
import ec.gob.mdi.scsf.location.entities.Coordination;
import ec.gob.mdi.scsf.location.exception.BussinesRuleException;
import ec.gob.mdi.scsf.location.repository.CoordinationRepository;
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

@Tag(name = "Coordination API", description = "This API server all functionality for management Coordination the location")
@RestController
@RequestMapping("/coordination")
public class CoordinationRestController {
    
    @Autowired
    CoordinationRepository coordinationRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Coordination>> findAll() {
        List<Coordination> lista = coordinationRepository.findAll();
        return ResponseEntity.ok(lista);
    }       
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Coordination> coordination = coordinationRepository.findById(id);
        if(coordination.isPresent()){
            return new ResponseEntity<>(coordination.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        Coordination obj = coordinationRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Coordination input) throws BussinesRuleException, UnknownHostException{
        Coordination obj = coordinationRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Coordination find = coordinationRepository.findById(id).get();
        if (find != null) {
            coordinationRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
