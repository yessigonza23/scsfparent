package ec.gob.mdi.scsf.provisionservicesuse.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "calrenintervenciones",schema = "control")
public class CalrenInterventions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@ManyToOne
    //@JoinColumn(name = "id_calificacionesrenovaciones")
    @Column(name = "id_calificacionesrenovaciones")
    private Integer qualificationRenewalsID;

    @Column(name = "nombre_producto", length = 50, nullable = false)
    private String productName;

    @Column(name = "tipo", length = 10, nullable = false) ////Producto intermedio I, producto terminado T y diluciones D
    private String type;

    @Column(name = "cantidad_producto_elaborado")
    private Double quantityOfFinishedProduct;

    @Column(name = "relacion_porcentual")
    private String relationPercentage;

    @Column(name = "actividad")
    private String activity;

    @Column(name = "capacidad_nominal")
    private Double nominalCapacity;

    @Column(name = "capacidad_real")
    private Double actualCapacity;

    @Column(name = "rendimiento")
    private Double yield;

}
