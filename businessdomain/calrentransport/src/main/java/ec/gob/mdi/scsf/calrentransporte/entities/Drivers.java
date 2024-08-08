package ec.gob.mdi.scsf.calrentransporte.entities;

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
@Table(name = "conductores",schema = "control")
public class Drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_pais")
    private Integer countryID;

    @Column(name = "tipo_documento")
    private String typeDocument;

    @Column(name = "identificacion")
    private String identification;

    @Column(name = "nombre")
    private String name;

    @Column(name = "ruta_foto")
    private String routePhoto;

    @Column(name = "nombre_foto")
    private String namePhoto;

    @Column(name = "tipo_licencia")
    private String typeLicense;

    @Column(name = "tipo_licencia_ext") // campo si/no si es igual que el tipo E
    private String typeLiceneExt;

    @Column(name = "fecha_vigencia")
    private LocalDateTime dateValidity;

    @Column(name = "telefono_celular")
    private String cellPhone;

}
