package ec.gob.mdi.scsf.catalogue.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Entity
@Table(name = "sustancias",schema = "control")
@Data
public class Substance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")    
    private Integer id;
    @Column(name = "denominacion")
    private String denomination;
    @Column(name = "estado")
    private String state;
    @Column(name = "lista")
    private String list;
    @Column(name = "necesita_reptecnico")
    private Boolean needRepTechnical;
    @Column(name = "nombre")
    private String name;
    @Column(name = "numero_caso")
    private String numCase;
    @Column(name = "tipo")
    private String type;
    @Column(name = "uni_l")
    private Float unil;
    @Column(name = "uni_ml")
    private Float uniml;
    @Column(name = "observacion")
    private String observation;
    @ManyToOne
    @JoinColumn(name = "id_tiposustancia", referencedColumnName = "id")
    private TypeSubstance typeSubstance;
}
    
