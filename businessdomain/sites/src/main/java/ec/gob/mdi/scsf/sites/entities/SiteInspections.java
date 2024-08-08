package ec.gob.mdi.scsf.sites.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "sitiosinspecciones",schema = "control")
public class SiteInspections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_sitios", nullable = false)
    private Sites sites ;

    @Column(name = "fecha_inspeccion", nullable = false)
    private LocalDateTime dateInspection;

    @Column(name = "observaciones", length = 2000)
    private String remarks;

    //@ManyToOne
    @Column(name = "id_coordinacion")
    private Integer coordinationID;

    //@ManyToOne
    //@JoinColumn(name = "id_usuario")
    @Column(name = "id_usuario")
    private Integer userID;

    @Column(name = "estado", columnDefinition = "boolean default true")
    private Boolean state = true;

    @Column(name = "cumple", columnDefinition = "boolean default false")
    private Boolean meets = false;

    @Column(name = "otra_coordinacion", columnDefinition = "boolean default true")
    private Boolean otherCoordination = false;

    @Column(name = "conclusiones", length = 1000)
    private String conclusions;

    @Column(name = "recomendaciones", length = 1000)
    private String recomendations;

    @Column(name = "persona_inspeccion", length = 100)
    private String personaInspection;

    @Column(name = "persona_inspeccion_cedula", length = 10)
    private String personaInspectionLicense;

    @Column(name = "persona_inspeccion_cargo", length = 100)
    private String personaInspectionCharge;

    @Column(name = "numero_informe", length = 50)
    private String numReport;

    @Column(name = "nombre_archivo_inf")
    private String fileNameInf;

    @Column(name = "ruta_archivo_inf")
    private String filePathInf;

    @Column(name = "nombre_archivo_check")
    private String fileNameCheck;

    @Column(name = "ruta_archivo_check")
    private String filePathCheck;
}
