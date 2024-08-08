package ec.gob.mdi.scsf.catalogue.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_sustancia",schema = "control")
public class TypeSubstance {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "definicion")
    private String definition;
    @Column(name = "estado")
    private Boolean state;
    @Column(name = "nombre")
    private String name;
}
