package ec.gob.mdi.scsf.qualificationsrenewals.repository;

import ec.gob.mdi.scsf.qualificationsrenewals.entities.QualificationsRenewals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationsRenewalsRepository extends JpaRepository<QualificationsRenewals, Long> {
    
}
