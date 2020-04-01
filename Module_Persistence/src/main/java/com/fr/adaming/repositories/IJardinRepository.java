package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.User;

@Repository
public interface IJardinRepository extends JpaRepository<Jardin, Long>{

	public List<Jardin> findByNom(String nom);
	
	@Query(value = "from Jardin where id= :idUtilisateur")
	public List<Jardin> findByUtilisateur(@Param(value = "idUtilisateur") Integer idUtilisateur);
	
	@Query(value = "from Jardin where id= :idUtilisateur")
	public List<Jardin> findByDepartement(@Param(value = "idUtilisateur") Integer idUtilisateur);
	
	
}
