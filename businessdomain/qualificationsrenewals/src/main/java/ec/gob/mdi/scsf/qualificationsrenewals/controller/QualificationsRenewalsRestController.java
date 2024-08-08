package ec.gob.mdi.scsf.qualificationsrenewals.controller;
import ec.gob.mdi.scsf.qualificationsrenewals.entities.QualificationsRenewals;
import ec.gob.mdi.scsf.qualificationsrenewals.exception.BussinesRuleException;
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
import ec.gob.mdi.scsf.qualificationsrenewals.repository.QualificationsRenewalsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;

@Tag(name = "QualificationsRenewals API", description = "This API server all functionality for management QualificationsRenewals")
@RestController
@RequestMapping("/v1/qualificationsrenewals")
public class QualificationsRenewalsRestController {
    
    @Autowired
    QualificationsRenewalsRepository qualificationsRenewalsRepository;
    
    @Operation(description = "Return all volumetricMeasurement", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})
    
   @GetMapping()
    public ResponseEntity<List<QualificationsRenewals>> findAll() {
        List<QualificationsRenewals> lista = qualificationsRenewalsRepository.findAll();
        return ResponseEntity.ok(lista);
    }      
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<QualificationsRenewals> QualificationsRenewals = qualificationsRenewalsRepository.findById(id);
        if(QualificationsRenewals.isPresent()){
            return new ResponseEntity<>(QualificationsRenewals.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody QualificationsRenewals input) {
       QualificationsRenewals find = qualificationsRenewalsRepository.findById(id).get();
        if (find != null) {
            find.setApproved(input.getApproved());
            find.setCompanyID(input.getCompanyID());
            find.setConclusions(input.getConclusions());
            find.setCurrentCategory(input.getCurrentCategory());
            find.setDateCaducity(input.getDateCaducity());
            find.setDatePayment(input.getDatePayment());
            find.setDatePaymentNote(input.getDatePaymentNote());
            find.setDateRegistration(input.getDateRegistration());
            find.setDateRenovation(input.getDateRenovation());
            find.setDateReport(input.getDateReport());
            find.setFileNameActa(input.getFileNameActa());
            find.setFilePathActa(input.getFilePathActa());
            find.setInvoice(input.getInvoice());
            find.setNumReport(input.getNumReport());
            find.setObservation(input.getObservation());
            find.setQuotaKgCurrent(input.getQuotaKgCurrent());
            find.setRecommendations(input.getRecommendations());
            find.setSequence(input.getSequence());
            find.setStateCalren(input.getStateCalren());
        }
        QualificationsRenewals obj = qualificationsRenewalsRepository.save(input);
        return ResponseEntity.ok(obj);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody QualificationsRenewals input) throws BussinesRuleException, UnknownHostException{
        QualificationsRenewals obj = qualificationsRenewalsRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        QualificationsRenewals find = qualificationsRenewalsRepository.findById(id).get();
        if (find != null) {
            qualificationsRenewalsRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
    
}
