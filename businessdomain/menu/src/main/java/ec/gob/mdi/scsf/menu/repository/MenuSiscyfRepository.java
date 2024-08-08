/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.menu.repository;

import ec.gob.mdi.scsf.menu.entities.MenuSiscyf;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author yessenia.gonzalez
 */
public interface MenuSiscyfRepository extends JpaRepository <MenuSiscyf, Long>{
 
  @Query(value = "SELECT DISTINCT m.* FROM control.MenuRol_Siscyf mr "
          + "inner join control.Usuario_Rol_Siscyf ur on ur.id_rolsiscyf  = mr.id_rol "
          + "inner join control.Menu_Siscyf m on m.id = mr.id_menu "
          + "inner join control.usuario u on u.id = ur.id_usuario "
          + "where u.identificacion =:username "
          + "and m.codigo_submenu is null "
          + "ORDER BY  m.tipo desc, m.codigo_submenu, m.nombre_completo", nativeQuery = true)
    List<MenuSiscyf> listaMenuRolByUsuario(@Param("username") String username);

    @Query(value = "SELECT DISTINCT m.* FROM control.MenuRol_Siscyf mr "
            + "inner join control.Usuario_Rol_Siscyf ur on ur.id_rolsiscyf  = mr.id_rol "
            + "inner join control.Menu_Siscyf m on m.id = mr.id_menu "
            + "inner join control.usuario u on u.id = ur.id_usuario "
            + "where m.codigo_submenu =:idMenu "
            + "ORDER BY  m.tipo desc, m.codigo_submenu, m.nombre_completo", nativeQuery = true)
    List<MenuSiscyf> listaMenuRolByUsuarioHijos(@Param("idMenu") Integer idMenu);

}
