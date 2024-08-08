
package ec.gob.mdi.scsf.calrenwarehouse.controller;

import ec.gob.mdi.scsf.calrenwarehouse.entities.CalrenActCalCustomer;
import ec.gob.mdi.scsf.calrenwarehouse.exception.BussinesRuleException;
import ec.gob.mdi.scsf.calrenwarehouse.repository.CalrenActCalCustomerRepository;
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
@Tag(name = "CalrenActCalCustomer API", description = "This API server all functionality for management CalrenActCalCustomer the qualification renewals")
@RestController
@RequestMapping("/calrenActCalCustomers")
public class CalrenActCalCustomerRestController {
    
    @Autowired
    CalrenActCalCustomerRepository calrenActCalCustomersRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CalrenActCalCustomer>> findAll() {
        List<CalrenActCalCustomer> lista = calrenActCalCustomersRepository.findAll();
        return ResponseEntity.ok(lista);
    }        
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenActCalCustomer> calrenActCalCustomers = calrenActCalCustomersRepository.findById(id);
        if(calrenActCalCustomers.isPresent()){
            return new ResponseEntity<>(calrenActCalCustomers.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenActCalCustomer input) {
        CalrenActCalCustomer find = calrenActCalCustomersRepository.findById(id).get();
        if (find != null) {
            find.setCalrenQualificationActivitiesID(input.getCalrenQualificationActivitiesID());
            find.setQualificationRenewalsID(input.getQualificationRenewalsID());
        }
        CalrenActCalCustomer obj = calrenActCalCustomersRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenActCalCustomer input) throws BussinesRuleException, UnknownHostException{
        CalrenActCalCustomer obj = calrenActCalCustomersRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenActCalCustomer find = calrenActCalCustomersRepository.findById(id).get();
        if (find != null) {
            calrenActCalCustomersRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
