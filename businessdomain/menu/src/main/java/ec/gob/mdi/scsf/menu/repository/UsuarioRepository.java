/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.menu.repository;


import ec.gob.mdi.scsf.menu.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author yessenia.gonzalez
 */
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
 
    @Query(value = "SELECT DISTINCT u FROM Usuario u where u.username =:username", nativeQuery = false)
    Usuario usuarioByUsername(@Param("username") String username);

    @Query(value = "SELECT us FROM Usuario us where us.username=:identificacion and us.estado<>'N'", nativeQuery = false)
    Usuario existsUsuario(@Param("identificacion")String identificacion);

    Usuario findOneByEmail(String username);

    @Query(value = "SELECT us FROM Usuario us where us.id=:id and us.estado<>'N'", nativeQuery = false)
    Usuario emailById(@Param("id")Integer id);
}
