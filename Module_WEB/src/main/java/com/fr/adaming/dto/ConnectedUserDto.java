package com.fr.adaming.dto;

import com.fr.adaming.enums.Role;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto pour la transmission de données de session au front
 * @author Gregoire
 *
 */
@Getter @Setter
public class ConnectedUserDto {
	
	private int id;
	private String mail;
	private Role role = Role.None;
	private String pseudo; 

}
