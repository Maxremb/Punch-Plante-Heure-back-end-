package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.TypePeriod;

//TODO convertir listes en Pages

@Repository
public interface IPeriodeRepository extends JpaRepository<Periode, Integer>{
	
	public Page<Periode> findByDepartement(Pageable pageable, Departement departement);
	public Page<Periode> findByPlanteModel(Pageable pageable, PlanteModel planteModel);
	public List<Periode> findByDepartementAndPlanteModel(Departement departement, PlanteModel planteModel);
	public Periode findByDepartementAndPlanteModelAndType(Departement departement, PlanteModel planteModel, TypePeriod type);

}
