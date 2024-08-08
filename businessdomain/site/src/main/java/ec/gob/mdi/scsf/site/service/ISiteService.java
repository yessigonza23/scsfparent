/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.site.service;

import ec.gob.mdi.scsf.site.dto.ResponseDTO;


/**
 *
 * @author Jose Alvear
 */
public interface ISiteService{
    
    ResponseDTO getSiteById(long id);
}
