package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

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
	
	/**
	 * Cette méthode retourne une liste de 'météos' pour un département
	 * @param numeroDep - le numéro du département dont on veut récupérer la météo
	 * @return liste d'objets de type météo
	 */
	@Query(value = "FROM Meteo WHERE departement_id = :numeroDep")
	public List<Meteo> findMeteoByNumeroDep(Integer numeroDep);

}
