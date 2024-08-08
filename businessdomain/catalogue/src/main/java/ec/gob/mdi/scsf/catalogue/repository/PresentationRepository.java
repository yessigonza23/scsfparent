
package ec.gob.mdi.scsf.catalogue.repository;

import ec.gob.mdi.scsf.catalogue.entities.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    
}
