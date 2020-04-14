package com.fr.adaming.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Retention;
import com.fr.adaming.enums.Sol;

/**
 * Interface repository responsable de la communication avec la base de données pour l'entité Retention
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@Repository
public interface IRetentionRepository extends JpaRepository<Retention, Integer>{
	
	/**
	 * Permet la recherche d'une rétention par type de sol
	 * @param sol à rechercher
	 * @return un objet de type Retention correspondant au type de sol correspondant
	 */
	public Retention findBySol (Sol sol);

	/**
	 * Vérifie l'existence d'un sol dans la base de données
	 * @param sol du jardin à vérifier
	 * @return true si l'objet existe, false sinon
	 */
	public boolean existsBySol (Sol sol);
}
