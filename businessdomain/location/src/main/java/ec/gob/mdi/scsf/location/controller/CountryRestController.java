package ec.gob.mdi.scsf.location.controller;
import ec.gob.mdi.scsf.location.entities.Country;
import ec.gob.mdi.scsf.location.exception.BussinesRuleException;
import ec.gob.mdi.scsf.location.repository.CountryRepository;
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

@Tag(name = "Country API", description = "This API server all functionality for management Country the location")
@RestController
@RequestMapping("/country")
public class CountryRestController {
    
    @Autowired
    CountryRepository countryRepository;
    
   @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Country>> findAll() {
        List<Country> lista = countryRepository.findAll();
        return ResponseEntity.ok(lista);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Country> country = countryRepository.findById(id);
        if(country.isPresent()){
            return new ResponseEntity<>(country.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Country input) {
        Country find = countryRepository.findById(id).get();
        if (find != null) {
            find.setCodealphathree(input.getCodealphathree());
            find.setCodealphatwo(input.getCodealphatwo());
            find.setIdContinent(input.getIdContinent());
            find.setName(input.getName());
            find.setNationality(input.getNationality());
        }
        Country obj = countryRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Country input) throws BussinesRuleException, UnknownHostException{
        Country obj = countryRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Country find = countryRepository.findById(id).get();
        if (find != null) {
            countryRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
