package ec.gob.mdi.scsf.representatives.controller;

import ec.gob.mdi.scsf.representatives.entities.TypeRepresentative;
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
import ec.gob.mdi.scsf.representatives.repository.TypeRepresentativeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TypeRepresentative API", description = "This API server all functionality for management TypeRepresentative ")
@RestController
@RequestMapping("/typerepresentative")
public class TypeRepresentativeRestController {
    
    @Autowired
    TypeRepresentativeRepository typeRepresentativeRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
     @GetMapping()
    public ResponseEntity<List<TypeRepresentative>> findAll() {
        List<TypeRepresentative> lista = typeRepresentativeRepository.findAll();
        return ResponseEntity.ok(lista);
    }     
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<TypeRepresentative> typeRepresentative = typeRepresentativeRepository.findById(id);
        if(typeRepresentative.isPresent()){
            return new ResponseEntity<>(typeRepresentative.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody TypeRepresentative input) {
         TypeRepresentative find = typeRepresentativeRepository.findById(id).get();
        if (find != null) {
            find.setName(input.getName());
            find.setType(input.getType());
        }
        TypeRepresentative obj = typeRepresentativeRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody TypeRepresentative input) {
        TypeRepresentative obj = typeRepresentativeRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        TypeRepresentative find = typeRepresentativeRepository.findById(id).get();
        if (find != null) {
            typeRepresentativeRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
