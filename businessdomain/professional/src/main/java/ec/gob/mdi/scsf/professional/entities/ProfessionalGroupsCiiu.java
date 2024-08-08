package ec.gob.mdi.scsf.professional.entities;

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
@Table(name = "gruposprofesionalesciiu",schema = "control")
public class ProfessionalGroupsCiiu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_gruposprofesionales", referencedColumnName = "id")
    private ProfessionalGroups professionalGroups;

    @ManyToOne
    @JoinColumn(name = "id_ciiu", referencedColumnName = "id")
    private Ciiu ciiu;
}
