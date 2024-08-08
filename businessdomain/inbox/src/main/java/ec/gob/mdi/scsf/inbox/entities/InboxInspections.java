package ec.gob.mdi.scsf.inbox.entities;

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
@Table(name = "bandejaentradainspecciones", schema = "control")
public class InboxInspections{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "id_bantipotramite", nullable = false)
	private Integer InboxTypeProcessID;

	@ManyToOne
	@JoinColumn(name = "id_bancatalogoestados", nullable = false)
	private InboxStatusCatalogue inboxStatusCatalogue;

	@Column(name = "id_sitios", nullable = false)
	private Integer sitesID;

	@Column(name = "id_usuario")
	private Integer userID;

	@Column(name = "observacion", length = 1000, nullable = false)
	private String observation;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "fecha_vencimiento")
	private LocalDateTime dateExpiration;
	
	@Column(name = "ver", columnDefinition = "boolean default false")
	private Boolean see=false;

}