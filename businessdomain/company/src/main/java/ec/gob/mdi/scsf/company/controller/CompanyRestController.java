package ec.gob.mdi.scsf.company.controller;
import ec.gob.mdi.scsf.company.entities.Company;
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
import ec.gob.mdi.scsf.company.repository.CompanyRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Tag(name = "Company API", description = "This API server all functionality for management Company ")
@RestController
@RequestMapping("/v1/company")
public class CompanyRestController {
    
    @Autowired
    CompanyRepository companyRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error") ,
        @ApiResponse(responseCode = "404", description = "No se encontró información"),
        @ApiResponse(responseCode = "400", description = "Informacion nula")})    
    @GetMapping()
    public ResponseEntity<List<Company>> findAll() {
        List<Company> lista = companyRepository.findAll();
        return ResponseEntity.ok(lista);
    }        
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Company input) {
        Company find = companyRepository.findById(id).get();
        if (find != null) {
            find.setAlternatePhone(input.getAlternatePhone());
            find.setCauseCancellation(input.getCauseCancellation());
            find.setCellPhone(input.getCellPhone());
            find.setCompanyType(input.getCompanyType());
            find.setContactPerson(input.getContactPerson());
            find.setDateCancellation(input.getDateCancellation());
            find.setDateQualification(input.getDateQualification());
            find.setDateRegistration(input.getDateRegistration());
            find.setEmail(input.getEmail());
            find.setEmailContact(input.getEmailContact());
            find.setExpirationTermSocial(input.getExpirationTermSocial());
            find.setHandlesSubstances(input.getHandlesSubstances());
            find.setJDateConstitution(input.getJDateConstitution());
            find.setJDateDossier(input.getJDateDossier());
            find.setJLegalStatus(input.getJLegalStatus());
            find.setJNumDossier(input.getJNumDossier());
            find.setMainActivity(input.getMainActivity());
            find.setName(input.getName());
            find.setNumEmployees(input.getNumEmployees());
            find.setNumQualification(input.getNumQualification());
            find.setODateActaConstitutive(input.getODateActaConstitutive());
            find.setODateResolution(input.getODateResolution());
            find.setONumResolution(input.getONumResolution());
            find.setOStateTaxpaye(input.getOStateTaxpaye());
            find.setPActCreation(input.getPActCreation());
            find.setPDateOfficialRegistration(input.getPDateOfficialRegistration());
            find.setPDateCreation(input.getPDateCreation());
            find.setPOfficialRegistration(input.getPOfficialRegistration());
            find.setParroquiaID(input.getParroquiaID());
            find.setPhoneContact(input.getPhoneContact());
            find.setPrimaryPhone(input.getPrimaryPhone());
            find.setRuc(input.getRuc());
            find.setState(input.getState());
            find.setSecondaryActivity(input.getSecondaryActivity());
            find.setTaxPayerType(input.getTaxPayerType());
            find.setTaxpayerSubtype(input.getTaxpayerSubtype());
            find.setTradeName(input.getTradeName());
            find.setTransportsSubstances(input.getTransportsSubstances());
            find.setTypePerson(input.getTypePerson());
        }
        Company obj = companyRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Company input) throws BussinesRuleException, UnknownHostException{
        Company obj = companyRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Company find = companyRepository.findById(id).get();
        if (find != null) {
            companyRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/active/{ruc}")
    public ResponseEntity<?> validityCompanyActive(@PathVariable(name = "ruc") String ruc) {
        Company company = companyRepository.validityCompanyActive(ruc);
        if(company !=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error") ,
        @ApiResponse(responseCode = "404", description = "No se encontró información"),
        @ApiResponse(responseCode = "400", description = "Informacion nula")})
    @GetMapping("/exists/{ruc}")
    public ResponseEntity<?> existsCompany(@PathVariable(name = "ruc") String ruc) {
        Company company = companyRepository.existsCompany(ruc);
        System.out.println("Este es un mensaje en la consola " + company);
        if(company !=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           
        }
    }
 
}
