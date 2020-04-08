package com.fr.adaming.controller.impl;

import java.time.LocalDate;

import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.dto.PlanteModelUpdateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;

public class PeriodeControllerTest extends AbstractTestMethods<PeriodeUpdateDto> implements IControllerTests {

	
	private static final int identifier =1;
	
	// paramètres periodeUpdateDTO
	private static final LocalDate startDate = (LocalDate.parse("2020-01-01"));
	private static final LocalDate endDate = (LocalDate.parse("2020-02-01"));

	// paramètres pour dept
	private static final int depNum = 74;
	private static final String depName = "Haute-Savoie";
	private static final String depNameSql = "'" + depName + "'";

	// paramètre PlanteModelUpdateDto
	private static final String scientifique = "plante carnivore";
	private static final String commun = "carnivorus plantus communus";

	@Override
	public void testCreatingEntityWithValidBody_shouldReturn200() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturn400() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testDeletingEntityWithValidId_shouldReturn200() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testDeletingEntityWithInvalidId_shouldReturn400() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testUpdatingEntityWithValidId_shouldReturn200() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturn400() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testReadingEntityWithValidId_shouldReturn200() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testReadingEntityWithInvalidId_shouldReturn400() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void testReadingAllEntity_shouldReturn200() throws Exception {
		// TODO Auto-generated method stub

	}

	// *** Méthodes privés ***

	private PeriodeUpdateDto makeNewUpdateDto() {
		// Creation du dto qu'on va utiliser pour la requete et aussi la comparaison

		DepartementDto depDto = new DepartementDto();

		depDto.setDepNum(depNum);
		depDto.setName(depName);

		PlanteModelUpdateDto planteDto = new PlanteModelUpdateDto();
		planteDto.setIdentifiant(identifier);
		planteDto.setCommun(commun);
		planteDto.setScientifique(scientifique);

		PeriodeUpdateDto dto = new PeriodeUpdateDto();
		dto.setIdentity(identifier);
		dto.setEndDate(endDate);
		dto.setStartDate(startDate);
		dto.setPlantSpecies(planteDto);
		dto.setCounty(depDto);

		return dto;

	}

}
