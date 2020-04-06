package com.fr.adaming.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Meteo;


/**
 * Interface Repository responsable de la communication entre la BD et la couche Service
 * Etend l'interface JpaRepository
 * @author Jeanne-Marie Mathevet
 * @since 0.0.1-SNAPSHOT
 */
@Repository
public interface IMeteoRepository extends JpaRepository<Meteo, Integer>{
	
	/**
	 * Methode retournant une entité de type Meteo pour une date donnée en paramètre
	 * @param date date a laquelle on veut connaitre la meteo
	 * @return une entité de type Meteo
	 */
	public Meteo findByDate(LocalDate date);

}
