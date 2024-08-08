package ec.gob.mdi.scsf.company.repository;

import ec.gob.mdi.scsf.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "SELECT us FROM Company us where us.ruc=:ruc and us.state ='A'", nativeQuery = false)
    Company validityCompanyActive(@Param("ruc")String ruc);
    
    @Query(value = "SELECT us FROM Company us where us.id = (select max(id) from Company c where c.ruc = us.ruc and c.ruc=:ruc and c.state<>'N' ) ", nativeQuery = false)
    Company existsCompany(@Param("ruc")String ruc);
}
