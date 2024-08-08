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
@Table(name = "pais",schema = "control")
public class Country {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    @Column(name = "codigoalfados")
    private String codealphatwo;    
    @Column(name = "codigoalfatres")
    private String codealphathree;
    @Column(name = "nacionalidad")
    private String nationality;
    @Column(name = "nombre")
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_continente", referencedColumnName = "id")
    private Continent idContinent;
}
