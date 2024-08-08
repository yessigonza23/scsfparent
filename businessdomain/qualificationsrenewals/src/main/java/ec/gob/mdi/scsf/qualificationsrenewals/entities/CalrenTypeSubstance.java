package ec.gob.mdi.scsf.qualificationsrenewals.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "calrentiposustancia",schema = "control")
public class CalrenTypeSubstance {    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_calificacionesRenovaciones")
	private QualificationsRenewals qualificationsRenewalsID;
		
	//@ManyToOne
	//@JoinColumn(name = "id_tiposustancia")
        @Column(name = "id_tiposustancia")
	private Integer typeSubstanceID;
	
	@Column(name = "estado")
	private String state;

}
