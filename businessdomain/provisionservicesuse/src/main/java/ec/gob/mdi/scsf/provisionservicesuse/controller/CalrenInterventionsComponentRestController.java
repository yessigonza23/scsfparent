package ec.gob.mdi.scsf.provisionservicesuse.controller;

import ec.gob.mdi.scsf.provisionservicesuse.entities.CalrenInterventionsComponent;
import ec.gob.mdi.scsf.provisionservicesuse.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.provisionservicesuse.repository.CalrenInterventionsComponentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenInterventionsComponent API", description = "This API server all functionality for management CalrenInterventionsComponent the qualification renewals")
@RestController
@RequestMapping("/calreninterventionscomponent")
public class CalrenInterventionsComponentRestController {
    
    @Autowired
    CalrenInterventionsComponentRepository calrenInterventionsComponentRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<CalrenInterventionsComponent>> findAll() {
        List<CalrenInterventionsComponent> lista = calrenInterventionsComponentRepository.findAll();
        return ResponseEntity.ok(lista);
    }   
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenInterventionsComponent> calrenInterventionsComponent = calrenInterventionsComponentRepository.findById(id);
        if(calrenInterventionsComponent.isPresent()){
            return new ResponseEntity<>(calrenInterventionsComponent.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenInterventionsComponent input) {
        CalrenInterventionsComponent find = calrenInterventionsComponentRepository.findById(id).get();
        if (find != null) {
            find.setCalrenIntervenciones(input.getCalrenIntervenciones());
            find.setCalrenSubstancesActivitiesID(input.getCalrenSubstancesActivitiesID());
            find.setNonControlledSubstancesID(input.getNonControlledSubstancesID());
            find.setPorcentage(input.getPorcentage());
        }
        CalrenInterventionsComponent obj = calrenInterventionsComponentRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenInterventionsComponent input) throws BussinesRuleException, UnknownHostException{
        CalrenInterventionsComponent obj = calrenInterventionsComponentRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
       CalrenInterventionsComponent find = calrenInterventionsComponentRepository.findById(id).get();
        if (find != null) {
            calrenInterventionsComponentRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
