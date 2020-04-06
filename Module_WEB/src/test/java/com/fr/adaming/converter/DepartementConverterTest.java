package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.MeteoUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Meteo;

/**
 * Classe de tests du converter Département Implémente l'interface
 * IConverterDepartementTests
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class DepartementConverterTest implements IConverterDepartementTests {

//	
//	Quand le converter Meteo sera fonctionnel, il faut passer les lignes en commentaires dans les assertions des méthodes en lignes fonctionnelles et supprimer les lignes au dessus de chaque commentaire.
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
	
	
	@Autowired
	private DepartementConverter departementConverter;
	
	@Autowired
	private MeteoConverter meteoConverter;

	@Override
	@Test
	public void testConvertingDtoToEntity_shouldReturnEntity() {
		DepartementDto dto = new DepartementDto();

		dto.setDepNum(1);
		dto.setName("nom4Test");
		dto.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		Departement returnDepartement = departementConverter.convertDtoToEntity(dto);

		assertThat(returnDepartement.getClass()).isEqualTo(Departement.class);
		assertThat(returnDepartement).hasFieldOrPropertyWithValue("numeroDep", dto.getDepNum());
		assertThat(returnDepartement).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnDepartement).hasFieldOrPropertyWithValue("meteoDep", dto.getWeatherDep());
//		assertThat(returnDepartement).hasFieldOrPropertyWithValue("meteoDep", meteoConverter.convertListUpdateDtoToEntity(dto.getWeatherDep()));
	}

	@Override
	@Test
	public void testConvertingNullDtoToEntity_shouldReturnNullEntity() {
		assertNull(departementConverter.convertDtoToEntity(null));
	}

	@Override
	@Test
	public void testConvertingEntityToDto_shouldReturnDto() {
		Departement dep = new Departement();

		dep.setNumeroDep(1);
		dep.setNom("nom4Test");
		dep.setMeteoDep(new ArrayList<Meteo>());

		DepartementDto returnedDto = departementConverter.convertEntityToDto(dep);

		assertThat(returnedDto.getClass()).isEqualTo(DepartementDto.class);
		assertThat(returnedDto).hasFieldOrPropertyWithValue("depNum", dep.getNumeroDep());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", dep.getNom());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("weatherDep", dep.getMeteoDep());
//		assertThat(returnedDto).hasFieldOrPropertyWithValue("weatherDep", meteoConverter.convertListEntityToUpdateDto(dep.getMeteoDep()));
	}

	@Override
	@Test
	public void testConvertingNullEntityToDto_shouldReturnNullDto() {
		assertNull(departementConverter.convertEntityToDto(null));
	}

	@Override
	@Test
	public void testConvertingListEntityToDto_shouldReturnDtoList() {
		Departement dep1 = new Departement();

		dep1.setNumeroDep(1);
		dep1.setNom("nom4Test1");
		dep1.setMeteoDep(new ArrayList<Meteo>());

		Departement dep2 = new Departement();

		dep2.setNumeroDep(2);
		dep2.setNom("nom4Test2");
		dep2.setMeteoDep(new ArrayList<Meteo>());

		List<Departement> liste = new ArrayList<>();
		liste.add(dep1);
		liste.add(dep2);

		List<DepartementDto> returnedListDto = departementConverter.convertListEntityToDto(liste);

		assertThat(returnedListDto).isNotNull().hasSize(2).hasOnlyElementsOfType(DepartementDto.class);
		assertThat(returnedListDto.get(0)).hasFieldOrPropertyWithValue("depNum", dep1.getNumeroDep());
		assertThat(returnedListDto.get(1)).hasFieldOrPropertyWithValue("depNum", dep2.getNumeroDep());
		assertThat(returnedListDto.get(0)).hasFieldOrPropertyWithValue("name", dep1.getNom());
		assertThat(returnedListDto.get(1)).hasFieldOrPropertyWithValue("name", dep2.getNom());
		assertThat(returnedListDto.get(0)).hasFieldOrPropertyWithValue("weatherDep", dep1.getMeteoDep());
//		assertThat(returnedListDto.get(0)).hasFieldOrPropertyWithValue("weatherDep", meteoConverter.convertListEntityToUpdateDto(dep1.getMeteoDep()));
		assertThat(returnedListDto.get(1)).hasFieldOrPropertyWithValue("weatherDep", dep2.getMeteoDep());
//		assertThat(returnedListDto.get(1)).hasFieldOrPropertyWithValue("weatherDep", meteoConverter.convertListEntityToUpdateDto(dep2.getMeteoDep()));
	}

	@Override
	@Test
	public void testConvertingEmptyListEntityToDto_shouldReturnEmptyList() {
		List<DepartementDto> returnedListDto = departementConverter
				.convertListEntityToDto(new ArrayList<Departement>());

		assertThat(returnedListDto).isEmpty();
	}

	@Override
	@Test
	public void testConvertingListDtoToEntity_shouldReturnEntityList() {
		DepartementDto dto1 = new DepartementDto();

		dto1.setDepNum(1);
		dto1.setName("nom4Test1");
		dto1.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		DepartementDto dto2 = new DepartementDto();

		dto2.setDepNum(2);
		dto2.setName("nom4Test2");
		dto2.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		List<DepartementDto> liste = new ArrayList<>();
		liste.add(dto1);
		liste.add(dto2);

		List<Departement> returnedListDep = departementConverter.convertListDtoToEntity(liste);

		assertThat(returnedListDep).isNotNull().hasSize(2).hasOnlyElementsOfType(Departement.class);
		assertThat(returnedListDep.get(0)).hasFieldOrPropertyWithValue("numeroDep", dto1.getDepNum());
		assertThat(returnedListDep.get(1)).hasFieldOrPropertyWithValue("numeroDep", dto2.getDepNum());
		assertThat(returnedListDep.get(0)).hasFieldOrPropertyWithValue("nom", dto1.getName());
		assertThat(returnedListDep.get(1)).hasFieldOrPropertyWithValue("nom", dto2.getName());
		assertThat(returnedListDep.get(0)).hasFieldOrPropertyWithValue("meteoDep", dto1.getWeatherDep());
//		assertThat(returnedListDep.get(0)).hasFieldOrPropertyWithValue("meteoDep", meteoConverter.convertListUpdateDtoToEntity(dto1.getWeatherDep()));
		assertThat(returnedListDep.get(1)).hasFieldOrPropertyWithValue("meteoDep", dto2.getWeatherDep());
//		assertThat(returnedListDep.get(1)).hasFieldOrPropertyWithValue("meteoDep", meteoConverter.convertListUpdateDtoToEntity(dto2.getWeatherDep()));
	}

	@Override
	@Test
	public void testConvertingEmptyListDtoToEntity_shouldReturnEmptyList() {
		List<Departement> returnedListDep = departementConverter
				.convertListDtoToEntity(new ArrayList<DepartementDto>());

		assertThat(returnedListDep).isEmpty();

	}

	@Override
	@Test
	public void testConvertingPageDtoToEntity_shouldReturnPageOfEntities() {
		DepartementDto dto1 = new DepartementDto();

		dto1.setDepNum(1);
		dto1.setName("nom4Test1");
		dto1.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		DepartementDto dto2 = new DepartementDto();

		dto2.setDepNum(2);
		dto2.setName("nom4Test2");
		dto2.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		List<DepartementDto> liste = new ArrayList<>();
		liste.add(dto1);
		liste.add(dto2);
		Page<DepartementDto> page = new PageImpl<DepartementDto>(liste);

		Page<Departement> returnedPageDep = departementConverter.convertPageDtoToEntity(page);

		assertThat(returnedPageDep).isNotNull();
		assertThat(returnedPageDep.toList()).hasSize(2).hasOnlyElementsOfType(Departement.class);
		assertThat(returnedPageDep.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageDep.getNumber()).isEqualTo(0);;
		assertThat(returnedPageDep.toList().get(0)).hasFieldOrPropertyWithValue("numeroDep", dto1.getDepNum());
		assertThat(returnedPageDep.toList().get(1)).hasFieldOrPropertyWithValue("numeroDep", dto2.getDepNum());
		assertThat(returnedPageDep.toList().get(0)).hasFieldOrPropertyWithValue("nom", dto1.getName());
		assertThat(returnedPageDep.toList().get(1)).hasFieldOrPropertyWithValue("nom", dto2.getName());
		assertThat(returnedPageDep.toList().get(0)).hasFieldOrPropertyWithValue("meteoDep", dto1.getWeatherDep());
//		assertThat(returnedListDep.toList().get(0)).hasFieldOrPropertyWithValue("meteoDep", meteoConverter.convertListUpdateDtoToEntity(dto1.getWeatherDep()));
		assertThat(returnedPageDep.toList().get(1)).hasFieldOrPropertyWithValue("meteoDep", dto2.getWeatherDep());
//		assertThat(returnedListDep.toList().get(1)).hasFieldOrPropertyWithValue("meteoDep", meteoConverter.convertListUpdateDtoToEntity(dto2.getWeatherDep()));

	}

	@Override
	@Test
	public void testConvertingPageEntityToDto_shouldReturnPageOfDtos() {
		Departement dep1 = new Departement();

		dep1.setNumeroDep(1);
		dep1.setNom("nom4Test1");
		dep1.setMeteoDep(new ArrayList<Meteo>());

		Departement dep2 = new Departement();

		dep2.setNumeroDep(2);
		dep2.setNom("nom4Test2");
		dep2.setMeteoDep(new ArrayList<Meteo>());

		List<Departement> liste = new ArrayList<>();
		liste.add(dep1);
		liste.add(dep2);
		Page<Departement> page = new PageImpl<Departement>(liste);

		Page<DepartementDto> returnedPageDto = departementConverter.convertPageEntityToDto(page);

		assertThat(returnedPageDto).isNotNull();
		assertThat(returnedPageDto.toList()).hasSize(2).hasOnlyElementsOfType(DepartementDto.class);
		assertThat(returnedPageDto.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageDto.getNumber()).isEqualTo(0);
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("depNum", dep1.getNumeroDep());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("depNum", dep2.getNumeroDep());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("name", dep1.getNom());
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("name", dep2.getNom());
		assertThat(returnedPageDto.toList().get(0)).hasFieldOrPropertyWithValue("weatherDep", dep1.getMeteoDep());
//		assertThat(returnedListDto.toList().get(0)).hasFieldOrPropertyWithValue("weatherDep", meteoConverter.convertListEntityToUpdateDto(dep1.getMeteoDep()));
		assertThat(returnedPageDto.toList().get(1)).hasFieldOrPropertyWithValue("weatherDep", dep2.getMeteoDep());
//		assertThat(returnedListDto.toList().get(1)).hasFieldOrPropertyWithValue("weatherDep", meteoConverter.convertListEntityToUpdateDto(dep2.getMeteoDep()));

	}

}
