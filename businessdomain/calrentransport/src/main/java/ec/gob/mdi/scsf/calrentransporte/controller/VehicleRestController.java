
package ec.gob.mdi.scsf.calrentransporte.controller;

import ec.gob.mdi.scsf.calrentransporte.entities.Vehicle;
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
import ec.gob.mdi.scsf.calrentransporte.repository.VehicleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "Vehicle API", description = "This API server all functionality for management Vehicle the qualification renewals")
@RestController
@RequestMapping("/vehicle")
public class VehicleRestController {
    
    @Autowired
    VehicleRepository vehicleRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Vehicle>> findAll() {
        List<Vehicle> lista = vehicleRepository.findAll();
        return ResponseEntity.ok(lista);
    }        
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isPresent()){
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Vehicle input) {
        Vehicle find = vehicleRepository.findById(id).get();
        if (find != null) {
            find.setBrand(input.getBrand());
            find.setColour(input.getColour());
            find.setCountryID(input.getCountryID());
            find.setLoadCapacity(input.getLoadCapacity());
            find.setModel(input.getModel());
            find.setNumChassisSeries(input.getNumChassisSeries());
            find.setNumEngine(input.getNumEngine());
            find.setOrigin(input.getOrigin());
            find.setOwner(input.getOwner());
            find.setPlaque(input.getPlaque());
            find.setPlaqueAdditional(input.getPlaqueAdditional());
            find.setTypeChangeID(input.getTypeChangeID());
            find.setVehicleType(input.getVehicleType());
            find.setYearManufacture(input.getYearManufacture());
        }
        Vehicle obj = vehicleRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Vehicle input) throws BussinesRuleException, UnknownHostException{
        Vehicle obj = vehicleRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Vehicle find = vehicleRepository.findById(id).get();
        if (find != null) {
            vehicleRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
