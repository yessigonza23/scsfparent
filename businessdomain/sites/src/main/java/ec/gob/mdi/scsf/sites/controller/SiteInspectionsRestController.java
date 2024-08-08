package ec.gob.mdi.scsf.sites.controller;
import ec.gob.mdi.scsf.sites.entities.SiteInspections;
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
import ec.gob.mdi.scsf.sites.repository.SiteInspectionsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/siteInspections")
public class SiteInspectionsRestController {
    
    @Autowired
    SiteInspectionsRepository siteInspectionsRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    
   @GetMapping()
    public ResponseEntity<List<SiteInspections>> findAll() {
        List<SiteInspections> lista = siteInspectionsRepository.findAll();
        return ResponseEntity.ok(lista);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<SiteInspections> siteInspections = siteInspectionsRepository.findById(id);
        if(siteInspections.isPresent()){
            return new ResponseEntity<>(siteInspections.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody SiteInspections input) {
        SiteInspections find = siteInspectionsRepository.findById(id).get();
        if (find != null) {
            find.setConclusions(input.getConclusions());
            find.setCoordinationID(input.getCoordinationID());
            find.setDateInspection(input.getDateInspection());
            find.setFileNameCheck(input.getFileNameCheck());
            find.setFileNameInf(input.getFileNameInf());
            find.setFilePathCheck(input.getFilePathCheck());
            find.setFilePathInf(input.getFilePathInf());
            find.setMeets(input.getMeets());
            find.setNumReport(input.getNumReport());
            find.setOtherCoordination(input.getOtherCoordination());
            find.setPersonaInspection(input.getPersonaInspection());
            find.setPersonaInspectionCharge(input.getPersonaInspectionCharge());
            find.setPersonaInspectionLicense(input.getPersonaInspectionLicense());
            find.setRecomendations(input.getRecomendations());
            find.setRemarks(input.getRemarks());
            find.setSites(input.getSites());
            find.setState(input.getState());
            find.setUserID(input.getUserID());
        }
        SiteInspections obj = siteInspectionsRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody SiteInspections input) {
        SiteInspections obj = siteInspectionsRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
         SiteInspections find = siteInspectionsRepository.findById(id).get();
        if (find != null) {
            siteInspectionsRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
