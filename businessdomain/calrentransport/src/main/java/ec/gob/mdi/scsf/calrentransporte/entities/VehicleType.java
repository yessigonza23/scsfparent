package ec.gob.mdi.scsf.calrentransporte.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculostipo",schema = "control")
public class VehicleType {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre")
	private String name;

	@Column(name = "descripcion", length = 500, nullable = false)
	private String description;

	@Column(name = "placa_adicional")
	private Boolean plaqueAdditional;

}
