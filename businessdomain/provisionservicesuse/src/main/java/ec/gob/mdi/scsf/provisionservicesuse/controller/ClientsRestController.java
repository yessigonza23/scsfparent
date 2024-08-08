package ec.gob.mdi.scsf.provisionservicesuse.controller;

import ec.gob.mdi.scsf.provisionservicesuse.entities.Clients;
import ec.gob.mdi.scsf.provisionservicesuse.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.provisionservicesuse.repository.ClientsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "Clients API", description = "This API server all functionality for management Clients the qualification renewals")
@RestController
@RequestMapping("/clients")
public class ClientsRestController {
    
    @Autowired
    ClientsRepository clientsRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})

   @GetMapping()
    public ResponseEntity<List<Clients>> findAll() {
        List<Clients> lista = clientsRepository.findAll();
        return ResponseEntity.ok(lista);
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Clients> clients = clientsRepository.findById(id);
        if(clients.isPresent()){
            return new ResponseEntity<>(clients.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Clients input) {
        Clients find = clientsRepository.findById(id).get();
        if (find != null) {
            find.setName(input.getName());
            find.setRuc(input.getRuc());
        }
        Clients obj = clientsRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Clients input) throws BussinesRuleException, UnknownHostException{
        Clients obj = clientsRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Clients find = clientsRepository.findById(id).get();
        if (find != null) {
            clientsRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
