package ec.gob.mdi.scsf.inbox.controller;
import ec.gob.mdi.scsf.inbox.entities.InboxTypeProcess;
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
import ec.gob.mdi.scsf.inbox.repository.InboxTypeProcessRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "InboxTypeProcess API", description = "This API server all functionality for management InboxTypeProcess the Inbox")
@RestController
@RequestMapping("/inboxtypeprocess")
public class InboxTypeProcessRestController {
    
    @Autowired
    InboxTypeProcessRepository inboxTypeProcessRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<InboxTypeProcess>> findAll() {
        List<InboxTypeProcess> lista = inboxTypeProcessRepository.findAll();
        return ResponseEntity.ok(lista);
    }   
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<InboxTypeProcess> inboxTypeProcess = inboxTypeProcessRepository.findById(id);
        if(inboxTypeProcess.isPresent()){
            return new ResponseEntity<>(inboxTypeProcess.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody InboxTypeProcess input) {
        InboxTypeProcess find = inboxTypeProcessRepository.findById(id).get();
        if (find != null) {
            find.setAcronyms(input.getAcronyms());
            find.setDescription(input.getDescription());
            find.setShortDescription(input.getShortDescription());
            find.setState(input.getState());
            find.setValue(input.getValue());
        }
        InboxTypeProcess obj = inboxTypeProcessRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody InboxTypeProcess input) throws BussinesRuleException, UnknownHostException{
        InboxTypeProcess obj = inboxTypeProcessRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        InboxTypeProcess find = inboxTypeProcessRepository.findById(id).get();
        if (find != null) {
            inboxTypeProcessRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
