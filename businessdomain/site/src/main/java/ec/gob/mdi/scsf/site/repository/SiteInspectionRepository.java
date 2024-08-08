package ec.gob.mdi.scsf.site.repository;

import ec.gob.mdi.scsf.site.entities.SiteInspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteInspectionRepository extends JpaRepository <SiteInspection, Long> {
    
}
