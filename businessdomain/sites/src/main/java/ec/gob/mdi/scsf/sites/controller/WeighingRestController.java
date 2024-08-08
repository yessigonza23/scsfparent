package ec.gob.mdi.scsf.sites.controller;

import ec.gob.mdi.scsf.sites.entities.VolumetricMeasurement;
import ec.gob.mdi.scsf.sites.entities.Weighing;
import ec.gob.mdi.scsf.sites.exception.BussinesRuleException;
import ec.gob.mdi.scsf.sites.repository.WeighingRepository;
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

@Tag(name = "Weighing API", description = "This API server all functionality for management Weighing the sites")
@RestController
@RequestMapping("/weighing")
public class WeighingRestController {

    @Autowired
    WeighingRepository weighingRepository;

    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

    @GetMapping()
    public ResponseEntity<List<Weighing>> findAll() {
        List<Weighing> lista = weighingRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Weighing> weighing = weighingRepository.findById(id);
        if (weighing.isPresent()) {
            return new ResponseEntity<>(weighing.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Weighing input) {
        Weighing find = weighingRepository.findById(id).get();
        if (find != null) {
            find.setBrandScale(input.getBrandScale());
            find.setCapacity(input.getCapacity());
            find.setFechaCertfCalibration(input.getFechaCertfCalibration());
            find.setModel(input.getModel());
            find.setNumCertfCalibration(input.getNumCertfCalibration());
            find.setSites(input.getSites());
            find.setTolerance(input.getTolerance());
            find.setUnit(input.getUnit());
            find.setYears(input.getYears());
        }
        Weighing obj = weighingRepository.save(input);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Weighing input) throws BussinesRuleException, UnknownHostException {
        Weighing obj = weighingRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Weighing find = weighingRepository.findById(id).get();
        if (find != null) {
            weighingRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }

}
