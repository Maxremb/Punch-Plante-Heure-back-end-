package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

	public List<User> findByNomAndPrenom(String nom, String prenom);
}
