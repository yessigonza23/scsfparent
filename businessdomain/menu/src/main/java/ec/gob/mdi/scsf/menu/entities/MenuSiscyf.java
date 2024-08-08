/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.menu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author yessenia.gonzalez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "menu_siscyf", schema = "control")
public class MenuSiscyf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    // tipo de menu I o S
    @Column(name = "tipo", nullable = false, length = 2)
    private String tipo;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "A";

    @Column(name = "link", nullable = false, length = 100)
    private String link;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombre_completo;

    @ManyToOne
    @JoinColumn(name = "codigo_submenu", nullable = false)
    private MenuSiscyf submenu;

    @OneToMany(mappedBy = "submenu", fetch = FetchType.LAZY)
    private List<MenuSiscyf> hijos;

    @ManyToOne(fetch = FetchType.LAZY) // Avoid eager loading for performance
    private MenuSiscyf padre;
}
