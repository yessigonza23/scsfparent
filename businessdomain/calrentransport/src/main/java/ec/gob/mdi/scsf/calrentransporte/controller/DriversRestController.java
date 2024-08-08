
package ec.gob.mdi.scsf.calrentransporte.controller;

import ec.gob.mdi.scsf.calrentransporte.entities.Drivers;
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
import ec.gob.mdi.scsf.calrentransporte.repository.DriversRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "Drivers API", description = "This API server all functionality for management Drivers the qualification renewals")
@RestController
@RequestMapping("/drivers")
public class DriversRestController {
    
    @Autowired
    DriversRepository driversRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Drivers>> findAll() {
        List<Drivers> lista = driversRepository.findAll();
        return ResponseEntity.ok(lista);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Drivers> drivers = driversRepository.findById(id);
        if(drivers.isPresent()){
            return new ResponseEntity<>(drivers.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Drivers input) {
        Drivers find = driversRepository.findById(id).get();
        if (find != null) {
            find.setCellPhone(input.getCellPhone());
            find.setCountryID(input.getCountryID());
            find.setDateValidity(input.getDateValidity());
            find.setIdentification(input.getIdentification());
            find.setName(input.getName());
            find.setNamePhoto(input.getNamePhoto());
            find.setRoutePhoto(input.getRoutePhoto());
            find.setTypeDocument(input.getTypeDocument());
            find.setTypeLiceneExt(input.getTypeLiceneExt());
            find.setTypeLicense(input.getTypeLicense());
        }
        Drivers obj = driversRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Drivers input) throws BussinesRuleException, UnknownHostException{
        Drivers obj = driversRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Drivers find = driversRepository.findById(id).get();
        if (find != null) {
            driversRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
