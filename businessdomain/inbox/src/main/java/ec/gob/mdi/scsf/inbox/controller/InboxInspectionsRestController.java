package ec.gob.mdi.scsf.inbox.controller;
import ec.gob.mdi.scsf.inbox.entities.InboxInspections;
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
import ec.gob.mdi.scsf.inbox.repository.InboxInspectionsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "InboxInspections API", description = "This API server all functionality for management InboxInspections the Inbox")
@RestController
@RequestMapping("/inboxinspections")
public class InboxInspectionsRestController {
    
    @Autowired
    InboxInspectionsRepository inboxInspectionsRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<InboxInspections>> findAll() {
        List<InboxInspections> lista = inboxInspectionsRepository.findAll();
        return ResponseEntity.ok(lista);
    }     
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<InboxInspections> inboxInspections = inboxInspectionsRepository.findById(id);
        if(inboxInspections.isPresent()){
            return new ResponseEntity<>(inboxInspections.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody InboxInspections input) {
        InboxInspections find = inboxInspectionsRepository.findById(id).get();
        if (find != null) {
            find.setDate(input.getDate());
            find.setDateExpiration(input.getDateExpiration());
            find.setInboxStatusCatalogue(input.getInboxStatusCatalogue());
            find.setInboxTypeProcessID(input.getInboxTypeProcessID());
            find.setObservation(input.getObservation());
            find.setSee(input.getSee());
            find.setSitesID(input.getSitesID());
            find.setUserID(input.getUserID());
        }
        InboxInspections obj = inboxInspectionsRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody InboxInspections input) throws BussinesRuleException, UnknownHostException{
        InboxInspections obj = inboxInspectionsRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        InboxInspections find = inboxInspectionsRepository.findById(id).get();
        if (find != null) {
            inboxInspectionsRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
