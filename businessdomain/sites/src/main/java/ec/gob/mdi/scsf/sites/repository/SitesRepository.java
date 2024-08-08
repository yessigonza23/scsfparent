package ec.gob.mdi.scsf.sites.repository;

import ec.gob.mdi.scsf.sites.entities.Sites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SitesRepository extends JpaRepository<Sites, Long> {
    
}
