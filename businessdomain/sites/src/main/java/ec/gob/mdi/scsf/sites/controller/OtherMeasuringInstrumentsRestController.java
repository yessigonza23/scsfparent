package ec.gob.mdi.scsf.sites.controller;

import ec.gob.mdi.scsf.sites.entities.OtherMeasuringInstruments;
import ec.gob.mdi.scsf.sites.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.sites.repository.OtherMeasuringInstrumentsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "OtherMeasuringInstruments API", description = "This API server all functionality for management OtherMeasuringInstruments the sites")
@RestController
@RequestMapping("/othermeasuringinstruments")
public class OtherMeasuringInstrumentsRestController {

    @Autowired
    OtherMeasuringInstrumentsRepository otherMeasuringInstrumentsRepository;

    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<OtherMeasuringInstruments>> findAll() {
        List<OtherMeasuringInstruments> lista = otherMeasuringInstrumentsRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<OtherMeasuringInstruments> otherMeasuringInstruments = otherMeasuringInstrumentsRepository.findById(id);
        if (otherMeasuringInstruments.isPresent()) {
            return new ResponseEntity<>(otherMeasuringInstruments.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody OtherMeasuringInstruments input) {
        OtherMeasuringInstruments find = otherMeasuringInstrumentsRepository.findById(id).get();
        if (find != null) {
            find.setDescription(input.getDescription());
            find.setSites(input.getSites());
        }
        OtherMeasuringInstruments obj = otherMeasuringInstrumentsRepository.save(input);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OtherMeasuringInstruments input) throws BussinesRuleException, UnknownHostException {
        OtherMeasuringInstruments obj = otherMeasuringInstrumentsRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        OtherMeasuringInstruments find = otherMeasuringInstrumentsRepository.findById(id).get();
        if (find != null) {
            otherMeasuringInstrumentsRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }

}
