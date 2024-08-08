package ec.gob.mdi.scsf.company.entities;

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
@Table(name = "empresa", schema = "control")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "actividad_principal")
    private String mainActivity;
    @Column(name = "actividad_secundaria")
    private String secondaryActivity;
    @Column(name = "causa_anulacion")
    private String causeCancellation;
    @Column(name = "correo_electronico")
    private String email;
    @Column(name = "correo_electronico_contacto")
    private String emailContact;
    @Column(name = "estado")
    private String state;
    @Column(name = "fecha_anulacion")
    private LocalDateTime dateCancellation;
    @Column(name = "fecha_calificacion")
    private LocalDateTime dateQualification;
    @Column(name = "fecha_registro")
    private LocalDateTime dateRegistration;
    @Column(name = "j_fecha_constitucion")
    private LocalDateTime jDateConstitution;
    @Column(name = "j_fecha_expediente")
    private LocalDateTime jDateDossier;
    @Column(name = "j_numero_expediente")
    private String jNumDossier;
    @Column(name = "j_situacion_legal")
    private String jLegalStatus;
    @Column(name = "maneja_sustancias")
    private Boolean handlesSubstances;
    @Column(name = "nombre")
    private String name;
    @Column(name = "nombre_comercial")
    private String tradeName;
    @Column(name = "numero_calificacion")
    private String numQualification;
    @Column(name = "o_estado_contribuyentes")
    private String oStateTaxpaye;
    @Column(name = "o_fecha_acta_constitutiva")
    private LocalDateTime oDateActaConstitutive;
    @Column(name = "o_fecha_resolucion")
    private LocalDateTime oDateResolution;
    @Column(name = "o_numero_resolucon")
    private String oNumResolution;
    @Column(name = "p_acto_creacion")
    private String pActCreation;
    @Column(name = "p_fecha_creacion")
    private LocalDateTime pDateCreation;
    @Column(name = "p_fecha_registro_oficial")
    private LocalDateTime pDateOfficialRegistration;
    @Column(name = "p_registro_oficial")
    private String pOfficialRegistration;
    @Column(name = "persona_contacto")
    private String contactPerson;
    @Column(name = "ruc")
    private String ruc;
    @Column(name = "subtipo_contribuyente")
    private String taxpayerSubtype;
    @Column(name = "telefono_alterno")
    private String alternatePhone;
    @Column(name = "telefono_celular")
    private String cellPhone;
    @Column(name = "telefono_contacto")
    private String phoneContact;
    @Column(name = "telefono_principal")
    private String primaryPhone;
    @Column(name = "tipo_contribuyente")
    private String taxPayerType;
    @Column(name = "tipo_empresa")
    private String companyType;
    @Column(name = "tipo_persona")
    private String typePerson;
    @Column(name = "transporta_sustancias")
    private Boolean transportsSubstances;
    @Column(name = "vencimiento_plazo_social")
    private LocalDateTime expirationTermSocial;    
    @Column(name = "id_parroquia")
    private Integer parroquiaID;
    @Column(name = "numero_empleados")
    private Integer numEmployees;
}
