package ec.gob.mdi.scsf.representatives.controller;

import ec.gob.mdi.scsf.representatives.entities.Representatives;
import ec.gob.mdi.scsf.representatives.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.representatives.repository.RepresentativesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "Representatives API", description = "This API server all functionality for management Representatives")
@RestController
@RequestMapping("/representatives")
public class RepresentativesRestController {
    
    @Autowired
    RepresentativesRepository representativesRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

   @GetMapping()
    public ResponseEntity<List<Representatives>> findAll() {
        List<Representatives> lista = representativesRepository.findAll();
        return ResponseEntity.ok(lista);
    }     
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Representatives> representatives = representativesRepository.findById(id);
        if(representatives.isPresent()){
            return new ResponseEntity<>(representatives.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Representatives input) {
        Representatives find = representativesRepository.findById(id).get();
        if (find != null) {
            find.setAddress(input.getAddress());
            find.setAlternativeEmail(input.getAlternativeEmail());
            find.setAppointment(input.getAppointment());
            find.setArea(input.getArea());
            find.setBelongBank(input.getBelongBank());
            find.setCauseCancelation(input.getCauseCancelation());
            find.setCellPhone(input.getCellPhone());
            find.setContractRegistration(input.getContractRegistration());
            find.setCoordinacionID(input.getCoordinacionID());
            find.setCountryID(input.getCountryID());
            find.setDateActive(input.getDateActive());
            find.setDateAnnulment(input.getDateAnnulment());
            find.setDateAppointment(input.getDateAppointment());
            find.setDateApproval(input.getDateApproval());
            find.setDateBirth(input.getDateBirth());
            find.setDateNotActive(input.getDateNotActive());
            find.setDateQualification(input.getDateQualification());
            find.setDateRegistration(input.getDateRegistration());
            find.setDateSend(input.getDateSend());
            find.setDateRegistrationSenescyt(input.getDateRegistrationSenescyt());
            find.setDateSend(input.getDateSend());
            find.setEmail(input.getEmail());
            find.setState(input.getState());
            find.setGender(input.getGender());
            find.setHomeTelephone(input.getHomeTelephone());
            find.setIdentification(input.getIdentification());
            find.setInstitution(input.getInstitution());
            find.setName(input.getName());
            find.setNumQualification(input.getNumQualification());
            find.setParroquiaID(input.getParroquiaID());
            find.setProfesionalGroupID(input.getProfesionalGroupID());
            find.setReference(input.getReference());
            find.setStateRegistration(input.getStateRegistration());
            find.setTypeRepresentativeID(input.getTypeRepresentativeID());
            find.setTitle(input.getTitle());
            find.setTypeIdentification(input.getTypeIdentification());
            find.setTypePerson(input.getTypePerson());
            
        }
        Representatives obj = representativesRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Representatives input) throws BussinesRuleException, UnknownHostException{
        Representatives obj = representativesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
         Representatives find = representativesRepository.findById(id).get();
        if (find != null) {
            representativesRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
