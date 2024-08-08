package ec.gob.mdi.scsf.qualificationsrenewals.entities;

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
@Table(name = "calrensustancias",schema = "control")
public class CalrenSubstances {    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_calificacionesRenovaciones", referencedColumnName = "id")
	private QualificationsRenewals qualificationsRenewals;
	
	//@ManyToOne
	//@JoinColumn(name = "id_sustancias", referencedColumnName = "id")
        @Column(name = "id_sustancias")
	private Integer substanceID;
	
	//@ManyToOne
	//@JoinColumn(name = "id_presentacion", referencedColumnName = "id")
        @Column(name = "id_presentacion")
	private Integer presentationID;
	
	//@ManyToOne
	//@JoinColumn(name = "id_tipocambio")
        @Column(name = "id_tipocambio")
	private Integer typeChangeID;
	

	@Column(name = "cupo_solicitado")
	private Double quotaRequest;
	
	@Column(name = "cupo_asignado")
	private Double quotaAssigned;
	
	@Column(name = "unidad")
	private String unit;
	
	@Column(name = "estado")
	private Boolean state=true;
	
	@Column(name = "tipo_cambio")
	private String typeChange;

 
}
