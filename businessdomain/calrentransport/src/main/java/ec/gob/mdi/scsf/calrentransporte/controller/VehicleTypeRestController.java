
package ec.gob.mdi.scsf.calrentransporte.controller;

import ec.gob.mdi.scsf.calrentransporte.entities.VehicleType;
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
import ec.gob.mdi.scsf.calrentransporte.repository.VehicleTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "VehicleType API", description = "This API server all functionality for management VehicleType the qualification renewals")
@RestController
@RequestMapping("/vehicleTypetype")
public class VehicleTypeRestController {
    
    @Autowired
    VehicleTypeRepository vehicleTypeRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<VehicleType>> findAll() {
        List<VehicleType> lista = vehicleTypeRepository.findAll();
        return ResponseEntity.ok(lista);
    }       
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
        if(vehicleType.isPresent()){
            return new ResponseEntity<>(vehicleType.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody VehicleType input) {
        VehicleType find = vehicleTypeRepository.findById(id).get();
        if (find != null) {
            find.setDescription(input.getDescription());
            find.setName(input.getName());
            find.setPlaqueAdditional(input.getPlaqueAdditional());
        }
        VehicleType obj = vehicleTypeRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody VehicleType input) throws BussinesRuleException, UnknownHostException{
        VehicleType obj = vehicleTypeRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        VehicleType find = vehicleTypeRepository.findById(id).get();
        if (find != null) {
            vehicleTypeRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
