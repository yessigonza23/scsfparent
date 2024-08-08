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
@Table(name = "calrenactividadescalificacion",schema = "control")
public class CalrenQualificationActivities {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_calificacionesrenovaciones")
	private QualificationsRenewals qualificationsRenewals;
	
	//@ManyToOne
	//@JoinColumn(name = "id_actividadescalificacion")
        @Column(name = "id_actividadescalificacion")
	private Integer activityQualificationID;
	
	@Column(name = "estado")
	private String state;
	
	@Column(name = "area_almacenamiento")// cantidad en metros cuadrados
	private Double storageArea;
	
	@Column(name = "capacidad_almacenamiento")// cantidad en metros cubicos
	private Double storageCapacity;
	
	@Column(name = "codigo_operador_importacion")
	private String importOperatorCode;
	
	@Column(name = "codigo_operador_exportacion")
	private String exportOperatorCode;
	
	@Column(name = "permiso_arcsa")
	private String permissionArcsa;
	
	@Column(name = "correo_principal")
	private String email;
	
	@Column(name = "correo_alterno")
	private String emailAlternate;

}
