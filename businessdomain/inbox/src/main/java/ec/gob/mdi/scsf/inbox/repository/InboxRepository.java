package ec.gob.mdi.scsf.inbox.repository;

import ec.gob.mdi.scsf.inbox.entities.Inbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboxRepository extends JpaRepository<Inbox, Long> {
    
}
