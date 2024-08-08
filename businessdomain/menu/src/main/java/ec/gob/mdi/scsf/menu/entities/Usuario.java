/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.menu.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
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
@Table(name = "usuario", schema = "control")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_coordinacion")
    private Integer coordinacion;

    @Size(max = 15)
    @Column(name = "identificacion")
    private String username;

    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;

    @Size(max = 1)
    @Column(name = "tipocertificado")
    private String tipoCertificado;

    @Size(max = 100)
    @Column(name = "correo_electronico")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @Size(max = 1)
    @Column(name = "estado")
    private String estado;

    @Size(max = 80)
    @Column(name = "clave")
    private String password;

    @Column(name = "fecha_registro")
    private LocalDateTime fecha_registro;

    @Column(name = "cantidaddeentradas")
    private Integer cantidadDeEntradas;

    @Column(name = "fecha_cambio_clave")
    private LocalDateTime fecha_cambio_clave;

    @Size(max = 100)
    @Column(name = "cargo")
    private String cargo;

    @Size(max = 30)
    @Column(name = "titulo")
    private String titulo;

    @Size(max = 1)
    @Column(name = "tipo_interno_externo")
    private String tipo_interno_externo;

    @Transient
    private String clave_anterior;

    @Transient
    private String clave_repetida;

    @Column(name = "fecha_desactivacion")
    private LocalDateTime fecha_desactivacion;

    @Column(name = "reseteaclave")
    private boolean reseteaClave;

    @Column(name = "jefe", columnDefinition = "boolean default false")
    private boolean jefe = false;

    @Column(name = "rol")
    private String rol;

    @Column(name = "mail_to_copy")
    private String mailToCopy;

}
