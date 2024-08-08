package ec.gob.mdi.scsf.professional.controller;
import ec.gob.mdi.scsf.professional.entities.Ciiu;
import ec.gob.mdi.scsf.professional.exception.BussinesRuleException;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ec.gob.mdi.scsf.professional.repository.CiiuRepository;
import java.net.UnknownHostException;

@Tag(name = "Ciuu API", description = "This API server all functionality for management Ciuu the professional")
@RestController
@RequestMapping("/ciuu")
public class CiiuRestController {
    
    @Autowired
    CiiuRepository ciuuRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<Ciiu>> findAll() {
        List<Ciiu> lista = ciuuRepository.findAll();
        return ResponseEntity.ok(lista);
    }   
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Ciiu> ciuu = ciuuRepository.findById(id);
        if(ciuu.isPresent()){
            return new ResponseEntity<>(ciuu.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Ciiu input) {
        Ciiu find = ciuuRepository.findById(id).get();
        if (find != null) {
            find.setCode(input.getCode());
            find.setDescription(input.getDescription());
        }
        Ciiu obj = ciuuRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Ciiu input) throws BussinesRuleException, UnknownHostException{
        Ciiu obj = ciuuRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Ciiu find = ciuuRepository.findById(id).get();
        if (find != null) {
            ciuuRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
