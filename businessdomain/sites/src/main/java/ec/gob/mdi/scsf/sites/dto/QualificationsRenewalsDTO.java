package ec.gob.mdi.scsf.sites.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QualificationsRenewalsDTO{	
	private Integer id;
	private Integer idCompany;
	private Integer sequence;
	private LocalDateTime dateRenovation;
	private LocalDateTime dateCaducity;
	private LocalDateTime dateRegistration;	
}
