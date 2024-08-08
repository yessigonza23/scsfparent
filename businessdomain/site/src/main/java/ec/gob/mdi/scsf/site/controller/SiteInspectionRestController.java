package ec.gob.mdi.scsf.site.controller;

import ec.gob.mdi.scsf.site.dto.ResponseDTO;
import ec.gob.mdi.scsf.site.entities.SiteInspection;
import ec.gob.mdi.scsf.site.exception.BussinesRuleException;
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
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ec.gob.mdi.scsf.site.repository.SiteInspectionRepository;
import ec.gob.mdi.scsf.site.service.ISiteService;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Site API", description = "This API server all functionality for management Sites the company")
@RestController
@RequestMapping("/site")
public class SiteInspectionRestController {
    
    @Autowired
    private Environment env;
    
    @Autowired
    ISiteService siteService;
            
    @GetMapping("/check")
    public String check() {
        return "Hello your property value is: "+ env.getProperty("custom.activeprofileName");
    }

    @Autowired
    SiteInspectionRepository siteRepository;
    
    @Operation(description = "Return all sites", summary ="Return 204 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
         @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping
    public ResponseEntity<List<SiteInspection>> list() {
        List<SiteInspection> list = siteRepository.findAll();
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }
    
   @GetMapping("full")
   public Optional<SiteInspection> get(@RequestParam (name="id") long id){
        Optional<SiteInspection> site = siteRepository.findById(id);
       return site;
   }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<SiteInspection> obj = siteRepository.findById(id);
        if(obj.isPresent()){
            //return new ResponseEntity<>(category.get(), HttpStatus.OK);
            return ResponseEntity.ok(obj);
        }else{
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody SiteInspection input) {
        SiteInspection find = siteRepository.findById(id).get();
        if (find != null) {
            find.setAnotherCoordination(input.getAnotherCoordination());
            find.setConclusions(input.getConclusions());
            find.setCumple(input.getCumple());
            find.setDateInspection(input.getDateInspection());
            find.setNameFileCheck(input.getNameFileCheck());
            find.setNameFileInf(input.getNameFileInf());
            find.setNumberReport(input.getNumberReport());
            find.setObservations(input.getObservations());
            find.setPersonInspection(input.getPersonInspection());
            find.setPersonInspectionIdentification(input.getPersonInspectionIdentification());
            find.setRecommendations(input.getRecommendations());
            find.setRouteFileCheck(input.getRouteFileCheck());
            find.setRouteFileInf(input.getRouteFileInf());
            find.setStatus(input.getStatus());
        }
        SiteInspection save = siteRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody SiteInspection input) throws BussinesRuleException, UnknownHostException{
        SiteInspection save = siteRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        SiteInspection find = siteRepository.findById(id).get();
        if(find != null){
            siteRepository.delete(find);
        }
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/findById/{id}")
    public ResponseDTO getSiteInspectionById(@PathVariable(name = "id") long id) {
        return siteService.getSiteById(id);
    }
}
