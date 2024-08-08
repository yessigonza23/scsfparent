package ec.gob.mdi.scsf.catalogue.controller;

import ec.gob.mdi.scsf.catalogue.entities.TypeSubstance;
import ec.gob.mdi.scsf.catalogue.exception.BussinesRuleException;
import ec.gob.mdi.scsf.catalogue.repository.TypeSubstanceRepository;
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

@Tag(name = "TypeSubstance API", description = "This API server all functionality for management TypeSubstance the catalogue")
@RestController
@RequestMapping("/typesubstance")
public class TypeSubstanceRestController {
    
    @Autowired
    TypeSubstanceRepository typeSubstanceRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<TypeSubstance>> findAll() {
        List<TypeSubstance> lista = typeSubstanceRepository.findAll();
        return ResponseEntity.ok(lista);
    }        
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<TypeSubstance> typeSubstance = typeSubstanceRepository.findById(id);
        if(typeSubstance.isPresent()){
            return new ResponseEntity<>(typeSubstance.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody TypeSubstance input) {
        TypeSubstance find = typeSubstanceRepository.findById(id).get();
        if (find != null) {
            find.setDefinition(input.getDefinition());
            find.setName(input.getName());
            find.setState(input.getState());
        }
        TypeSubstance obj = typeSubstanceRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody TypeSubstance input) throws BussinesRuleException, UnknownHostException{
        TypeSubstance obj = typeSubstanceRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        TypeSubstance find = typeSubstanceRepository.findById(id).get();
        if (find != null) {
            typeSubstanceRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
