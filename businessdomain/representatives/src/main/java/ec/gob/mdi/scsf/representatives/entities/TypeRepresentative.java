package ec.gob.mdi.scsf.representatives.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_representante",schema = "control")
public class TypeRepresentative {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
  
    @Column(name = "nombre")
    private String name;
    
    @Column(name = "tipo")
    private String type;
    //@OneToMany(mappedBy = "id_tiposustancia")
    //private Collection<Substance> sustanciasCollection;
}
