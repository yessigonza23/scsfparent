package ec.gob.mdi.scsf.qualificationsrenewals.controller;
import ec.gob.mdi.scsf.qualificationsrenewals.entities.CalrenSubstances;
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
import ec.gob.mdi.scsf.qualificationsrenewals.repository.CalrenSubstancesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CalrenSubstances API", description = "This API server all functionality for management CalrenSubstances the qualificationrenewals")
@RestController
@RequestMapping("/calrensubstances")
public class CalrenSubstancesController {
    
    @Autowired
    CalrenSubstancesRepository calrenSubstancesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CalrenSubstances>> findAll() {
        List<CalrenSubstances> lista = calrenSubstancesRepository.findAll();
        return ResponseEntity.ok(lista);
    }  
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenSubstances> calrenSubstances = calrenSubstancesRepository.findById(id);
        if(calrenSubstances.isPresent()){
            return new ResponseEntity<>(calrenSubstances.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenSubstances input) {
        CalrenSubstances find = calrenSubstancesRepository.findById(id).get();
        if (find != null) {
            find.setPresentationID(input.getPresentationID());
            find.setQualificationsRenewals(input.getQualificationsRenewals());
            find.setQuotaAssigned(input.getQuotaAssigned());
            find.setQuotaRequest(input.getQuotaRequest());
            find.setState(input.getState());
            find.setSubstanceID(input.getSubstanceID());
            find.setTypeChange(input.getTypeChange());
            find.setTypeChangeID(input.getTypeChangeID());
            find.setUnit(input.getUnit());
        }
        CalrenSubstances obj = calrenSubstancesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenSubstances input) {
        CalrenSubstances obj = calrenSubstancesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenSubstances find = calrenSubstancesRepository.findById(id).get();
        if (find != null) {
            calrenSubstancesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
