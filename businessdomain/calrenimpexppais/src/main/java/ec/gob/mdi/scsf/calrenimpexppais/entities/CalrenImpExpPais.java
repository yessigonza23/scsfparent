package ec.gob.mdi.scsf.calrenimpexppais.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "calrensustanciasactividadespaisimpexp",schema = "control")
public class CalrenImpExpPais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_calrensustanciasactividades", nullable = false)
    private Integer calrenSubstancesActivitiesID;

    @Column(name = "id_pais", nullable = false)
    private Integer countryID;

    @Column(name = "actividad_imp_exp")/// importador o exportador o las dos I E 
    private String activityImpExp;

}
