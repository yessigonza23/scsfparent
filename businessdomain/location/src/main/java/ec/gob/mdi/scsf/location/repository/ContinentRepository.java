package ec.gob.mdi.scsf.location.repository;

import ec.gob.mdi.scsf.location.entities.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Long> {
    
}
