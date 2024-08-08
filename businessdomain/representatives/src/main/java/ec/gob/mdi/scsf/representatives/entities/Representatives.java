package ec.gob.mdi.scsf.representatives.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "representantes",schema = "control")
@Data
public class Representatives implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "correo_electronico")
    private String email;
    @Column(name = "direccion")
    private String address;
    @Column(name = "estado")
    private String state;
    @Column(name = "estado_registro")
    private String stateRegistration;
    @Column(name = "fecha_activa")
    private LocalDateTime dateActive;
    @Column(name = "fecha_aprobacion")
    private LocalDateTime dateApproval;
    @Column(name = "fecha_nacimiento")
    private LocalDateTime dateBirth;
    @Column(name = "fecha_noactiva")
    private LocalDateTime dateNotActive;
    @Column(name = "fecha_nombramiento")
    private LocalDateTime dateAppointment;
    @Column(name = "fecha_registro")
    private LocalDateTime dateRegistration;
    @Column(name = "genero")
    private String gender;
    @Column(name = "identificacion")
    private String identification;
    @Column(name = "nombramiento")
    private String appointment;
    @Column(name = "nombre")
    private String name;
    @Column(name = "num_calificacion")
    private String numQualification;
    @Column(name = "referencia")
    private String reference;
    @Column(name = "registro_contrato")
    private String contractRegistration;
    @Column(name = "registro_senescyt")
    private String senescytRegistration;
    @Column(name = "telefono_celular")
    private String cellPhone;
    @Column(name = "telefono_domicilio")
    private String homeTelephone;
    @Column(name = "tipo_identificacion")
    private String typeIdentification;
    @Column(name = "tipo_persona")
    private String typePerson;
    @Column(name = "titulo")
    private String title;
    @Column(name = "id_pais")    
    private Integer countryID;
    @Column(name = "id_parroquia")
    private Integer parroquiaID;
    @ManyToOne
    @JoinColumn(name = "id_tipo_representante", referencedColumnName = "id")
    private TypeRepresentative typeRepresentativeID;
    @Column(name = "institucion")
    private String institution;
    @Column(name = "fecha_registro_senescyt")
    private LocalDateTime dateRegistrationSenescyt;
    @Column(name = "id_grupoprofesional")
    private Integer profesionalGroupID;    
    @Column(name = "fecha_calificacion")
    private LocalDateTime dateQualification;    
    @Column(name = "pertenecer_banco")
    private Boolean belongBank;    
    @Column(name = "fecha_envio")
    private LocalDateTime dateSend;
    @Column(name = "fecha_anulacion")
    private LocalDateTime dateAnnulment;
    @Column(name = "causa_anulacion")
    private String causeCancelation;
    @Column(name = "correo_alterno")
    private String alternativeEmail;
    @Column(name = "id_coordinacion")
    private Integer coordinacionID;
    @Column(name = "area")
    private String area;
}
