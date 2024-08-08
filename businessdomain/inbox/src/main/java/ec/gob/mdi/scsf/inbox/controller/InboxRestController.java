package ec.gob.mdi.scsf.inbox.controller;
import ec.gob.mdi.scsf.inbox.entities.Inbox;
import ec.gob.mdi.scsf.inbox.entities.InboxStatusCatalogue;
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
import ec.gob.mdi.scsf.inbox.repository.InboxRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "Inbox API", description = "This API server all functionality for management Inbox")
@RestController
@RequestMapping("/inbox")
public class InboxRestController {
    
    @Autowired
    InboxRepository inboxRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<Inbox>> findAll() {
        List<Inbox> lista = inboxRepository.findAll();
        return ResponseEntity.ok(lista);
    }     
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Inbox> inbox = inboxRepository.findById(id);
        if(inbox.isPresent()){
            return new ResponseEntity<>(inbox.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Inbox input) {
        Inbox find = inboxRepository.findById(id).get();
        if (find != null) {
            find.setDate(input.getDate());
            find.setDateExpiration(input.getDateExpiration());
            find.setInboxStatusCatalogue(input.getInboxStatusCatalogue());
            find.setInboxTypeProcess(input.getInboxTypeProcess());
            find.setNumProcess(input.getNumProcess());
            find.setObservation(input.getObservation());
            find.setQualificationRenewalsID(input.getQualificationRenewalsID());
            find.setSee(input.getSee());
            find.setUser(input.getUser());
            
        }
        Inbox obj = inboxRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Inbox input) throws BussinesRuleException, UnknownHostException{
        Inbox obj = inboxRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Inbox find = inboxRepository.findById(id).get();
        if (find != null) {
            inboxRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
