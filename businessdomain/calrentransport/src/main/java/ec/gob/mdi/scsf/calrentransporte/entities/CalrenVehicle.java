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
@Table(name = "calrenvehiculos",schema = "control")
public class CalrenVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@ManyToOne
    //@JoinColumn(name = "id_calificacionesRenovaciones")
    @JoinColumn(name = "id_calificacionesRenovaciones")
    private Integer qualificationRenewalsID;

    @ManyToOne
    @JoinColumn(name = "id_vehiculos")
    private Vehicle vehicle;

    @Column(name = "tipo_sustancia", length = 1, nullable = false)
    private String typeSubstance;

    @Column(name = "certificado_sae")
    private String certificateSae;

    @Column(name = "fecha_inspeccion")
    private LocalDateTime dateInspection;

    @Column(name = "fecha_caducidad")
    private LocalDateTime dateCaducity;

    @Column(name = "ruta_pdf")
    private String routePdf;

    @Column(name = "nombre_archivo_pdf")
    private String fileNamePdf;

    @Column(name = "link_rastreo")
    private String trackingLink;

    @Column(name = "usuario_rastreo")
    private String userTracking;

    @Column(name = "clave_rastreo")
    private String keyTracking;

    //////////NACIONALES 
    /////lista desplegable con las opciones: Permiso de operaciones; Autorización por cuenta propia; 
    /////Vehículos del estado; Otros (Especificar).
    //////Al seleccionar Otros, habilitar un campo editable vacío para ingreso de información). 
    @ManyToOne
    @JoinColumn(name = "id_vehiculosautorizacion", nullable = false)
    private VehicleAutoritation vehicleAutoritation;

    @Column(name = "n_num_autorizacion")
    private String nNumAutoritation;

    @Column(name = "n_autorizacion_otros")
    private String nAutoritationOthers;

    //////EXTRANJEROS
    ////lista desplegable con las opciones: Permiso originario-PO; Certificado de idoneidad-CI/ 
    ////Permiso de prestación de servicios - PPS).
    ////Si selecciona Permiso originario-PO,
    @Column(name = "x1_num_permiso")
    private String x1NumPermission;

    @Column(name = "x1_lugar_fecha_expedicion")
    private String x1PlaceDateExpedition;

    @Column(name = "x1_fecha_vencimiento")
    private LocalDateTime x1DateExpiration;

    ///Si selecciona Certificado de idoneidad-CI/ Permiso de prestación de servicios - PPS, 
    ///habilitar los siguientes campos:
    @Column(name = "x2_num_ci")
    private String x2NumCi;

    @Column(name = "x2_lugar_fecha_expedicion")
    private String x2PlaceDateExpedition;

    @Column(name = "x2_fecha_vencimiento")
    private LocalDateTime x2DateExpiration;

    @Column(name = "x2_numero_pps")
    private String x2NumPps;

    @Column(name = "x2_lugar_fecha_expedicion_pps")
    private String x2PlaceDateExpeditionPps;

    @Column(name = "x2_fecha_vencimiento_pps")
    private LocalDateTime x2DateExpirationPps;

    @Column(name = "estado", nullable = false)
    private Boolean state;

    @Column(name = "factura")
    private String invoice;

    @Column(name = "informe")
    private String report;

    @Column(name = "fecha_activacion")
    private LocalDateTime dateActivation;

    @Column(name = "fecha_desactivacion")
    private LocalDateTime DateDeactivation;

    @Column(name = "id_tipocambio", nullable = false)
    private Integer typeChangeID;

}
