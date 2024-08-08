package ec.gob.mdi.scsf.location.repository;

import ec.gob.mdi.scsf.location.entities.Coordination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinationRepository extends JpaRepository<Coordination, Long> {
    
}
