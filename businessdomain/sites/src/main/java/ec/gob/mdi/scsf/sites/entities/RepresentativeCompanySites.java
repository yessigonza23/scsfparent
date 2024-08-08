package ec.gob.mdi.scsf.sites.entities;

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
@Table(name = "sitios_empresa_representantes",schema = "control")
public class RepresentativeCompanySites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @Column(name = "estado")
    private Boolean state;
    
    @Column(name = "id_empresarepresentantes")
    private Integer companyRepresentativeID;    
    
    @ManyToOne
    @JoinColumn(name = "id_sitios", referencedColumnName = "id")
    private Sites sites;  
}
