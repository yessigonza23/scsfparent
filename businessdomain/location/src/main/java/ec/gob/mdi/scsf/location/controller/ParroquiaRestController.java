package ec.gob.mdi.scsf.location.controller;

import ec.gob.mdi.scsf.location.dto.LocationDTO;
import ec.gob.mdi.scsf.location.entities.Parroquia;
import ec.gob.mdi.scsf.location.exception.BussinesRuleException;
import ec.gob.mdi.scsf.location.repository.ParroquiaRepository;
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

@Tag(name = "Parroquia API", description = "This API server all functionality for management Parroquia the location")
@RestController
@RequestMapping("/parroquia")
public class ParroquiaRestController {

    @Autowired
    ParroquiaRepository parroquiaRepository;

    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Parroquia>> findAll() {
        List<Parroquia> lista = parroquiaRepository.findAll();
        return ResponseEntity.ok(lista);
    }   

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Parroquia> parroquia = parroquiaRepository.findById(id);
        if (parroquia.isPresent()) {
            return new ResponseEntity<>(parroquia.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Parroquia input) {
        Parroquia find = parroquiaRepository.findById(id).get();
        if (find != null) {
            find.setCode(input.getCode());
            find.setIdCanton(input.getIdCanton());
            find.setName(input.getName());
        }
        Parroquia obj = parroquiaRepository.save(input);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Parroquia input) throws BussinesRuleException, UnknownHostException{
        Parroquia obj = parroquiaRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Parroquia find = parroquiaRepository.findById(id).get();
        if (find != null) {
            parroquiaRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("location/{idParroquia}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable(name = "idParroquia") Integer idParroquia) throws Exception {
        LocationDTO location = parroquiaRepository.searchByIdParroquia(idParroquia);
        return new ResponseEntity<>(location, HttpStatus.OK);

    }
}
