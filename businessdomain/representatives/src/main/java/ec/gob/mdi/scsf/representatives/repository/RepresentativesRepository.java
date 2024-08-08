
package ec.gob.mdi.scsf.representatives.repository;

import ec.gob.mdi.scsf.representatives.entities.Representatives;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepresentativesRepository extends JpaRepository<Representatives, Long> {
    
}
