package com.fr.adaming.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.ModulePersistenceApplication;
import com.fr.adaming.entity.PlanteModel;
/**
 * Cette classe teste la couche repository de l'entité PlanteModel
 * @author Léa Coston
 * @since 0.0.1
 */
@SpringBootTest(classes = ModulePersistenceApplication.class)
public class IPlanteModelRepositoryTests {

	@Autowired
	private IPlanteModelRepository repo;

	@Sql(scripts = "classpath:plante_model_names.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from plante_model", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testGettingPageOf10ItemsOfPlants_shouldReturnPageOfTenPlantsSortedByNomScientifique() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("nomScientifique"));
		
		Page<PlanteModel> page = repo.findAll(pageable);

		System.out.println("debug page : " + page.getContent());
		System.out.println("debug total page number : " + page.getTotalPages());
		
		
		assertThat(page).isNotNull();
		assertThat(page.getSize()).isEqualTo(3);
		assertThat(page.getNumber()).isEqualTo(0);
		
		
	}
}
