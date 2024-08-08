package ec.gob.mdi.scsf.professional.controller;
import ec.gob.mdi.scsf.professional.entities.Uses;
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
import ec.gob.mdi.scsf.professional.repository.UsesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "Uses API", description = "This API server all functionality for management Uses the professional")
@RestController
@RequestMapping("/uses")
public class UsesRestController {
    
    @Autowired
    UsesRepository usesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<Uses>> findAll() {
        List<Uses> lista = usesRepository.findAll();
        return ResponseEntity.ok(lista);
    }   
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Uses> uses = usesRepository.findById(id);
        if(uses.isPresent()){
            return new ResponseEntity<>(uses.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Uses input) {
        Uses find = usesRepository.findById(id).get();
        if (find != null) {
            find.setAcronyms(input.getAcronyms());
            find.setCiiu(input.getCiiu());
            find.setName(input.getName());
        }
        Uses obj = usesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Uses input) throws BussinesRuleException, UnknownHostException{
        Uses obj = usesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Uses find = usesRepository.findById(id).get();
        if (find != null) {
            usesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
