package ec.gob.mdi.scsf.sites.dto;

import ec.gob.mdi.scsf.sites.entities.Sites;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {    
    private Sites sites;
    private LocationDTO location;
    private QualificationsRenewalsDTO qualificationsRenewalsDTO;
    
}
