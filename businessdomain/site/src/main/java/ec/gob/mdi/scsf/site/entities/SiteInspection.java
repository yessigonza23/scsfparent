package ec.gob.mdi.scsf.site.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 *
 * @author Jose Alvear
 */
@Entity
@Table(name = "sitiosinspecciones", schema = "control")
@Data
public class SiteInspection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "conclusiones",length = 1000)
    private String conclusions;
    
    @Column(name = "cumple")
    private Boolean cumple;

    @Column(name = "estado")
    private Boolean status;
    
    @Column(name = "fecha_inspeccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInspection;
    
    @Column(name = "nombre_archivo_check",length = 250)
    private String nameFileCheck;
    
    @Column(name = "nombre_archivo_inf",length = 250)
    private String nameFileInf;
    
    @Column(name = "numero_informe",length = 50)
    private String numberReport;
    
    @Column(name = "observaciones",length = 2000)
    private String observations;
     
    @Column(name = "otra_coordinacion")
    private Boolean anotherCoordination;
    
    @Column(name = "persona_inspeccion",length = 100)
    private String personInspection;
    
    @Column(name = "persona_inspeccion_cedula",length = 10)
    private String personInspectionIdentification;
    
    @Column(name = "recomendaciones",length = 1000)
    private String recommendations;
     
    @Column(name = "ruta_archivo_check",length = 150)
    private String routeFileCheck;
     
    @Column(name = "ruta_archivo_inf",length = 150)
    private String routeFileInf;
    
    @Column(name = "persona_inspeccion_cargo",length = 100)
    private String personInspectionPosition;
    
    @Column(name = "id_coordinacion")
    private Integer coordinationID;
    
    @Column(name = "id_sitios")
    private Integer SiteID;
    
    @Column(name = "id_usuario")
    private Integer userID;
}
