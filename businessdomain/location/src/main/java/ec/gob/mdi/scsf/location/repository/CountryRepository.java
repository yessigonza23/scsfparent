package ec.gob.mdi.scsf.location.repository;

import ec.gob.mdi.scsf.location.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    
}
