package ec.gob.mdi.scsf.company.repository;

import ec.gob.mdi.scsf.company.entities.NonControlledSubstances;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonControlledSubstancesRepository extends JpaRepository<NonControlledSubstances, Long> {
    
}
