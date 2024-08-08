/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.transaction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Jose Alvear
 */
@Entity
@Table(name = "transacciones", schema = "control")
@Data
public class RecordTransaction implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
  
    @Column(name = "saldo_inicial")
    private float initialBalance;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Column(name = "numero_factura")
    private String billNumber;
    
    @Column(name = "orden_produccion")
    private String productionOrder;
    
    @Column(name = "cantidad_ingreso")
    private float incomeAmount;
    
    @Column(name = "cantidad_egreso")
    private float expenditureAmount;
    
    @Column(name = "numero_lote")
    private String numberLote;
    
    @Column(name = "cantidad_producida")
    private float quantityProduced;
    
    @Column(name = "id_transacciones")
    private Integer idTrasaction;
    
    @Column(name = "id_calificacion")
    private Integer idQualification;
    
    @Column(name = "id_tipo_recipiente")
    private Integer idTypeBowl;
    
    @Column(name = "id_producto")
    private Integer idProduct;
    
    @Column(name = "id_recipiente")
    private Integer idBowl;
    
    @Column(name = "id_sitio_autorizado")
    private Integer idSiteAuthorized;
      
    @Column(name = "id_sustancia")
    private Integer idSubstance;
    
    @Column(name = "estado")
    private Boolean status;
}
