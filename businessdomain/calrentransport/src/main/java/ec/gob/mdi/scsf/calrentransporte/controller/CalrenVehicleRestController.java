package ec.gob.mdi.scsf.calrentransporte.controller;

import ec.gob.mdi.scsf.calrentransporte.entities.CalrenVehicle;
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
import ec.gob.mdi.scsf.calrentransporte.repository.CalrenVehicleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenVehicle API", description = "This API server all functionality for management CalrenVehicle the qualification renewals")
@RestController
@RequestMapping("/calrenvehicle")
public class CalrenVehicleRestController {
    
    @Autowired
    CalrenVehicleRepository calrenVehicleRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<CalrenVehicle>> findAll() {
        List<CalrenVehicle> lista = calrenVehicleRepository.findAll();
        return ResponseEntity.ok(lista);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenVehicle> calrenVehicle = calrenVehicleRepository.findById(id);
        if(calrenVehicle.isPresent()){
            return new ResponseEntity<>(calrenVehicle.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenVehicle input) {
        CalrenVehicle find = calrenVehicleRepository.findById(id).get();
        if (find != null) {
            find.setCertificateSae(input.getCertificateSae());
            find.setDateActivation(input.getDateActivation());
            find.setDateCaducity(input.getDateCaducity());
            find.setDateDeactivation(input.getDateDeactivation());
            find.setDateInspection(input.getDateInspection());
            find.setFileNamePdf(input.getFileNamePdf());
            find.setInvoice(input.getInvoice());
            find.setKeyTracking(input.getKeyTracking());
            find.setNAutoritationOthers(input.getNNumAutoritation());
            find.setQualificationRenewalsID(input.getQualificationRenewalsID());
            find.setReport(input.getReport());
            find.setRoutePdf(input.getRoutePdf());
            find.setState(input.getState());
            find.setTrackingLink(input.getTrackingLink());
            find.setTypeChangeID(input.getTypeChangeID());
            find.setTypeSubstance(input.getTypeSubstance());
        }
        CalrenVehicle obj = calrenVehicleRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenVehicle input) throws BussinesRuleException, UnknownHostException{
        CalrenVehicle obj = calrenVehicleRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenVehicle find = calrenVehicleRepository.findById(id).get();
        if (find != null) {
            calrenVehicleRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
