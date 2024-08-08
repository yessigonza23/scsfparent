package ec.gob.mdi.scsf.coordination.repository;

import ec.gob.mdi.scsf.coordination.entities.Coordination;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jose Alvear
 */
public interface CoordinationRepository extends JpaRepository <Coordination, Long> {
    
}
