package ec.gob.mdi.scsf.calrenwarehouse.controller;

import ec.gob.mdi.scsf.calrenwarehouse.entities.CalrenActCalCustomer;
import ec.gob.mdi.scsf.calrenwarehouse.entities.CalrenActCalCustomerSubstances;
import ec.gob.mdi.scsf.calrenwarehouse.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.calrenwarehouse.repository.CalrenActCalCustomerSubstancesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenActCalCustomerSubstances API", description = "This API server all functionality for management CalrenActCalCustomerSubstances the qualification renewals")
@RestController
@RequestMapping("/calrenactcalcustomersubstances")
public class CalrenActCalCustomerSubstancesRestController {
    
    @Autowired
    CalrenActCalCustomerSubstancesRepository calrenActCalCustomerSubstancesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CalrenActCalCustomerSubstances>> findAll() {
        List<CalrenActCalCustomerSubstances> lista = calrenActCalCustomerSubstancesRepository.findAll();
        return ResponseEntity.ok(lista);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenActCalCustomerSubstances> calrenActCalCustomerSubstances = calrenActCalCustomerSubstancesRepository.findById(id);
        if(calrenActCalCustomerSubstances.isPresent()){
            return new ResponseEntity<>(calrenActCalCustomerSubstances.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenActCalCustomerSubstances input) {
        CalrenActCalCustomerSubstances find = calrenActCalCustomerSubstancesRepository.findById(id).get();
        if (find != null) {
            find.setCalrenActCalCustomer(input.getCalrenActCalCustomer());
            find.setCalrenSubstancesID(input.getCalrenSubstancesID());
        }
        CalrenActCalCustomerSubstances obj = calrenActCalCustomerSubstancesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenActCalCustomerSubstances input) throws BussinesRuleException, UnknownHostException{
        CalrenActCalCustomerSubstances obj = calrenActCalCustomerSubstancesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenActCalCustomerSubstances find = calrenActCalCustomerSubstancesRepository.findById(id).get();
        if (find != null) {
            calrenActCalCustomerSubstancesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
