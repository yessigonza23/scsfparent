package ec.gob.mdi.scsf.company.controller;
import ec.gob.mdi.scsf.company.entities.CompanyRepresentative;
import ec.gob.mdi.scsf.company.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.company.repository.CompanyRepresentativeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CompanyRepresentative API", description = "This API server all functionality for management CompanyRepresentative the company")
@RestController
@RequestMapping("/companyrepresentative")
public class CompanyRepresentativeRestController {
    
    @Autowired
    CompanyRepresentativeRepository companyRepresentativeRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CompanyRepresentative>> findAll() {
        List<CompanyRepresentative> lista = companyRepresentativeRepository.findAll();
        return ResponseEntity.ok(lista);
    }       
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CompanyRepresentative> companyRepresentative = companyRepresentativeRepository.findById(id);
        if(companyRepresentative.isPresent()){
            return new ResponseEntity<>(companyRepresentative.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CompanyRepresentative input) {
        CompanyRepresentative find = companyRepresentativeRepository.findById(id).get();
        if (find != null) {
            find.setCompany(input.getCompany());
            find.setDateAppointmentBod(input.getDateAppointmentBod());
            find.setNumContractAppointmentBod(input.getNumContractAppointmentBod());
            find.setRepresentativesID(input.getRepresentativesID());
            find.setState(input.getState());
        }
        CompanyRepresentative obj = companyRepresentativeRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CompanyRepresentative input) throws BussinesRuleException, UnknownHostException{
        CompanyRepresentative obj = companyRepresentativeRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CompanyRepresentative find = companyRepresentativeRepository.findById(id).get();
        if (find != null) {
            companyRepresentativeRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
