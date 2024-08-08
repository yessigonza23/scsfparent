package ec.gob.mdi.scsf.representatives.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "representantescalificacionrenovacion",schema = "control")
@Data
public class RepresentativesQualificationRenovation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
    private Integer id;
    @Column(name = "fecha_renovacion")
    private LocalDateTime dateRenovation;
    @Column(name = "fecha_caducidad")
    private LocalDateTime dateCaducity;
     @Column(name = "nota_evaluacion")
    private Float evaluationNote;
    @Column(name = "estado")
    private String state;
    @Column(name = "numero_informe")
    private String numReport;
    @Column(name = "aprueba_informe")
    private Boolean approvesReport;
    @Column(name = "recomendaciones")
    private String recomendations;    
    @Column(name = "observaciones")
    private String observations;    
    @Column(name = "fecha_ingreso")
    private LocalDateTime dateIcome;
    @Column(name = "fecha_envio")
    private LocalDateTime dateSend;
    @Column(name = "fecha_asignacion")
    private LocalDateTime dateAssignment;
    @Column(name = "fecha_aprobacion")
    private LocalDateTime dateApproval;    
    @Column(name = "recomienda")
    private Boolean recommended;
    @Column(name = "fecha_notapago")
    private LocalDateTime datePaymentNote;
    @Column(name = "fecha_pago")
    private LocalDateTime datePayment;    
    @Column(name = "factura")
    private String invoice;
    @Column(name = "fecha_informe")
    private LocalDateTime dateReport;
    @Column(name = "id_representantes")
    private Integer representantiveID;
    @Column(name = "fecha_evaluacion")
    private LocalDateTime dateEvaluation;
    @Column(name = "secuencia")
    private Integer sequence;
    @Column(name = "estado_calren")
    private String estate_calren;
    @Column(name = "aprobado")
    private String approved;
    @Column(name = "fecha_registro")
    private LocalDateTime dateRegistration;
    @Column(name = "nombre_archivo")
    private String fileName;
    @Column(name = "ruta_archivo")
    private String filePath;
}
    
