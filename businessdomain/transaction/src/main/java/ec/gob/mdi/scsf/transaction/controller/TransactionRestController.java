/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.transaction.controller;

import ec.gob.mdi.scsf.transaction.entities.RecordTransaction;
import ec.gob.mdi.scsf.transaction.exception.BussinesRuleException;
import ec.gob.mdi.scsf.transaction.repository.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Alvear
 */
@Tag(name = "Transaction API", description = "This API server all functionality for management Transaction")
@RestController
@RequestMapping("/v1/transaction")
public class TransactionRestController {
    
    @Autowired
    TransactionRepository transactionRepository;
    
    @Autowired
    private Environment env;
    
    @GetMapping("/check")
    public String check() {
        return "Config Server. Your property value is: "+ env.getProperty("custom.activeprofileName");
    }
    
    @Operation(description = "Return all transactions", summary ="Return 204 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping
    public ResponseEntity<List<RecordTransaction>> list() {
        List<RecordTransaction> list = transactionRepository.findAll();
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }
    
    /**
     * Segun la documentación
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<RecordTransaction> obj = transactionRepository.findById(id);
        if(obj.isPresent()){
            //return new ResponseEntity<>(category.get(), HttpStatus.OK);
            return ResponseEntity.ok(obj);
        }else{
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody RecordTransaction input) {
        RecordTransaction find = transactionRepository.findById(id).get();
        if (find != null) {
            find.setBillNumber(input.getBillNumber());
            find.setDate(input.getDate());
            find.setExpenditureAmount(input.getExpenditureAmount());
            find.setIdBowl(input.getIdBowl());
            find.setIdProduct(input.getIdProduct());
            find.setIdQualification(input.getIdQualification());
            find.setIdSiteAuthorized(input.getIdSiteAuthorized());
            find.setIdSubstance(input.getIdSubstance());
            find.setIdTrasaction(input.getIdTrasaction());
            find.setIdTypeBowl(input.getIdTypeBowl());
            find.setIncomeAmount(input.getIncomeAmount());
            find.setInitialBalance(input.getInitialBalance());
            find.setNumberLote(input.getNumberLote());
            find.setProductionOrder(input.getProductionOrder());
            find.setQuantityProduced(input.getQuantityProduced());
            find.setStatus(input.getStatus());
        }
        RecordTransaction save = transactionRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody RecordTransaction input) throws BussinesRuleException, UnknownHostException{
        RecordTransaction save = transactionRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        RecordTransaction find = transactionRepository.findById(id).get();
        if(find != null){
            transactionRepository.delete(find);
        }
        return ResponseEntity.ok().build();
    }
    
    //Metodo qye consume otro Api
    /*@GetMapping("/findById/{id}")
    public ResponseDTO getRecordTransactionById(@PathVariable(name = "id") long id) {
        return siteService.getSiteById(id);
    }*/
}
