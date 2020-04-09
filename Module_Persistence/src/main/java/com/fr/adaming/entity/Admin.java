package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentative de l'entité persistante Admin
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column (nullable = false)
	private String mdp;
	@Column (nullable = false, unique = true)
	private String pseudonyme;
	@Column (nullable = false, unique = true)
	private String email;

}
