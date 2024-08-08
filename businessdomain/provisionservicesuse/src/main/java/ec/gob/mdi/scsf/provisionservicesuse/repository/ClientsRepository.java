
package ec.gob.mdi.scsf.provisionservicesuse.repository;

import ec.gob.mdi.scsf.provisionservicesuse.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
    
}
