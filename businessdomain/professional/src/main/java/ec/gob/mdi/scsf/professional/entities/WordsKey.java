package ec.gob.mdi.scsf.professional.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "palabrasclave",schema = "control")
public class WordsKey {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    @Column(name = "descripcion")
    private String description;   
}
