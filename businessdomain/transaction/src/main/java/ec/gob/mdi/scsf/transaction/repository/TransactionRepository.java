/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.gob.mdi.scsf.transaction.repository;

import ec.gob.mdi.scsf.transaction.entities.RecordTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jose Alvear
 */
public interface TransactionRepository extends JpaRepository<RecordTransaction, Long>{
    
}
