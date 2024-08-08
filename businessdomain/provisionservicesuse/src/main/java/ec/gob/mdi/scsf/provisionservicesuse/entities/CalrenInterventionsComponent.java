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
@Table(name = "calrenintervencionescomponentes",schema = "control")
public class CalrenInterventionsComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_calrenintervenciones")
    private CalrenInterventions calrenIntervenciones;

    //@ManyToOne
    //@JoinColumn(name = "id_sustanciasnocontroladas")
    @Column(name = "id_sustanciasnocontroladas")
    private Integer nonControlledSubstancesID;

    //@ManyToOne
    //@JoinColumn(name = "id_calrenSustanciasActividades")
    @Column(name = "id_calrenSustanciasActividades")
    private Integer calrenSubstancesActivitiesID;

    @Column(name = "porcentaje")
    private Double porcentage;

}
