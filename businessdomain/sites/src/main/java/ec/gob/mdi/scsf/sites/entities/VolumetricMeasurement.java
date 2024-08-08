package ec.gob.mdi.scsf.sites.entities;

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
@Table(name = "medicionvolumetrica",schema = "control")
public class VolumetricMeasurement {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;        
    @Column(name = "cuenta_calibracion")
    private Boolean calibrationAccount;    
    @Column(name = "fecha_certf_calibracion")
    private LocalDateTime dateCertfCalibration;
    @Column(name = "instrumento_medicion")
    private String measuringInstrument;    
    @Column(name = "num_certf_calibracion")
    private String numCertfcalibration;      
    @ManyToOne
    @JoinColumn(name = "id_sitios", referencedColumnName = "id")
    private Sites sites;   
}
