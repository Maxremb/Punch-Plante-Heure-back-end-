package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

/**
 * Cette interface manage la couche repository de l'entité Département.
 * @author Isaline MILLET
 *
 */
@Repository
public interface IDepartementRepository extends JpaRepository<Departement, Integer> {
	
	public List<Departement> findDepartmentByNumeroDep(Integer numeroDep);
	
	public List<Departement> findDepartementByNom(String nom);
	
	public List<Meteo> findMeteoByNumeroDep(Integer numeroDep);

}
