package ec.gob.mdi.scsf.catalogue.controller;

import ec.gob.mdi.scsf.catalogue.entities.Substance;
import ec.gob.mdi.scsf.catalogue.exception.BussinesRuleException;
import ec.gob.mdi.scsf.catalogue.repository.SubstanceRepository;
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

@Tag(name = "Substance API", description = "This API server all functionality for management Substance the catalogue")
@RestController
@RequestMapping("/substance")
public class SubstanceRestController {
    
    @Autowired
    SubstanceRepository substanceRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Substance>> findAll() {
        List<Substance> lista = substanceRepository.findAll();
        return ResponseEntity.ok(lista);
    }       
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Substance> substance = substanceRepository.findById(id);
        if(substance.isPresent()){
            return new ResponseEntity<>(substance.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Substance input) {
        Substance find = substanceRepository.findById(id).get();
        if (find != null) {
            find.setDenomination(input.getDenomination());
            find.setList(input.getList());
            find.setName(input.getName());
            find.setNeedRepTechnical(input.getNeedRepTechnical());
            find.setNumCase(input.getNumCase());
            find.setObservation(input.getObservation());
            find.setState(input.getState());
            find.setType(input.getType());
            find.setTypeSubstance(input.getTypeSubstance());
            find.setUnil(input.getUnil());
            find.setUniml(input.getUniml());
        }
        Substance obj = substanceRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Substance input) throws BussinesRuleException, UnknownHostException{
        Substance obj = substanceRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Substance find = substanceRepository.findById(id).get();
        if (find != null) {
            substanceRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
