/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.menu.service.impl;

import ec.gob.mdi.scsf.menu.entities.MenuSiscyf;
import ec.gob.mdi.scsf.menu.repository.MenuSiscyfRepository;
import ec.gob.mdi.scsf.menu.service.IMenuSiscyfService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author yessenia.gonzalez
 */
@Service
@RequiredArgsConstructor
public class MenuSiscyfServiceImpl implements IMenuSiscyfService{
    private final MenuSiscyfRepository repo;

    @Override
    public List<MenuSiscyf> listaMenuRolByUsuario(String username) {
        String contextSessionUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return repo.listaMenuRolByUsuario(contextSessionUser);
    }

    @Override
    public List<MenuSiscyf> listaMenuRolByUsuarioHijos(Integer id) {
        return repo.listaMenuRolByUsuarioHijos(id);
    }

}
