package com.fr.adaming.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fr.adaming.enums.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Stoque les données pour une session d'un utilisateur. Remplace ConnectedUser, en enlevant le token qui était redondant
 * @author Gregoire
 *
 */
@SessionScope
@Component
@Setter
@Getter
@ToString
public class ConnectedUser2 {
	
	private int identifier;
	private String mail;
	private String pseudo;
	private Role role = Role.None;	

}
