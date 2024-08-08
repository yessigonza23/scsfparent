
package ec.gob.mdi.scsf.representatives.controller;

import ec.gob.mdi.scsf.representatives.entities.RepresentativesQualificationRenovation;
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
import ec.gob.mdi.scsf.representatives.repository.RepresentativesQualificationRenovationRepository;

@RestController
@RequestMapping("/representativesqualificationrenovation")
public class RepresentativesQualificationRenovationController {
    
    @Autowired
    RepresentativesQualificationRenovationRepository qualificationActivitiesRepository;
    
    @GetMapping()
    public List<RepresentativesQualificationRenovation> findAll() {
        return qualificationActivitiesRepository.findAll();
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") long id) {
        Optional<RepresentativesQualificationRenovation> representativesQualificationRenovation = qualificationActivitiesRepository.findById(id);
        if(representativesQualificationRenovation.isPresent()){
            return new ResponseEntity<>(representativesQualificationRenovation.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") long id, @RequestBody RepresentativesQualificationRenovation input) {
        Optional<RepresentativesQualificationRenovation> optionalRepresentativesQualificationRenovation = qualificationActivitiesRepository.findById(id);
        if (optionalRepresentativesQualificationRenovation.isPresent()) {
            RepresentativesQualificationRenovation newRepresentativesQualificationRenovation = optionalRepresentativesQualificationRenovation.get();
            newRepresentativesQualificationRenovation = input;
            RepresentativesQualificationRenovation save = qualificationActivitiesRepository.save(newRepresentativesQualificationRenovation);
          return new ResponseEntity<>(save, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody RepresentativesQualificationRenovation input) {
        RepresentativesQualificationRenovation save = qualificationActivitiesRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        qualificationActivitiesRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
