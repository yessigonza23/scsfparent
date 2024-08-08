package ec.gob.mdi.scsf.sites.controller;

import ec.gob.mdi.scsf.sites.dto.ResponseDTO;
import ec.gob.mdi.scsf.sites.entities.Sites;
import ec.gob.mdi.scsf.sites.exception.BussinesRuleException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ec.gob.mdi.scsf.sites.repository.SitesRepository;
import ec.gob.mdi.scsf.sites.services.ISitesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.UnknownHostException;
import java.util.Optional;
import org.springframework.core.env.Environment;

@Tag(name = "Site API", description = "This API server all functionality for management Sites the company")
@RestController
@RequestMapping("/sites")
public class SitesRestController {

    @Autowired
    private Environment env;

    @GetMapping("/check")
    public String check() {
        return "Hello your property value is: " + env.getProperty("custom.activeprofileName");
    }
    
    @Autowired
    ISitesService sitesService;

    @Autowired
    SitesRepository sitesRepository;

    @Operation(description = "Return all sites", summary = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exito"),
        @ApiResponse(responseCode = "500", description = "Internal error")})    
    
    @GetMapping()
    public ResponseEntity<List<Sites>> findAll(){
        List<Sites> lista = sitesRepository.findAll();
        return  ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Sites> sites = sitesRepository.findById(id);
        if(sites.isPresent()){
            return new ResponseEntity<>(sites.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") long id, @RequestBody Sites input) {
        Sites find = sitesRepository.findById(id).get();
        if (find != null) {
            find.setAlarms(input.getAlarms());
            find.setBranch(input.getBranch());
            find.setCellarVolume(input.getCellarVolume());
            find.setClosedCircuit(input.getClosedCircuit());
            find.setClosing(input.getClosing());
            find.setCommercialName(input.getCommercialName());
            find.setContainmentDike(input.getContainmentDike());
            find.setContingencyPlan(input.getContingencyPlan());
            find.setControlEntryManual(input.getControlEntryManual());
            find.setControlEntryComputarized(input.getControlEntryComputarized());
            find.setElectricFence(input.getElectricFence());
            find.setEmergencyShower(input.getEmergencyShower());
            find.setEyewash(input.getEyewash());
            find.setFileName(input.getFileName());
            find.setFileNameSecurity(input.getFileNameSecurity());
            find.setFilePath(input.getFilePath());
            find.setFilePathSegurity(input.getFilePathSegurity());
            find.setFireFightingSystem(input.getFireFightingSystem());
            find.setFirePermit(input.getFirePermit());
            find.setGuardianship(input.getGuardianship());
            find.setIncreasedActivity(input.getIncreasedActivity());
            find.setInstallation(input.getInstallation());
            find.setIntersection(input.getIntersection());
            find.setKardex(input.getKardex());
            find.setLatitude(input.getLatitude());
            find.setLongitude(input.getLongitude());
            find.setLog(input.getLog());
            find.setMotionSensors(input.getMotionSensors());
            find.setNumEstablishment(input.getNumEstablishment());
            find.setNumExtension(input.getNumExtension());
            find.setNumFirePermit(input.getNumFirePermit());
            find.setNumber(input.getNumber());
            find.setOffice(input.getOffice());
            find.setOther(input.getOther());
            find.setOtherComputarized(input.getOtherComputarized());
            find.setOtherDescription(input.getOtherComputarized());
            find.setParroquiaID(input.getParroquiaID());
            find.setPhone(input.getPhone());
            find.setPlant(input.getPlant());
            find.setProtectiveEquipment(input.getProtectiveEquipment());
            find.setQualificationRenewalsID(input.getQualificationRenewalsID());
            find.setSafetyDataSheets(input.getSafetyDataSheets());
            find.setSpillKit(input.getSpillKit());
            find.setStreet(input.getStreet());
            find.setTypeEstablishment(input.getTypeEstablishment());
            find.setValidityfirePermit(input.getValidityfirePermit());
            find.setWarehouseCapacity(input.getWarehouseCapacity());
            find.setWineCellarArea(input.getWineCellarArea());
            find.setWinecellar(input.getWinecellar());
        }
        Sites obj = sitesRepository.save(input);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Sites input) throws BussinesRuleException, UnknownHostException {
        Sites obj = sitesRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Sites find = sitesRepository.findById(id).get();
        if (find != null) {
            sitesRepository.delete(find);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findById/{id}")
    public ResponseDTO getSiteInspectionById(@PathVariable(name = "id") long id) {
        return sitesService.getSitesById(id);
    }
}
