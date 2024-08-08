package ec.gob.mdi.scsf.calrentransporte.controller;

import ec.gob.mdi.scsf.calrentransporte.entities.CalrenDrivers;
import ec.gob.mdi.scsf.calrentransporte.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.calrentransporte.repository.CalrenDriversRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenDrivers API", description = "This API server all functionality for management CalrenDrivers the qualification renewals")
@RestController
@RequestMapping("/calrendrivers")
public class CalrenDriversRestController {
    
    @Autowired
    CalrenDriversRepository calrenDriversRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List< CalrenDrivers>> findAll() {
        List< CalrenDrivers> lista = calrenDriversRepository.findAll();
        return ResponseEntity.ok(lista);
    }       
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenDrivers> calrenDrivers = calrenDriversRepository.findById(id);
        if(calrenDrivers.isPresent()){
            return new ResponseEntity<>(calrenDrivers.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenDrivers input) {
        CalrenDrivers find = calrenDriversRepository.findById(id).get();
        if (find != null) {
            find.setDateActivation(input.getDateActivation());
            find.setDateExpeditionCrewman(input.getDateExpeditionCrewman());
            find.setDateTraining(input.getDateTraining());
            find.setDateValidityTraining(input.getDateValidityTraining());
            find.setDateDesactivation(input.getDateDesactivation());
            find.setDrivers(input.getDrivers());
            find.setFileNamePdf(input.getFileNamePdf());
            find.setFilePath(input.getFilePath());
            find.setHeavyTransport(input.getHeavyTransport());
            find.setNumCertificate(input.getNumCertificate());
            find.setNumCrewNotebook(input.getNumCrewNotebook());
            find.setPlaceDateExpeditionCrewman(input.getPlaceDateExpeditionCrewman());
            find.setQualificationRenewalsID(input.getQualificationRenewalsID());
            find.setRoutePdf(input.getRoutePdf());
            find.setState(input.getState());
            find.setTypeChangeID(input.getTypeChangeID());
            find.setTypeSubstance(input.getTypeSubstance());
        }
        CalrenDrivers obj = calrenDriversRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenDrivers input) throws BussinesRuleException, UnknownHostException{
        CalrenDrivers obj = calrenDriversRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenDrivers find = calrenDriversRepository.findById(id).get();
        if (find != null) {
            calrenDriversRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
