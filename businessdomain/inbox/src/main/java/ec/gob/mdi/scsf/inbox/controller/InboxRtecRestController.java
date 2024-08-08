package ec.gob.mdi.scsf.inbox.controller;
import ec.gob.mdi.scsf.inbox.entities.InboxRtec;
import ec.gob.mdi.scsf.inbox.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.inbox.repository.InboxRtecRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Tag(name = "InboxRtec API", description = "This API server all functionality for management InboxRtec the Inbox")
@RestController
@RequestMapping("/inboxrtec")
public class InboxRtecRestController {
    
    @Autowired
    InboxRtecRepository inboxRtecRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<InboxRtec>> findAll() {
        List<InboxRtec> lista = inboxRtecRepository.findAll();
        return ResponseEntity.ok(lista);
    }     
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<InboxRtec> inboxRtec = inboxRtecRepository.findById(id);
        if(inboxRtec.isPresent()){
            return new ResponseEntity<>(inboxRtec.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody InboxRtec input) {
        InboxRtec find = inboxRtecRepository.findById(id).get();
        if (find != null) {
            find.setDate(input.getDate());
            find.setDateExpiration(input.getDateExpiration());
            find.setInboxStatusCatalogue(input.getInboxStatusCatalogue());
            find.setInboxTypeProcess(input.getInboxTypeProcess());
            find.setNumProcess(input.getNumProcess());
            find.setObservation(input.getObservation());
            find.setRepresentativesQualificationRenovationID(input.getRepresentativesQualificationRenovationID());
            find.setSee(input.getSee());
            find.setUserID(input.getUserID());
        }
        InboxRtec obj = inboxRtecRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody InboxRtec input) throws BussinesRuleException, UnknownHostException{
        InboxRtec obj = inboxRtecRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        InboxRtec find = inboxRtecRepository.findById(id).get();
        if (find != null) {
            inboxRtecRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
