package ec.gob.mdi.scsf.sites.controller;
import ec.gob.mdi.scsf.sites.entities.RepresentativeCompanySites;
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
import ec.gob.mdi.scsf.sites.repository.RepresentativeCompanySitesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "RepresentativeCompanySites API", description = "This API server all functionality for management RepresentativeCompanySites the sites")
@RestController
@RequestMapping("/representativecompanysites")
public class RepresentativeCompanySitesRestController {
    
    @Autowired
    RepresentativeCompanySitesRepository representativeCompanySitesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<RepresentativeCompanySites>> findAll() {
        List<RepresentativeCompanySites> lista = representativeCompanySitesRepository.findAll();
        return ResponseEntity.ok(lista);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<RepresentativeCompanySites> representativeCompanySites = representativeCompanySitesRepository.findById(id);
        if(representativeCompanySites.isPresent()){
            return new ResponseEntity<>(representativeCompanySites.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody RepresentativeCompanySites input) {
        RepresentativeCompanySites find = representativeCompanySitesRepository.findById(id).get();
        if (find != null) {
            find.setCompanyRepresentativeID(input.getCompanyRepresentativeID());
            find.setSites(input.getSites());
            find.setState(input.getState());
        }
        RepresentativeCompanySites obj = representativeCompanySitesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody RepresentativeCompanySites input) throws BussinesRuleException, UnknownHostException{
        RepresentativeCompanySites obj = representativeCompanySitesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        RepresentativeCompanySites find = representativeCompanySitesRepository.findById(id).get();
        if (find != null) {
            representativeCompanySitesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
