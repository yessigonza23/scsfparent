package ec.gob.mdi.scsf.calrentransporte.entities;

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
@Table(name = "calrenconductores",schema = "control")
public class CalrenDrivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_calificacionesRenovaciones")
    private Integer qualificationRenewalsID;
    @ManyToOne
    @JoinColumn(name = "id_conductores")
    private Drivers drivers;

    @Column(name = "tipo_sustancia", length = 1)
    private String typeSubstance;

    @Column(name = "num_certificado", length = 30)
    private String numCertificate;

    @Column(name = "fecha_capacitacion")
    private LocalDateTime dateTraining;

    @Column(name = "fecha_vigencia_capacitacion")
    private LocalDateTime dateValidityTraining;

    @Column(name = "ruta_pdf", length = 100)
    private String routePdf;

    @Column(name = "nombre_archivo_pdf", length = 50)
    private String fileNamePdf;

    ///EXTRANJEROS 
    //Si selecciona la opción NO, desplegar el mensaje “El conductor no posee el tipo de 
    //licencia requerido para el transporte de sustancias químicas peligrosas.”
    @Column(name = "transporte_pesado")
    private Boolean heavyTransport;

    @Column(name = "num_libreta_tripulante", length = 30)
    private String numCrewNotebook;

    @Column(name = "lugar_fecha_expedicion_tripulante", length = 150)
    private String placeDateExpeditionCrewman;

    @Column(name = "fecha_vigencia_tripulante")
    private LocalDateTime dateExpeditionCrewman;

    @Column(name = "estado", nullable = false)
    private Boolean state;

    @Column(name = "fecha_activacion")
    private LocalDateTime dateActivation;

    @Column(name = "fecha_desactivacion")
    private LocalDateTime dateDesactivation;

    @Column(name = "id_tipocambio")
    private Integer typeChangeID;

    @Column(name = "ruta_archivo", length = 30)
    private String filePath;

}
