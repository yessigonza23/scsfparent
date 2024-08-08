package ec.gob.mdi.scsf.sites.repository;

import ec.gob.mdi.scsf.sites.entities.Weighing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeighingRepository extends JpaRepository<Weighing, Long> {
    
}
