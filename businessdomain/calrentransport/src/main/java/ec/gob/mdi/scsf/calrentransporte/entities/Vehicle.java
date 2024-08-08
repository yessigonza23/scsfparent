package ec.gob.mdi.scsf.calrentransporte.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculos",schema = "control")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "origen")
    private String origin;

    @Column(name = "placa")
    private String plaque;

    @Column(name = "placa_adicional")
    private String plaqueAdditional;

    @Column(name = "propietario")
    private String owner;

    @Column(name = "id_pais")
    private Integer countryID;

    @ManyToOne
    @JoinColumn(name = "id_vehiculostipo", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "id_tipoCambio")
    private Integer typeChangeID;

    @Column(name = "marca")
    private String brand;

    @Column(name = "modelo", nullable = false)
    private String model;

    @Column(name = "anio_fabricacion", nullable = false)
    private String yearManufacture;

    @Column(name = "color")
    private String colour;

    @Column(name = "num_motor", nullable = false)
    private String numEngine;

    @Column(name = "num_serie_chasis", nullable = false)
    private String numChassisSeries;

    @Column(name = "capacidad_carga")
    private String loadCapacity;

}
