package ec.gob.mdi.scsf.inbox.repository;

import ec.gob.mdi.scsf.inbox.entities.InboxStatusCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboxStatusCatalogueRepository extends JpaRepository<InboxStatusCatalogue, Long> {
    
}
