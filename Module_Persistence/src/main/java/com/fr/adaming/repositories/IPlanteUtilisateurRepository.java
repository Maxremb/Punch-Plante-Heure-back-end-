package com.fr.adaming.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.entity.User;

@Repository
public interface IPlanteUtilisateurRepository extends JpaRepository<PlanteUtilisateur, Long> {

}
