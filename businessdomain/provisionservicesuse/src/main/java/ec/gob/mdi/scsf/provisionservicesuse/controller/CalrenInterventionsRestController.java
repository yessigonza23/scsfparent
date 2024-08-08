package ec.gob.mdi.scsf.provisionservicesuse.controller;

import ec.gob.mdi.scsf.provisionservicesuse.entities.CalrenInterventions;
import ec.gob.mdi.scsf.provisionservicesuse.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.provisionservicesuse.repository.CalrenInterventionsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenInterventions API", description = "This API server all functionality for management CalrenInterventions the qualification renewals")
@RestController
@RequestMapping("/calreninterventions")
public class CalrenInterventionsRestController {
    
    @Autowired
    CalrenInterventionsRepository calrenInterventionsRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<CalrenInterventions>> findAll() {
        List<CalrenInterventions> lista = calrenInterventionsRepository.findAll();
        return ResponseEntity.ok(lista);
    }   
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenInterventions> calrenInterventions = calrenInterventionsRepository.findById(id);
        if(calrenInterventions.isPresent()){
            return new ResponseEntity<>(calrenInterventions.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenInterventions input) {
        CalrenInterventions find = calrenInterventionsRepository.findById(id).get();
        if (find != null) {
            find.setActivity(input.getActivity());
            find.setActualCapacity(input.getActualCapacity());
            find.setNominalCapacity(input.getNominalCapacity());
            find.setProductName(input.getProductName());
            find.setQualificationRenewalsID(input.getQualificationRenewalsID());
            find.setQuantityOfFinishedProduct(input.getQuantityOfFinishedProduct());
            find.setRelationPercentage(input.getRelationPercentage());
            find.setType(input.getType());
            find.setYield(input.getYield());
        }
        CalrenInterventions obj = calrenInterventionsRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenInterventions input) throws BussinesRuleException, UnknownHostException{
        CalrenInterventions obj = calrenInterventionsRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenInterventions find = calrenInterventionsRepository.findById(id).get();
        if (find != null) {
            calrenInterventionsRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
