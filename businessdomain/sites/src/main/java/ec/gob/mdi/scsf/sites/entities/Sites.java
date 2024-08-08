package ec.gob.mdi.scsf.sites.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "sitios",schema = "control")
public class Sites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@ManyToOne
    @Column(name = "id_calificacionesrenovaciones")
    private Integer qualificationRenewalsID;

    //@ManyToOne
    @Column(name = "id_parroquia")
    private Integer parroquiaID;
    
    @Transient
    private String parroquiaName;

    @Column(name = "calle")
    private String street;

    @Column(name = "interseccion")
    private String intersection;

    @Column(name = "numero")
    private String number;

    @Column(name = "nombre_comercial")
    private String commercialName;

    @Column(name = "tipo_establecimiento")
    private String typeEstablishment;

    @Column(name = "numero_establecimiento")
    private String numEstablishment;

    @Column(name = "latitud")
    private Double latitude;

    @Column(name = "longitud")
    private Double longitude;

    @Column(name = "instalacion")
    private String installation;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "num_extension")
    private String numExtension;

    @Column(name = "oficina", columnDefinition = "boolean default false")
    private Boolean office = false;

    @Column(name = "bodega", columnDefinition = "boolean default false")
    private Boolean winecellar = false;

    @Column(name = "planta", columnDefinition = "boolean default false")
    private Boolean plant = false;

    @Column(name = "sucursal", columnDefinition = "boolean default false")
    private Boolean branch = false;

    @Column(name = "mayor_actividad", columnDefinition = "boolean default false")
    private Boolean increasedActivity = false;

    @Column(name = "control_ingreso_manual", columnDefinition = "boolean default false")
    private Boolean controlEntryManual = false;

    @Column(name = "control_ingreso_computarizado")
    private Boolean controlEntryComputarized = false;

    @Column(name = "nombre_archivo")
    private String fileName;

    @Column(name = "ruta_archivo")
    private String filePath;

    @Column(name = "kardex", columnDefinition = "boolean default false")
    private Boolean kardex = false;

    @Column(name = "bitacora", columnDefinition = "boolean default false")
    private Boolean log = false;

    @Column(name = "otros", columnDefinition = "boolean default false")
    private Boolean other = false;

    @Column(name = "otros_descripcion")
    private String otherDescription;

    @Column(name = "otros_computarizado")
    private String otherComputarized;
 
    @Column(name = "cerramiento", columnDefinition = "boolean default false")
    private Boolean closing = false;

    @Column(name = "cerca_electrica", columnDefinition = "boolean default false")
    private Boolean electricFence = false;

    @Column(name = "guardiania", columnDefinition = "boolean default false")
    private Boolean guardianship = false;

    @Column(name = "alarmas", columnDefinition = "boolean default false")
    private Boolean alarms = false;

    @Column(name = "sensores_movimiento", columnDefinition = "boolean default false")
    private Boolean motionSensors = false;

    @Column(name = "circuito_cerrado", columnDefinition = "boolean default false")
    private Boolean closedCircuit = false;

    @Column(name = "hojas_seguridad", columnDefinition = "boolean default false")
    private Boolean safetyDataSheets = false;

    @Column(name = "plan_contigencia", columnDefinition = "boolean default false")
    private Boolean contingencyPlan = false;

    @Column(name = "kit_derrames", columnDefinition = "boolean default false")
    private Boolean spillKit = false;

    @Column(name = "dique_contencion", columnDefinition = "boolean default false")
    private Boolean containmentDike = false;

    @Column(name = "equipos_proteccion", columnDefinition = "boolean default false")
    private Boolean protectiveEquipment = false;

    @Column(name = "permiso_bomberos", columnDefinition = "boolean default false")
    private Boolean firePermit = false;

    @Column(name = "num_permiso_bomberos")
    private String numFirePermit;

    @Column(name = "vigencia_permiso_bomberos")
    private String validityfirePermit;

    @Column(name = "ducha_emergencia", columnDefinition = "boolean default false")
    private Boolean emergencyShower = false;

    @Column(name = "lavatorio_ojos", columnDefinition = "boolean default false")
    private Boolean eyewash = false;

    @Column(name = "sistema_contra_incendios", columnDefinition = "boolean default false")
    private Boolean fireFightingSystem = false;

    @Column(name = "nombre_archivo_seguridad")
    private String fileNameSecurity;

    @Column(name = "ruta_archivo_seguridad")
    private String filePathSegurity;

    @Column(name = "area_bodega")
    private Double wineCellarArea = 0.00;

    @Column(name = "volumen_bodega")
    private Double cellarVolume = 0.00;

    @Column(name = "capacidad_bodega")
    private Double warehouseCapacity = 0.00;
}
