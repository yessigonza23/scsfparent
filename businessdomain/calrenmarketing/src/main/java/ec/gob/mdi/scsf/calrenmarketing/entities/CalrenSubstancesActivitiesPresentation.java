package ec.gob.mdi.scsf.calrenmarketing.entities;

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
@Table(name = "calrensustanciasactividadespresentacion")
public class CalrenSubstancesActivitiesPresentation {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_calrensustanciasactividades", nullable = false)
	private CalrenSubstancesActivities calrenSubstancesActivities;
	
        @Column(name = "id_presentacion")
	private Integer presentationID;

}
