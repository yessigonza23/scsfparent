package ec.gob.mdi.scsf.provisionservicesuse.entities;

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
@Table(name = "calrenactividadescalificacionclientesext",schema = "control")
public class CalrenActivitiesQualificationClientsExt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@ManyToOne
    //@JoinColumn(name = "id_calrenactividadescalificacion", nullable = false)
    @Column(name = "id_calrenactividadescalificacion", nullable = false)
    private Integer calrenActivitiesQualificationID;

    @ManyToOne
    @JoinColumn(name = "id_clientes")
    private Clients clientes;

}
