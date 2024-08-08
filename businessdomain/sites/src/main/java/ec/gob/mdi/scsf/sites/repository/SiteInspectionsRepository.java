package ec.gob.mdi.scsf.sites.repository;

import ec.gob.mdi.scsf.sites.entities.SiteInspections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteInspectionsRepository extends JpaRepository<SiteInspections, Long> {
    
}
