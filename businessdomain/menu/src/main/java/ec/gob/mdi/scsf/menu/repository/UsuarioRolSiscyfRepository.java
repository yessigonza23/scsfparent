/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.menu.repository;



import ec.gob.mdi.scsf.menu.entities.RolSiscyf;
import ec.gob.mdi.scsf.menu.entities.Usuario;
import ec.gob.mdi.scsf.menu.entities.UsuarioRolSiscyf;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author yessenia.gonzalez
 */
public interface UsuarioRolSiscyfRepository extends JpaRepository <UsuarioRolSiscyf, Long>{
 
    @Query("SELECT ur.usuario FROM UsuarioRolSiscyf ur WHERE ur.usuario.username = :username")
    Usuario findOneByUsuario(@Param("username") String username);
    @Query("SELECT ur.rolsiscyf FROM UsuarioRolSiscyf ur WHERE ur.usuario.username = :username")
    List<RolSiscyf>  findByRolsiscyf(@Param("username") String username);
    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Usuario findOneByUsername(@Param("username") String username);
}
