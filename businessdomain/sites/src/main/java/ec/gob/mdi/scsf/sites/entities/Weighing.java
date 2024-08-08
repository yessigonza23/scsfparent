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
@Table(name = "pesaje",schema = "control")
public class Weighing {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
     @Column(name = "anios")
    private Integer years; 
    @Column(name = "capacidad")
    private Integer capacity;   
    @Column(name = "fecha_certf_calibracion")
    private LocalDateTime fechaCertfCalibration;
    @Column(name = "marca_balanza")
    private String brandScale;
    @Column(name = "modelo")
    private String model;    
    @Column(name = "num_certf_calibracion")
    private String numCertfCalibration;
    @Column(name = "tolerancia")
    private Integer tolerance;  
    @Column(name = "unidad")
    private String unit;
    @ManyToOne
    @JoinColumn(name = "id_sitios", referencedColumnName = "id")
    private Sites sites;  
}
