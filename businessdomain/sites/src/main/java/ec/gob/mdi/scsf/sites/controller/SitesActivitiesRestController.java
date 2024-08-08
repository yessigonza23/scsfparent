package ec.gob.mdi.scsf.sites.controller;
import ec.gob.mdi.scsf.sites.entities.SitesActivities;
import ec.gob.mdi.scsf.sites.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.sites.repository.SitesActivitiesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "Site Activities API", description = "This API server all functionality for management Sites the company")
@RestController
@RequestMapping("/sitesActivities")
public class SitesActivitiesRestController {
    
    @Autowired
    SitesActivitiesRepository sitesActivitiesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    
   @GetMapping()
    public ResponseEntity<List<SitesActivities>> findAll() {
        List<SitesActivities> lista = sitesActivitiesRepository.findAll();
        return ResponseEntity.ok(lista);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<SitesActivities> sitesActivities = sitesActivitiesRepository.findById(id);
        if(sitesActivities.isPresent()){
            return new ResponseEntity<>(sitesActivities.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody SitesActivities input) {
      SitesActivities find = sitesActivitiesRepository.findById(id).get();
        if (find != null) {
            find.setCalrenQualificationActivitiesID(input.getCalrenQualificationActivitiesID());
            find.setSites(input.getSites());
        }
        SitesActivities obj = sitesActivitiesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody SitesActivities input) throws BussinesRuleException, UnknownHostException {
        SitesActivities obj = sitesActivitiesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        SitesActivities find = sitesActivitiesRepository.findById(id).get();
        if (find != null) {
            sitesActivitiesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
