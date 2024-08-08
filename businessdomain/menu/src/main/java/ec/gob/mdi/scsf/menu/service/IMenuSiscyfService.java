/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.gob.mdi.scsf.menu.service;

import ec.gob.mdi.scsf.menu.entities.MenuSiscyf;
import java.util.List;

/**
 *
 * @author yessenia.gonzalez
 */
public interface IMenuSiscyfService {

    List<MenuSiscyf> listaMenuRolByUsuario(String username);

    List<MenuSiscyf> listaMenuRolByUsuarioHijos(Integer id);

}
