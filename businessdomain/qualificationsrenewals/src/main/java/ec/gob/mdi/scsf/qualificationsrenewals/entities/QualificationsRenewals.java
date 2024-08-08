package ec.gob.mdi.scsf.qualificationsrenewals.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "calificacionesrenovaciones", schema = "control")
public class QualificationsRenewals{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//@ManyToOne
	//@JoinColumn(name = "id_empresa")
        @Column(name = "id_empresa")
	private Integer companyID;
        
    
	@Column(name = "secuencia")
	private Integer sequence;
	
	@Column(name = "fecha_renovacion")
	private LocalDateTime dateRenovation;
	
	@Column(name = "fecha_caducidad")
	private LocalDateTime dateCaducity;
	
	@Column(name = "fecha_registro")
	private LocalDateTime dateRegistration;
	
	@Column(name = "observacion")
	private String observation;
	
	@Column(name = "numero_informe")
	private String numReport;
	
	@Column(name = "fecha_informe")
	private LocalDateTime dateReport;
	
	@Column(name = "fecha_notapago")
	private LocalDateTime datePaymentNote;
	
	@Column(name = "factura")
	private String invoice;
	
	@Column(name = "fecha_pago")
	private LocalDateTime datePayment;

	@Column(name = "estado_calren")
	private String stateCalren;
	
	@Column(name = "aprobado")
	private String approved;
	
	@Column(name = "categoria_actual")
	private Integer currentCategory = 1;
	
	@Column(name = "cupo_kg_actual")
	private Double quotaKgCurrent = 0.00;
	
	@Column(name = "nombre_archivo_acta")
	private String fileNameActa;
	
	@Column(name = "ruta_archivo_acta")
	private String filePathActa;
	
	@Column(name = "conclusiones", columnDefinition = "boolean default true")
	private Boolean conclusions = true;
	
	@Column(name = "recomendaciones", columnDefinition = "boolean default true")
	private Boolean recommendations = true;
}
