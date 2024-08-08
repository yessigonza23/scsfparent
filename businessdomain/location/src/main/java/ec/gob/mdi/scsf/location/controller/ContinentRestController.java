package ec.gob.mdi.scsf.location.controller;
import ec.gob.mdi.scsf.location.entities.Continent;
import ec.gob.mdi.scsf.location.exception.BussinesRuleException;
import ec.gob.mdi.scsf.location.repository.ContinentRepository;
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

@Tag(name = "Continent API", description = "This API server all functionality for management Continent the location")
@RestController
@RequestMapping("/continent")
public class ContinentRestController {
    
    @Autowired
    ContinentRepository continentRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    
    @GetMapping()
    public ResponseEntity<List<Continent>> findAll() {
        List<Continent> lista = continentRepository.findAll();
        return ResponseEntity.ok(lista);
    }     
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Continent> continent = continentRepository.findById(id);
        if(continent.isPresent()){
            return new ResponseEntity<>(continent.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Continent input) {
        Continent find = continentRepository.findById(id).get();
        if (find != null) {
            find.setName(input.getName());
        }
        Continent obj = continentRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Continent input) throws BussinesRuleException, UnknownHostException{
        Continent obj = continentRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Continent find = continentRepository.findById(id).get();
        if (find != null) {
            continentRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
