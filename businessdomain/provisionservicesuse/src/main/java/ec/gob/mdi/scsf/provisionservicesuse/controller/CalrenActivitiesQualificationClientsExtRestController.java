package ec.gob.mdi.scsf.provisionservicesuse.controller;

import ec.gob.mdi.scsf.provisionservicesuse.entities.CalrenActivitiesQualificationClientsExt;
import ec.gob.mdi.scsf.provisionservicesuse.exception.BussinesRuleException;
import ec.gob.mdi.scsf.provisionservicesuse.repository.CalrenActivitiesQualificationClientsExtRepository;
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

@Tag(name = "CalrenActivitiesQualificationClientsExt API", description = "This API server all functionality for management CalrenActivitiesQualificationClientsExt the qualification renewals")
@RestController
@RequestMapping("/calrenactivitiesqualificationclientsext")
public class CalrenActivitiesQualificationClientsExtRestController {
    
    @Autowired
    CalrenActivitiesQualificationClientsExtRepository calrenActivitiesQualificationClientsExtRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<CalrenActivitiesQualificationClientsExt>> findAll() {
        List<CalrenActivitiesQualificationClientsExt> lista = calrenActivitiesQualificationClientsExtRepository.findAll();
        return ResponseEntity.ok(lista);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenActivitiesQualificationClientsExt> calrenActivitiesQualificationClientsExt = calrenActivitiesQualificationClientsExtRepository.findById(id);
        if(calrenActivitiesQualificationClientsExt.isPresent()){
            return new ResponseEntity<>(calrenActivitiesQualificationClientsExt.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenActivitiesQualificationClientsExt input) {
        CalrenActivitiesQualificationClientsExt find = calrenActivitiesQualificationClientsExtRepository.findById(id).get();
        if (find != null) {
            find.setCalrenActivitiesQualificationID(input.getCalrenActivitiesQualificationID());
            find.setClientes(input.getClientes());
        }
        CalrenActivitiesQualificationClientsExt obj = calrenActivitiesQualificationClientsExtRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenActivitiesQualificationClientsExt input) throws BussinesRuleException, UnknownHostException{
        CalrenActivitiesQualificationClientsExt obj = calrenActivitiesQualificationClientsExtRepository.save(input);
       return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenActivitiesQualificationClientsExt find = calrenActivitiesQualificationClientsExtRepository.findById(id).get();
        if (find != null) {
            calrenActivitiesQualificationClientsExtRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
