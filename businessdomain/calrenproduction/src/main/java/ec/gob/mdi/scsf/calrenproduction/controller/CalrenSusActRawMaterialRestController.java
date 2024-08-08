package ec.gob.mdi.scsf.calrenproduction.controller;

import ec.gob.mdi.scsf.calrenproduction.entities.CalrenSusActRawMaterial;
import ec.gob.mdi.scsf.calrenproduction.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.calrenproduction.repository.CalrenSusActRawMaterialRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenSusActRawMaterial API", description = "This API server all functionality for management CalrenSusActRawMaterial the qualification renewals")
@RestController
@RequestMapping("/calrensusactrawmaterial")
public class CalrenSusActRawMaterialRestController {
    
    @Autowired
    CalrenSusActRawMaterialRepository calrenSusActRawMaterialRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CalrenSusActRawMaterial>> findAll() {
        List<CalrenSusActRawMaterial> lista = calrenSusActRawMaterialRepository.findAll();
        return ResponseEntity.ok(lista);
    }   
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenSusActRawMaterial> calrenSusActRawMaterial = calrenSusActRawMaterialRepository.findById(id);
        if(calrenSusActRawMaterial.isPresent()){
            return new ResponseEntity<>(calrenSusActRawMaterial.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenSusActRawMaterial input) {
        CalrenSusActRawMaterial find = calrenSusActRawMaterialRepository.findById(id).get();
        if (find != null) {
            find.setCalrenSubstancesActivitiesID(input.getCalrenSubstancesActivitiesID());
            find.setCalrenSubstancesActivitiesProduccionID(input.getCalrenSubstancesActivitiesProduccionID());
            find.setNonControlledSubstancesID(input.getNonControlledSubstancesID());
            find.setPercentageRatio(input.getPercentageRatio());
            find.setPorcentage(input.getPorcentage());
        }
        CalrenSusActRawMaterial obj = calrenSusActRawMaterialRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenSusActRawMaterial input) throws BussinesRuleException, UnknownHostException{
        CalrenSusActRawMaterial obj = calrenSusActRawMaterialRepository.save(input);
       return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenSusActRawMaterial find = calrenSusActRawMaterialRepository.findById(id).get();
        if (find != null) {
            calrenSusActRawMaterialRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
