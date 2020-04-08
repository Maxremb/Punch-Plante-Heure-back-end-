package com.fr.adaming.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Departement;
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
	 * Methode retournant une liste de Meteo pour une date donnée en paramètre
	 * @param date date a laquelle on veut connaitre la meteo
	 * @param pageable caractéristiques de la pagination
	 * @return une page de Meteo pour tous les departements
	 */
	public Page<Meteo> findByDate(LocalDate date, Pageable pageable);
	
	/**
	 * Methode retournant une Météo pour une date et un departement donnés en paramètres
	 * @param date a laquelle on veut connaitre la meteo
	 * @param departement departement duquel on veut connaitre la meteo
	 * @return une entité Météo
	 */
	public Meteo findByDateAndDepartement(LocalDate date, Departement departement);
	
	/**
	 * Cette méthode retourne une liste de 'météos' pour un département
	 * @param numeroDep - le numéro du département dont on veut récupérer la météo
	 * @return liste d'objets de type météo
	 */
	@Query(value = "select * from meteo WHERE departement_id = :numeroDep", nativeQuery = true)
	public Page<Meteo> findMeteoByNumeroDep(Pageable pageable, Integer numeroDep);
	
	/** Prend les meteos dans la bd qui correspondent à un mois et à un departement
	 * @param month Le numéro du mois
	 * @param departementId Le numéro du département
	 * @return Une liste d'entités meteo
	 * 
	 * @author Gregoire
	 */
	@Query(value = "select * from meteo where month(date) = :month and departement_id = :departementId")
	public List<Meteo> findMeteoByMonthAndDepartement(int month, int departementId);

}
