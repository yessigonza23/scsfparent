package ec.gob.mdi.scsf.qualificationsrenewals.controller;
import ec.gob.mdi.scsf.qualificationsrenewals.entities.CalrenQualificationActivities;
import ec.gob.mdi.scsf.qualificationsrenewals.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.qualificationsrenewals.repository.CalrenQualificationActivitiesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenQualificationActivities API", description = "This API server all functionality for management CalrenQualificationActivities the qualificationsrenewals")
@RestController
@RequestMapping("/calrenqualificationactivities")
public class CalrenQualificationActivitiesController {
    
    @Autowired
    CalrenQualificationActivitiesRepository calrenQualificationActivitiesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    
   @GetMapping()
    public ResponseEntity<List<CalrenQualificationActivities>> findAll() {
        List<CalrenQualificationActivities> lista = calrenQualificationActivitiesRepository.findAll();
        return ResponseEntity.ok(lista);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenQualificationActivities> calrenQualificationActivities = calrenQualificationActivitiesRepository.findById(id);
        if(calrenQualificationActivities.isPresent()){
            return new ResponseEntity<>(calrenQualificationActivities.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenQualificationActivities input) {
         CalrenQualificationActivities find = calrenQualificationActivitiesRepository.findById(id).get();
        if (find != null) {
            find.setActivityQualificationID(input.getActivityQualificationID());
            find.setEmail(input.getEmail());
            find.setEmailAlternate(input.getEmailAlternate());
            find.setExportOperatorCode(input.getExportOperatorCode());
            find.setImportOperatorCode(input.getImportOperatorCode());
            find.setPermissionArcsa(input.getPermissionArcsa());
            find.setQualificationsRenewals(input.getQualificationsRenewals());
            find.setState(input.getState());
            find.setStorageArea(input.getStorageArea());
            find.setStorageCapacity(input.getStorageCapacity());
        }
        CalrenQualificationActivities obj = calrenQualificationActivitiesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenQualificationActivities input) throws BussinesRuleException, UnknownHostException{
        CalrenQualificationActivities obj = calrenQualificationActivitiesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenQualificationActivities find = calrenQualificationActivitiesRepository.findById(id).get();
        if (find != null) {
            calrenQualificationActivitiesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
