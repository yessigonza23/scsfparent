package ec.gob.mdi.scsf.calrenwarehouse.entities;

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
@Table(name = "calrenactividadescalificacionclientessustancias",schema = "control")
public class CalrenActCalCustomerSubstances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_calrenactividadescalificacionclientes", nullable = false)
    private CalrenActCalCustomer calrenActCalCustomer;

    @Column(name = "id_calrensustancias")
    private Integer calrenSubstancesID;

}
