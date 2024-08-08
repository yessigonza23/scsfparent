package ec.gob.mdi.scsf.calrenproduction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "calrensustanciasactividadesmateriaprima",schema = "control")
public class CalrenSusActRawMaterial {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//@ManyToOne
	//@JoinColumn(name = "id_calrensustanciasactividades")
	@Column(name = "id_calrensustanciasactividades")
        private Integer calrenSubstancesActivitiesID;
	
	//@ManyToOne
	//@JoinColumn(name = "id_sustanciasNoControladas")
	@Column(name = "id_sustanciasNoControladas")
        private Integer nonControlledSubstancesID;
	
	@Column(name = "porcentaje")
	private Integer porcentage;
	
	@Column(name = "relacion_porcentual")
	private String percentageRatio;
	
	@Column(name = "id_calrensustanciasactividadesproduccion")
        private Integer calrenSubstancesActivitiesProduccionID;

    
}
