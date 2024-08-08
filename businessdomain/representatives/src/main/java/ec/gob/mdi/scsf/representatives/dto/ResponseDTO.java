package ec.gob.mdi.scsf.representatives.dto;

import ec.gob.mdi.scsf.representatives.entities.Representatives;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    
    private Representatives representatives;
    private ParroquiaDTO parroquia;
}
