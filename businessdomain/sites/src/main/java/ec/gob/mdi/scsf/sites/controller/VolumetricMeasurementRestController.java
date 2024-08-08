package ec.gob.mdi.scsf.sites.controller;

import ec.gob.mdi.scsf.sites.entities.VolumetricMeasurement;
import ec.gob.mdi.scsf.sites.exception.BussinesRuleException;
import ec.gob.mdi.scsf.sites.repository.VolumetricMeasurementRepository;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "VolumetricMeasurement API", description = "This API server all functionality for management VolumetricMeasurement the sites")
@RestController
@RequestMapping("/volumetricmeasurement")
public class VolumetricMeasurementRestController {

    @Autowired
    VolumetricMeasurementRepository volumetricMeasurementRepository;

    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<VolumetricMeasurement>> findAll() {
        List<VolumetricMeasurement> lista = volumetricMeasurementRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<VolumetricMeasurement> volumetricMeasurement = volumetricMeasurementRepository.findById(id);
        if (volumetricMeasurement.isPresent()) {
            return new ResponseEntity<>(volumetricMeasurement.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody VolumetricMeasurement input) {
        VolumetricMeasurement find = volumetricMeasurementRepository.findById(id).get();
        if (find != null) {
            find.setCalibrationAccount(input.getCalibrationAccount());
            find.setDateCertfCalibration(input.getDateCertfCalibration());
            find.setMeasuringInstrument(input.getMeasuringInstrument());
            find.setNumCertfcalibration(input.getNumCertfcalibration());
            find.setSites(input.getSites());
        }
        VolumetricMeasurement obj = volumetricMeasurementRepository.save(input);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VolumetricMeasurement input) throws BussinesRuleException, UnknownHostException {
        VolumetricMeasurement obj = volumetricMeasurementRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        VolumetricMeasurement find = volumetricMeasurementRepository.findById(id).get();
        if (find != null) {
            volumetricMeasurementRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }

}
