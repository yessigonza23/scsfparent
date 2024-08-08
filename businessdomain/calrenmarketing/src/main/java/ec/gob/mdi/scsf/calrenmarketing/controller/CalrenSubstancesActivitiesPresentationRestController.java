package ec.gob.mdi.scsf.calrenmarketing.controller;
import ec.gob.mdi.scsf.calrenmarketing.entities.CalrenSubstancesActivitiesPresentation;
import ec.gob.mdi.scsf.calrenmarketing.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.calrenmarketing.repository.CalrenSubstancesActivitiesPresentationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenSubstancesActivitiesPresentation API", description = "This API server all functionality for management CalrenSubstancesActivitiesPresentation the qualification renewals")
@RestController
@RequestMapping("/calrensubstancesactivitiespresentation")
public class CalrenSubstancesActivitiesPresentationRestController {
    
    @Autowired
    CalrenSubstancesActivitiesPresentationRepository calrenSubstancesActivitiesPresentationRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CalrenSubstancesActivitiesPresentation>> findAll() {
        List<CalrenSubstancesActivitiesPresentation> lista = calrenSubstancesActivitiesPresentationRepository.findAll();
        return ResponseEntity.ok(lista);
    }        
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenSubstancesActivitiesPresentation> calrenSubstancesActivitiesPresentation = calrenSubstancesActivitiesPresentationRepository.findById(id);
        if(calrenSubstancesActivitiesPresentation.isPresent()){
            return new ResponseEntity<>(calrenSubstancesActivitiesPresentation.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenSubstancesActivitiesPresentation input) {
        CalrenSubstancesActivitiesPresentation find = calrenSubstancesActivitiesPresentationRepository.findById(id).get();
        if (find != null) {
            find.setCalrenSubstancesActivities(input.getCalrenSubstancesActivities());
            find.setPresentationID(input.getPresentationID());
        }
        CalrenSubstancesActivitiesPresentation obj = calrenSubstancesActivitiesPresentationRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenSubstancesActivitiesPresentation input) throws BussinesRuleException, UnknownHostException{
        CalrenSubstancesActivitiesPresentation obj = calrenSubstancesActivitiesPresentationRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenSubstancesActivitiesPresentation find = calrenSubstancesActivitiesPresentationRepository.findById(id).get();
        if (find != null) {
            calrenSubstancesActivitiesPresentationRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
