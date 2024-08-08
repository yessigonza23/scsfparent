package ec.gob.mdi.scsf.coordination.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;


/**
 *
 * @author Jose Alvear
 */
@Entity
@Table(name = "coordinacion", schema = "control")
@Data
public class Coordination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "autoridad",length = 100)
    private String authority;
    
    @Column(name = "nombre",length = 100)
    private String name;
    
    @Column(name = "correo",length = 100)
    private String email;
    
    @Column(name = "autoridad_cargo",length = 100)
    private String authorityPosition;
    
    @Column(name = "siglas",length = 5)
    private String acronym;
}
