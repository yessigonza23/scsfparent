package ec.gob.mdi.scsf.catalogue.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Entity
@Table(name = "actividadcalificacion",schema = "control")
@Data
public class QualificationActivities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
    private Integer id;
    @Column(name = "abreviatura")
    private String abbreviation;
    @Column(name = "definicion")
    private String definition;
     @Column(name = "estado")
    private Boolean state;
    @Column(name = "nombre")
    private String name;
    @Column(name = "nombre_formulario")
    private String nameForm;
    @Column(name = "si_sustancia")
    private Boolean siSubstance;
    @Column(name = "codigofacturacion")
    private String codeBilling;

}
    
