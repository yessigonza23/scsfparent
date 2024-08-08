package ec.gob.mdi.scsf.calrenmarketing.controller;
import ec.gob.mdi.scsf.calrenmarketing.entities.CalrenSubstancesActivities;
import ec.gob.mdi.scsf.calrenmarketing.exception.BussinesRuleException;
import ec.gob.mdi.scsf.calrenmarketing.repository.CalrenSubstancesActivitiesRepository;
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

@Tag(name = "CalrenSubstancesActivities API", description = "This API server all functionality for management CalrenSubstancesActivities the qualification renewals")
@RestController
@RequestMapping("/calrensubstancesactivities")
public class CalrenSubstancesActivitiesRestController {
    
    @Autowired
    CalrenSubstancesActivitiesRepository calrenSubstancesActivitiesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CalrenSubstancesActivities>> findAll() {
        List<CalrenSubstancesActivities> lista = calrenSubstancesActivitiesRepository.findAll();
        return ResponseEntity.ok(lista);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenSubstancesActivities> calrenCalrenSubstancesActivities = calrenSubstancesActivitiesRepository.findById(id);
        if(calrenCalrenSubstancesActivities.isPresent()){
            return new ResponseEntity<>(calrenCalrenSubstancesActivities.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenSubstancesActivities input) {
        CalrenSubstancesActivities find = calrenSubstancesActivitiesRepository.findById(id).get();
        if (find != null) {
            find.setCalrenQualificationActivities(input.getCalrenQualificationActivities());
            find.setCalrenSubstancesID(input.getCalrenSubstancesID());
            find.setCapInsNominal(input.getCapInsNominal());
            find.setCapInsReal(input.getCapInsReal());
            find.setFrom(input.getFrom());
            find.setMaxim(input.getMaxim());
            find.setMinimum(input.getMinimum());
            find.setObservation(input.getObservation());
            find.setPercentageRatio(input.getPercentageRatio());
            find.setProductionProcess(input.getProductionProcess());
            find.setRepackaging(input.getRepackaging());
            find.setType(input.getType());
            find.setTypeEmployment(input.getTypeEmployment());
            find.setUntil(input.getUntil());
            find.setYield(input.getYield());
         
        }
        CalrenSubstancesActivities obj = calrenSubstancesActivitiesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenSubstancesActivities input) throws BussinesRuleException, UnknownHostException{
        CalrenSubstancesActivities obj = calrenSubstancesActivitiesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenSubstancesActivities find = calrenSubstancesActivitiesRepository.findById(id).get();
        if (find != null) {
            calrenSubstancesActivitiesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
