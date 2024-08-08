package ec.gob.mdi.scsf.calrentransporte.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculosautorizacion",schema = "control")
public class VehicleAutoritation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "siglas")
    private String acronyms;

    @Column(name = "tipo_vehiculo")
    private String typeVehicle;

}
