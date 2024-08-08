package ec.gob.mdi.scsf.company.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "empresarepresentantes", schema = "control")
public class CompanyRepresentative {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
        @Column(name = "estado")
	private Boolean state;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Company company;	
        @Column(name = "id_representantes")
	private Integer representativesID;	
	@Column(name = "num_contrato_nombramiento_bod")
	private String numContractAppointmentBod;
	@Column(name = "fecha_nombramiento_bod")
	private LocalDateTime DateAppointmentBod;

}
