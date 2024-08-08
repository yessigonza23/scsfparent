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
@Table(name = "bandejaentrada", schema = "control")
public class Inbox {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_bantipotramite", nullable = false)
	private InboxTypeProcess inboxTypeProcess;

	@ManyToOne
	@JoinColumn(name = "id_bancatalogoestados", nullable = false)
	private InboxStatusCatalogue inboxStatusCatalogue;

	//@ManyToOne
	@Column(name = "id_calificacionesrenovaciones")
	private Integer qualificationRenewalsID;

	//@ManyToOne
	@Column(name = "id_usuario")
	private Integer user;

	@Column(name = "observacion", length = 1000, nullable = false)
	private String observation;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "fecha_vencimiento")
	private LocalDateTime dateExpiration;
	
	@Column(name = "ver", columnDefinition = "boolean default false")
	private Boolean see = false;
	
	@Column(name = "num_tramite", length = 50, nullable = false)
	private String numProcess;

}
