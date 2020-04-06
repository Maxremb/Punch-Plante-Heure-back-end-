package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe est le modèle de l'entité Département. 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@Getter @Setter
@EqualsAndHashCode
@Entity
public class Departement {
	
	@Id
	private Integer numeroDep;
	
	@Column(nullable = false, length = 100, unique = true)
	private String nom;

}
