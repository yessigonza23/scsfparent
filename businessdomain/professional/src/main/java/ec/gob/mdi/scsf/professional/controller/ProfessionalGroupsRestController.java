package ec.gob.mdi.scsf.professional.controller;
import ec.gob.mdi.scsf.professional.entities.ProfessionalGroups;
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
import ec.gob.mdi.scsf.professional.repository.ProfessionalGroupsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "ProfessionalGroups API", description = "This API server all functionality for management ProfessionalGroups the professional")
@RestController
@RequestMapping("/professionalgroups")
public class ProfessionalGroupsRestController {
    
    @Autowired
    ProfessionalGroupsRepository professionalGroupsRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<ProfessionalGroups>> findAll() {
        List<ProfessionalGroups> lista = professionalGroupsRepository.findAll();
        return ResponseEntity.ok(lista);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<ProfessionalGroups> professionalGroups = professionalGroupsRepository.findById(id);
        if(professionalGroups.isPresent()){
            return new ResponseEntity<>(professionalGroups.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody ProfessionalGroups input) {
        ProfessionalGroups find = professionalGroupsRepository.findById(id).get();
        if (find != null) {
            find.setDescription(input.getDescription());
            find.setName(input.getName());
        }
        ProfessionalGroups obj = professionalGroupsRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProfessionalGroups input) throws BussinesRuleException, UnknownHostException{
        ProfessionalGroups obj = professionalGroupsRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        ProfessionalGroups find = professionalGroupsRepository.findById(id).get();
        if (find != null) {
            professionalGroupsRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
