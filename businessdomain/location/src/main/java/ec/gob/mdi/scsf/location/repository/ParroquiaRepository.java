package ec.gob.mdi.scsf.location.repository;

import ec.gob.mdi.scsf.location.dto.LocationDTO;
import ec.gob.mdi.scsf.location.entities.Parroquia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParroquiaRepository extends JpaRepository<Parroquia, Long> {
    @Query(value = "SELECT new ec.gob.mdi.scsf.location.dto.LocationDTO(c.idCanton.idProvince.name ,c.idCanton.name ,c.name) FROM Parroquia c WHERE c.id=:idParroquia")
    LocationDTO searchByIdParroquia(@Param("idParroquia") Integer idParroquia);
}
