/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package ec.gob.mdi.scsf.catalogue.repository;

import ec.gob.mdi.scsf.catalogue.entities.TypeSubstance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jose Alvear
 */
public interface TypeSubstanceRepository extends JpaRepository<TypeSubstance, Long> {
    
}
