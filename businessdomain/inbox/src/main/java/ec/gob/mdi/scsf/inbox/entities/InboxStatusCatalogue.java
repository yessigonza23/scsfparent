package ec.gob.mdi.scsf.inbox.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bancatalogoestados", schema = "control")
public class InboxStatusCatalogue{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "descripcion", length = 100, nullable = false)
	private String description;

	@Column(name = "siglas", length = 10, nullable = false)
	private String acronyms;

	@Column(name = "estado", columnDefinition = "boolean default false")
	private Boolean state=false;
}

