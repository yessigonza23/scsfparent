package ec.gob.mdi.scsf.calrentransporte.repository;

import ec.gob.mdi.scsf.calrentransporte.entities.CalrenVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalrenVehicleRepository extends JpaRepository<CalrenVehicle, Long> {
    
}
