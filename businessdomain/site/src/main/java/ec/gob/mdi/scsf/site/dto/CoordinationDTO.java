/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Jose Alvear
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinationDTO {
    private Integer id;
    private String authority;
    private String name;
    private String email;
    private String authorityPosition;
    private String acronym;
}
