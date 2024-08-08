package ec.gob.mdi.scsf.professional.controller;
import ec.gob.mdi.scsf.professional.entities.ProfessionalGroupsCiiu;
import ec.gob.mdi.scsf.professional.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.professional.repository.ProfessionalGroupsCiiuRepository;
import java.net.UnknownHostException;

@Tag(name = "ProfessionalGroupsCiuu API", description = "This API server all functionality for management ProfessionalGroupsCiuu the professional")
@RestController
@RequestMapping("/professionalgroupsciuu")
public class ProfessionalGroupsCiiuRestController {

    @Autowired
    ProfessionalGroupsCiiuRepository professionalGroupsCiuuRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<ProfessionalGroupsCiiu>> findAll() {
        List<ProfessionalGroupsCiiu> lista = professionalGroupsCiuuRepository.findAll();
        return ResponseEntity.ok(lista);
    }   
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<ProfessionalGroupsCiiu> professionalGroupsCiuu = professionalGroupsCiuuRepository.findById(id);
        if(professionalGroupsCiuu.isPresent()){
            return new ResponseEntity<>(professionalGroupsCiuu.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody ProfessionalGroupsCiiu input) {
        ProfessionalGroupsCiiu find = professionalGroupsCiuuRepository.findById(id).get();
        if (find != null) {
            find.setCiiu(input.getCiiu());
            find.setProfessionalGroups(input.getProfessionalGroups());
        }
        ProfessionalGroupsCiiu obj = professionalGroupsCiuuRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProfessionalGroupsCiiu input) throws BussinesRuleException, UnknownHostException{
        ProfessionalGroupsCiiu obj = professionalGroupsCiuuRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        ProfessionalGroupsCiiu find = professionalGroupsCiuuRepository.findById(id).get();
        if (find != null) {
            professionalGroupsCiuuRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
