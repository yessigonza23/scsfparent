/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.gob.mdi.scsf.menu.service;

import ec.gob.mdi.scsf.menu.entities.Usuario;

/**
 *
 * @author yessenia.gonzalez
 */
public interface IUsuarioService {
    Usuario usuarioByUsuario(String username);
    Usuario existsUsuario(String id);
}
