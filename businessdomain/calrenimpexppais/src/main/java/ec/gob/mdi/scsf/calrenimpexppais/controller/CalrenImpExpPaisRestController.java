package ec.gob.mdi.scsf.calrenimpexppais.controller;
import ec.gob.mdi.scsf.calrenimpexppais.entities.CalrenImpExpPais;
import ec.gob.mdi.scsf.calrenimpexppais.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.calrenimpexppais.repository.CalrenImpExpPaisRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenImpExpPais API", description = "This API server all functionality for management CalrenImpExpPais the qualification renewals")
@RestController
@RequestMapping("/calrenimpexppais")
public class CalrenImpExpPaisRestController {
    
    @Autowired
    CalrenImpExpPaisRepository calrenImpExpPaisRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CalrenImpExpPais>> findAll() {
        List<CalrenImpExpPais> lista = calrenImpExpPaisRepository.findAll();
        return ResponseEntity.ok(lista);
    }       
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenImpExpPais> calrenCalrenImpExpPais = calrenImpExpPaisRepository.findById(id);
        if(calrenCalrenImpExpPais.isPresent()){
            return new ResponseEntity<>(calrenCalrenImpExpPais.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenImpExpPais input) {
        CalrenImpExpPais find = calrenImpExpPaisRepository.findById(id).get();
        if (find != null) {
            find.setActivityImpExp(input.getActivityImpExp());
            find.setCalrenSubstancesActivitiesID(input.getCalrenSubstancesActivitiesID());
            find.setCountryID(input.getCountryID());
        }
        CalrenImpExpPais obj = calrenImpExpPaisRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenImpExpPais input) throws BussinesRuleException, UnknownHostException{
        CalrenImpExpPais obj = calrenImpExpPaisRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenImpExpPais find = calrenImpExpPaisRepository.findById(id).get();
        if (find != null) {
            calrenImpExpPaisRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
