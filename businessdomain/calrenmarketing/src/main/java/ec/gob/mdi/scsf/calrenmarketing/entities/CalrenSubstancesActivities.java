package ec.gob.mdi.scsf.calrenmarketing.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "calrensustanciasactividades",schema = "control")
public class CalrenSubstancesActivities {
   @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

        @Column(name = "id_calrensustancias")
	private Integer calrenSubstancesID;

        @Column(name = "id_calrenactividades")
	private Integer calrenQualificationActivities;

	@Column(name = "tipo") /// diluciones o mezclas
	private String type;

	@Column(name = "minima")
	private Double minimum;

	@Column(name = "maxima")
	private Double maxim;

	@Column(name = "relacion_porcentual") /// P/P; P/V; V/V
	private String percentageRatio;

	@Column(name = "cap_ins_nominal")
	private Double capInsNominal;

	@Column(name = "cap_ins_real")
	private Double capInsReal;

	@Column(name = "rendimiento")
	private Double yield;

	@Column(name = "desde")
	private Double from;

	@Column(name = "hasta")
	private Double until;

	@Column(name = "proceso_productivo")
	private String productionProcess;

	@Column(name = "tipo_empleo")
	private String typeEmployment;

	@Column(name = "observacion")
	private String observation;

	@Column(name = "reenvasa")
	private Boolean repackaging = false;
  
}
