package ec.gob.mdi.scsf.inbox.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bantipotramite",schema = "control")
public class InboxTypeProcess {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "descripcion_corta")
    private String shortDescription;
    @Column(name = "estado")
    private Boolean state;
    @Column(name = "siglas")
    private String acronyms;
    @Column(name = "valor")
    private Integer value;
}
