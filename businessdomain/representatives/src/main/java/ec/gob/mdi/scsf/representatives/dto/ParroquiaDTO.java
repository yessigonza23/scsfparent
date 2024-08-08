package ec.gob.mdi.scsf.representatives.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParroquiaDTO {
    private Integer id;  
    private String code;
    private String name;
    private Integer idCanton;   
}
