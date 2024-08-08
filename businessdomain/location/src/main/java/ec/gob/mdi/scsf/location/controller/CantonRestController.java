package ec.gob.mdi.scsf.location.controller;
import ec.gob.mdi.scsf.location.entities.Canton;
import ec.gob.mdi.scsf.location.exception.BussinesRuleException;
import ec.gob.mdi.scsf.location.repository.CantonRepository;
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

@Tag(name = "Canton API", description = "This API server all functionality for management Canton the location")
@RestController
@RequestMapping("/canton")
public class CantonRestController {
    
    @Autowired
    CantonRepository cantonRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Canton>> findAll() {
        List<Canton> lista = cantonRepository.findAll();
        return ResponseEntity.ok(lista);
    }     
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Canton> canton = cantonRepository.findById(id);
        if(canton.isPresent()){
            return new ResponseEntity<>(canton.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Canton input) {
        Canton find = cantonRepository.findById(id).get();
        if (find != null) {
            find.setCode(input.getCode());
            find.setIdProvince(input.getIdProvince());
            find.setLatitude(input.getLatitude());
            find.setLongitude(input.getLongitude());
            find.setName(input.getName());
        }
        Canton obj = cantonRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Canton input) throws BussinesRuleException, UnknownHostException{
        Canton obj = cantonRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Canton find = cantonRepository.findById(id).get();
        if (find != null) {
            cantonRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
