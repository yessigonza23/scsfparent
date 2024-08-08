package ec.gob.mdi.scsf.calrentransporte.controller;

import ec.gob.mdi.scsf.calrentransporte.entities.VehicleAutoritation;
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
import ec.gob.mdi.scsf.calrentransporte.repository.VehicleAutoritationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "VehiculoAutoritation API", description = "This API server all functionality for management VehiculoAutoritation the qualification renewals")
@RestController
@RequestMapping("/vehicleautoritation")
public class VehiculoAutoritationRestController {
    
    @Autowired
    VehicleAutoritationRepository vehicleAutoritationRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<VehicleAutoritation>> findAll() {
        List<VehicleAutoritation> lista = vehicleAutoritationRepository.findAll();
        return ResponseEntity.ok(lista);
    }       
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<VehicleAutoritation> vehicleAutoritation = vehicleAutoritationRepository.findById(id);
        if(vehicleAutoritation.isPresent()){
            return new ResponseEntity<>(vehicleAutoritation.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody VehicleAutoritation input) {
        VehicleAutoritation find = vehicleAutoritationRepository.findById(id).get();
        if (find != null) {
            find.setAcronyms(input.getAcronyms());
            find.setName(input.getName());
            find.setTypeVehicle(input.getTypeVehicle());
        }
        VehicleAutoritation obj = vehicleAutoritationRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody VehicleAutoritation input) throws BussinesRuleException, UnknownHostException{
        VehicleAutoritation obj = vehicleAutoritationRepository.save(input);
       return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        VehicleAutoritation find = vehicleAutoritationRepository.findById(id).get();
        if (find != null) {
            vehicleAutoritationRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
