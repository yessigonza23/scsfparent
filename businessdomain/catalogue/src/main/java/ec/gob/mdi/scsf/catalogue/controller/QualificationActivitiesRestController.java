package ec.gob.mdi.scsf.catalogue.controller;

import ec.gob.mdi.scsf.catalogue.entities.QualificationActivities;
import ec.gob.mdi.scsf.catalogue.exception.BussinesRuleException;
import ec.gob.mdi.scsf.catalogue.repository.QualificationActivitiesRepository;
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

@Tag(name = "QualificationActivities API", description = "This API server all functionality for management QualificationActivities the catalogue")
@RestController
@RequestMapping("/qualificationactivities")
public class QualificationActivitiesRestController {
    
    @Autowired
    QualificationActivitiesRepository qualificationActivitiesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<QualificationActivities>> findAll() {
        List<QualificationActivities> lista = qualificationActivitiesRepository.findAll();
        return ResponseEntity.ok(lista);
    }        
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<QualificationActivities> qualificationActivities = qualificationActivitiesRepository.findById(id);
        if(qualificationActivities.isPresent()){
            return new ResponseEntity<>(qualificationActivities.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody QualificationActivities input) {
        QualificationActivities find = qualificationActivitiesRepository.findById(id).get();
        if (find != null) {
            find.setAbbreviation(input.getAbbreviation());
            find.setCodeBilling(input.getCodeBilling());
            find.setDefinition(input.getDefinition());
            find.setName(input.getName());
            find.setNameForm(input.getNameForm());
            find.setSiSubstance(input.getSiSubstance());
            find.setState(input.getState());
        }
        QualificationActivities obj = qualificationActivitiesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody QualificationActivities input) throws BussinesRuleException, UnknownHostException{
        QualificationActivities obj = qualificationActivitiesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        QualificationActivities find = qualificationActivitiesRepository.findById(id).get();
        if (find != null) {
            qualificationActivitiesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
