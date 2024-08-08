package ec.gob.mdi.scsf.sites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {    
    private String provincia;      
    private String canton;
    private String parroquia;       
}
