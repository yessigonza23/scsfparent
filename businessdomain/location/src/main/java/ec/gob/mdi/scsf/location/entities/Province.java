package ec.gob.mdi.scsf.location.entities;

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
@Table(name = "provincia",schema = "control")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
    @Column(name = "codigo")
    private String code;
    @Column(name = "nombre")
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_coordinacion", referencedColumnName = "id")
    private Coordination idCoordination;    
    @Column(name = "latitud")
    private Float latitude;    
    @Column(name = "longitud")
    private Float longitude;  
}
