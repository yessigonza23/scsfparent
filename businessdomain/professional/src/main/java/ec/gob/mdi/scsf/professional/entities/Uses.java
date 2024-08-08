package ec.gob.mdi.scsf.professional.entities;

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
@Table(name = "usos",schema = "control")
public class Uses {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    @Column(name = "nombre")
    private String name;
    @Column(name = "siglas")
    private String acronyms;  
    @ManyToOne
    @JoinColumn(name = "id_ciuu", referencedColumnName = "id")
    private Ciiu ciiu;    
}