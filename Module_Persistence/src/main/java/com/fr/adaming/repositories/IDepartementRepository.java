package com.fr.adaming.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Departement;

/**
 * Cette interface manage la couche repository de l'entité Département.
 * @author Isaline MILLET
 * @since 0.0.1
 */
@Repository
public interface IDepartementRepository extends JpaRepository<Departement, Integer> {
	
	/**
	 * Cette méthode retourne une liste de départements via une recherche par nom
	 * @param nom - le nom du département recherché
	 * @return Département
	 */
	public Departement findDepartementByNom(String nom);

}
