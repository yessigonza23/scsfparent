package ec.gob.mdi.scsf.sites.entities;

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
@Table(name = "otrosinstrumentosmedicion",schema = "control")
public class OtherMeasuringInstruments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "descripcion")
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_sitios", referencedColumnName = "id")
    private Sites sites;
}
