package ec.gob.mdi.scsf.location.controller;
import ec.gob.mdi.scsf.location.entities.Province;
import ec.gob.mdi.scsf.location.exception.BussinesRuleException;
import ec.gob.mdi.scsf.location.repository.ProvinceRepository;
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

@Tag(name = "Province API", description = "This API server all functionality for management Province the location")
@RestController
@RequestMapping("/province")
public class ProvinceRestController {
    
    @Autowired
    ProvinceRepository provinceRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Province>> findAll() {
        List<Province> lista = provinceRepository.findAll();
        return ResponseEntity.ok(lista);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Province> province = provinceRepository.findById(id);
        if(province.isPresent()){
            return new ResponseEntity<>(province.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Province input) {
        Province find = provinceRepository.findById(id).get();
        if (find != null) {
            find.setCode(input.getCode());
            find.setIdCoordination(input.getIdCoordination());
            find.setLatitude(input.getLatitude());
            find.setLongitude(input.getLongitude());
            find.setName(input.getName());
        }
        Province obj = provinceRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Province input) throws BussinesRuleException, UnknownHostException{
        Province obj = provinceRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Province find = provinceRepository.findById(id).get();
        if (find != null) {
            provinceRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
