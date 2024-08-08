package ec.gob.mdi.scsf.calrenwarehouse.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "calrenactividadescalificacionclientes",schema = "control")
public class CalrenActCalCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
        @Column(name = "id_calrenactividadescalificacion", nullable = false)
	private Integer calrenQualificationActivitiesID;
	
        @Column(name = "id_calificacionesrenovaciones")
	private Integer qualificationRenewalsID;

}
