package ec.gob.mdi.scsf.inbox.repository;

import ec.gob.mdi.scsf.inbox.entities.InboxInspections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboxInspectionsRepository extends JpaRepository<InboxInspections, Long> {
    
}
