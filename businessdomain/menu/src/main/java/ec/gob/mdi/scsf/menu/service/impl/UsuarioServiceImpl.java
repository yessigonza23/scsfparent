/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.menu.service.impl;

import ec.gob.mdi.scsf.menu.entities.Usuario;
import ec.gob.mdi.scsf.menu.repository.UsuarioRepository;
import ec.gob.mdi.scsf.menu.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author yessenia.gonzalez
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService{

    private final UsuarioRepository repo;

    public Usuario usuarioByUsuario(String username) {
        return repo.usuarioByUsername(username);
    }

    public Usuario existsUsuario(String id) {
        return repo.existsUsuario(id);
    }
}
