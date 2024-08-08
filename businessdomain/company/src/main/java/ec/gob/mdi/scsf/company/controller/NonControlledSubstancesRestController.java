package ec.gob.mdi.scsf.company.controller;
import ec.gob.mdi.scsf.company.entities.NonControlledSubstances;
import ec.gob.mdi.scsf.company.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.company.repository.NonControlledSubstancesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "NonControlledSubstances API", description = "This API server all functionality for management NonControlledSubstances the company")
@RestController
@RequestMapping("/noncontrolledsubstances")
public class NonControlledSubstancesRestController {
    
    @Autowired
    NonControlledSubstancesRepository nonControlledSubstancesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<NonControlledSubstances>> findAll() {
        List<NonControlledSubstances> lista = nonControlledSubstancesRepository.findAll();
        return ResponseEntity.ok(lista);
    }        
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<NonControlledSubstances> nonControlledSubstances = nonControlledSubstancesRepository.findById(id);
        if(nonControlledSubstances.isPresent()){
            return new ResponseEntity<>(nonControlledSubstances.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody NonControlledSubstances input) {
        NonControlledSubstances find = nonControlledSubstancesRepository.findById(id).get();
        if (find != null) {
            find.setCompany(input.getCompany());
            find.setName(input.getName());
        }
        NonControlledSubstances obj = nonControlledSubstancesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody NonControlledSubstances input) throws BussinesRuleException, UnknownHostException{
        NonControlledSubstances obj = nonControlledSubstancesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        NonControlledSubstances find = nonControlledSubstancesRepository.findById(id).get();
        if (find != null) {
            nonControlledSubstancesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
