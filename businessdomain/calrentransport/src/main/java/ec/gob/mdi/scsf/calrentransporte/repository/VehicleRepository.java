package ec.gob.mdi.scsf.calrentransporte.repository;

import ec.gob.mdi.scsf.calrentransporte.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
}
