package ec.gob.mdi.scsf.inbox.controller;
import ec.gob.mdi.scsf.inbox.entities.InboxStatusCatalogue;
import ec.gob.mdi.scsf.inbox.exception.BussinesRuleException;
import ec.gob.mdi.scsf.inbox.repository.InboxStatusCatalogueRepository;
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

@Tag(name = "InboxStatusCatalogue API", description = "This API server all functionality for management InboxStatusCatalogue the Inbox")
@RestController
@RequestMapping("/inboxstatuscatalogue")
public class InboxStatusCatalogueRestController {

    @Autowired
    InboxStatusCatalogueRepository inboxStatusCatalogueRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
    @GetMapping()
    public ResponseEntity<List<InboxStatusCatalogue>> findAll() {
        List<InboxStatusCatalogue> lista = inboxStatusCatalogueRepository.findAll();
        return ResponseEntity.ok(lista);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<InboxStatusCatalogue> inboxstatuscatalogue = inboxStatusCatalogueRepository.findById(id);
        if(inboxstatuscatalogue.isPresent()){
            return new ResponseEntity<>(inboxstatuscatalogue.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody InboxStatusCatalogue input) {
        InboxStatusCatalogue find = inboxStatusCatalogueRepository.findById(id).get();
        if (find != null) {
            find.setAcronyms(input.getAcronyms());
            find.setDescription(input.getDescription());
            find.setState(input.getState());
        }
        InboxStatusCatalogue obj = inboxStatusCatalogueRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody InboxStatusCatalogue input) throws BussinesRuleException, UnknownHostException{
        InboxStatusCatalogue obj = inboxStatusCatalogueRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        InboxStatusCatalogue find = inboxStatusCatalogueRepository.findById(id).get();
        if (find != null) {
            inboxStatusCatalogueRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
