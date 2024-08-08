package ec.gob.mdi.scsf.location.repository;

import ec.gob.mdi.scsf.location.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    
}
