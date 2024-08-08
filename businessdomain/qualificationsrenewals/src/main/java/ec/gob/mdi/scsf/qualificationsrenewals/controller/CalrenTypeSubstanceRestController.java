package ec.gob.mdi.scsf.qualificationsrenewals.controller;

import ec.gob.mdi.scsf.qualificationsrenewals.entities.CalrenTypeSubstance;
import ec.gob.mdi.scsf.qualificationsrenewals.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.qualificationsrenewals.repository.CalrenTypeSubstanceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "CalrenTypeSubstance API", description = "This API server all functionality for management CalrenTypeSubstance the qualification renewals")
@RestController
@RequestMapping("/calrentypesubstance")
public class CalrenTypeSubstanceRestController {

    @Autowired
    CalrenTypeSubstanceRepository calrenTypeSubstanceRepository;

    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<CalrenTypeSubstance>> findAll() {
        List<CalrenTypeSubstance> lista = calrenTypeSubstanceRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<CalrenTypeSubstance> CalrenTypeSubstance = calrenTypeSubstanceRepository.findById(id);
        if (CalrenTypeSubstance.isPresent()) {
            return new ResponseEntity<>(CalrenTypeSubstance.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody CalrenTypeSubstance input) {
        CalrenTypeSubstance find = calrenTypeSubstanceRepository.findById(id).get();
        if (find != null) {
            find.setQualificationsRenewalsID(input.getQualificationsRenewalsID());
            find.setState(input.getState());
            find.setTypeSubstanceID(input.getTypeSubstanceID());
        }
        CalrenTypeSubstance obj = calrenTypeSubstanceRepository.save(input);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CalrenTypeSubstance input) throws BussinesRuleException, UnknownHostException{
        CalrenTypeSubstance obj = calrenTypeSubstanceRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        CalrenTypeSubstance find = calrenTypeSubstanceRepository.findById(id).get();
        if (find != null) {
            calrenTypeSubstanceRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }

}
