package ec.gob.mdi.scsf.sites.repository;

import ec.gob.mdi.scsf.sites.entities.VolumetricMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolumetricMeasurementRepository extends JpaRepository<VolumetricMeasurement, Long> {
    
}
