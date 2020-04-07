package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.DepartementDto;
import com.fr.adaming.dto.MeteoCreateDto;
import com.fr.adaming.dto.MeteoUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;

/**
 * Classe de tests du converter Meteo
 * Implémente l'interface IConverterTests
 * 
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class MeteoConverterTests implements IConverterTests {
	
	@Autowired
	private MeteoConverter converterM;
	
	@Autowired
	private DepartementConverter converterD;

	@Test
	@Override
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(69);
		dtoD.setName("rhone");
		
		MeteoCreateDto dtoM = new MeteoCreateDto();
		dtoM.setTempMax(25d);
		dtoM.setTempMin(20d);
		dtoM.setRain(5d);
		dtoM.setRadiation(210d);
		dtoM.setEtp(5d);
		dtoM.setDateMeteo(LocalDate.parse("2020-01-01"));
		dtoM.setDepartement(dtoD);
		
		Meteo retour = converterM.convertCreateDtoToEntity(dtoM);
		
		assertThat(retour.getClass()).isEqualTo(Meteo.class);
		assertThat(retour.getTemperatureMax()).isEqualTo(25d);
		assertThat(retour.getDepartement()).isEqualTo(converterD.convertDtoToEntity(dtoD));
		
	}

	@Test
	@Override
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		Meteo retour = converterM.convertCreateDtoToEntity(null);
		assertNull(retour);
		
	}

	@Test
	@Override
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(69);
		dtoD.setName("rhone");
		
		MeteoUpdateDto dtoM = new MeteoUpdateDto();
		dtoM.setIdentifier(1);
		dtoM.setTempMax(25d);
		dtoM.setTempMin(20d);
		dtoM.setRain(5d);
		dtoM.setRadiation(210d);
		dtoM.setEtp(5d);
		dtoM.setDateMeteo(LocalDate.parse("2020-01-01"));
		dtoM.setDepartement(dtoD);
		
		Meteo retour = converterM.convertUpdateDtoToEntity(dtoM);
		
		assertThat(retour.getClass()).isEqualTo(Meteo.class);
		assertThat(retour.getTemperatureMax()).isEqualTo(25d);
		assertThat(retour.getId()).isEqualTo(1);
		assertThat(retour.getDepartement()).isEqualTo(converterD.convertDtoToEntity(dtoD));
	}
	
	@Test
	@Override
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		Meteo retour = converterM.convertUpdateDtoToEntity(null);
		assertNull(retour);
	}
	
	@Test
	@Override
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo = new Meteo();
		meteo.setTemperatureMax(25d);
		meteo.setTemperatureMin(20d);
		meteo.setPluie(5d);
		meteo.setEnsoleillement(120d);
		meteo.setEvapoTranspirationPotentielle(5d);
		meteo.setDate(LocalDate.parse("2020-01-01"));
		meteo.setDepartement(departement);
		
		MeteoCreateDto retour = converterM.convertEntityToCreateDto(meteo);
		
		assertThat(retour.getClass()).isEqualTo(MeteoCreateDto.class);
		assertThat(retour.getTempMax()).isEqualTo(25d);
		assertThat(retour.getDepartement()).isEqualTo(converterD.convertEntityToDto(departement));
		
	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		MeteoCreateDto retour = converterM.convertEntityToCreateDto(null);
		assertNull(retour);
		
	}
	
	@Test
	@Override
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo = new Meteo();
		meteo.setId(1);
		meteo.setTemperatureMax(25d);
		meteo.setTemperatureMin(20d);
		meteo.setPluie(5d);
		meteo.setEnsoleillement(120d);
		meteo.setEvapoTranspirationPotentielle(5d);
		meteo.setDate(LocalDate.parse("2020-01-01"));
		meteo.setDepartement(departement);
		
		MeteoUpdateDto retour = converterM.convertEntityToUpdateDto(meteo);
		
		assertThat(retour.getClass()).isEqualTo(MeteoUpdateDto.class);
		assertThat(retour.getTempMax()).isEqualTo(25d);
		assertThat(retour.getIdentifier()).isEqualTo(1);
		assertThat(retour.getDepartement()).isEqualTo(converterD.convertEntityToDto(departement));
		
	}
	
	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		MeteoUpdateDto retour = converterM.convertEntityToUpdateDto(null);
		assertNull(retour);
	}

	@Test
	@Override
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo1 = new Meteo();
		meteo1.setTemperatureMax(25d);
		meteo1.setTemperatureMin(20d);
		meteo1.setPluie(5d);
		meteo1.setEnsoleillement(120d);
		meteo1.setEvapoTranspirationPotentielle(5d);
		meteo1.setDate(LocalDate.parse("2020-01-01"));
		meteo1.setDepartement(departement);
		
		Meteo meteo2 = new Meteo();
		meteo2.setTemperatureMax(30d);
		meteo2.setTemperatureMin(20d);
		meteo2.setPluie(5d);
		meteo2.setEnsoleillement(120d);
		meteo2.setEvapoTranspirationPotentielle(5d);
		meteo2.setDate(LocalDate.parse("2020-05-05"));
		meteo2.setDepartement(departement);
		
		List<Meteo> liste = new ArrayList<>();
		liste.add(meteo1);
		liste.add(meteo2);
		
		List<MeteoCreateDto> retour = converterM.convertListEntityToCreateDto(liste);
		
		assertThat(retour).hasSize(2).hasOnlyElementsOfType(MeteoCreateDto.class);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("departement", converterD.convertEntityToDto(departement));
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("tempMax", 25d);
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("departement", converterD.convertEntityToDto(departement));
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("tempMax", 30d);
	}

	@Test
	@Override
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		List<MeteoCreateDto> retour = converterM.convertListEntityToCreateDto(null);
		assertTrue(retour.isEmpty());
	}

	@Test
	@Override
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(69);
		dtoD.setName("rhone");
		
		MeteoCreateDto dtoM1 = new MeteoCreateDto();
		dtoM1.setTempMax(25d);
		dtoM1.setTempMin(20d);
		dtoM1.setRain(5d);
		dtoM1.setRadiation(210d);
		dtoM1.setEtp(5d);
		dtoM1.setDateMeteo(LocalDate.parse("2020-01-01"));
		dtoM1.setDepartement(dtoD);
		
		MeteoCreateDto dtoM2 = new MeteoCreateDto();
		dtoM2.setTempMax(30d);
		dtoM2.setTempMin(20d);
		dtoM2.setRain(5d);
		dtoM2.setRadiation(210d);
		dtoM2.setEtp(5d);
		dtoM2.setDateMeteo(LocalDate.parse("2020-05-05"));
		dtoM2.setDepartement(dtoD);
		
		List<MeteoCreateDto> liste = new ArrayList<>();
		liste.add(dtoM1);
		liste.add(dtoM2);
		
		List<Meteo> retour = converterM.convertListCreateDtoToEntity(liste);
		
		assertThat(retour).hasSize(2).hasOnlyElementsOfType(Meteo.class);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("departement", converterD.convertDtoToEntity(dtoD));
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("temperatureMax", 25d);
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("departement", converterD.convertDtoToEntity(dtoD));
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("temperatureMax", 30d);
	}

	@Test
	@Override
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		List<Meteo> retour = converterM.convertListCreateDtoToEntity(null);
		assertTrue(retour.isEmpty());
		
	}
	
	@Test
	@Override
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo1 = new Meteo();
		meteo1.setId(1);
		meteo1.setTemperatureMax(25d);
		meteo1.setTemperatureMin(20d);
		meteo1.setPluie(5d);
		meteo1.setEnsoleillement(120d);
		meteo1.setEvapoTranspirationPotentielle(5d);
		meteo1.setDate(LocalDate.parse("2020-01-01"));
		meteo1.setDepartement(departement);
		
		Meteo meteo2 = new Meteo();
		meteo2.setId(2);
		meteo2.setTemperatureMax(30d);
		meteo2.setTemperatureMin(20d);
		meteo2.setPluie(5d);
		meteo2.setEnsoleillement(120d);
		meteo2.setEvapoTranspirationPotentielle(5d);
		meteo2.setDate(LocalDate.parse("2020-05-05"));
		meteo2.setDepartement(departement);
		
		List<Meteo> liste = new ArrayList<>();
		liste.add(meteo1);
		liste.add(meteo2);
		
		List<MeteoUpdateDto> retour = converterM.convertListEntityToUpdateDto(liste);
		
		assertThat(retour).hasSize(2).hasOnlyElementsOfType(MeteoUpdateDto.class);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("identifier", 1);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("departement", converterD.convertEntityToDto(departement));
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("tempMax", 25d);
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("identifier", 2);
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("departement", converterD.convertEntityToDto(departement));
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("tempMax", 30d);
		
	}

	@Test
	@Override
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		List<MeteoUpdateDto> retour = converterM.convertListEntityToUpdateDto(null);
		assertTrue(retour.isEmpty());
		
	}

	@Test
	@Override
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(69);
		dtoD.setName("rhone");
		
		MeteoUpdateDto dtoM1 = new MeteoUpdateDto();
		dtoM1.setIdentifier(1);
		dtoM1.setTempMax(25d);
		dtoM1.setTempMin(20d);
		dtoM1.setRain(5d);
		dtoM1.setRadiation(210d);
		dtoM1.setEtp(5d);
		dtoM1.setDateMeteo(LocalDate.parse("2020-01-01"));
		dtoM1.setDepartement(dtoD);
		
		MeteoUpdateDto dtoM2 = new MeteoUpdateDto();
		dtoM2.setIdentifier(2);
		dtoM2.setTempMax(30d);
		dtoM2.setTempMin(20d);
		dtoM2.setRain(5d);
		dtoM2.setRadiation(210d);
		dtoM2.setEtp(5d);
		dtoM2.setDateMeteo(LocalDate.parse("2020-05-05"));
		dtoM2.setDepartement(dtoD);
		
		List<MeteoUpdateDto> liste = new ArrayList<>();
		liste.add(dtoM1);
		liste.add(dtoM2);
		
		List<Meteo> retour = converterM.convertListUpdateDtoToEntity(liste);
		
		assertThat(retour).hasSize(2).hasOnlyElementsOfType(Meteo.class);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("departement", converterD.convertDtoToEntity(dtoD));
		assertThat(retour.get(0)).hasFieldOrPropertyWithValue("temperatureMax", 25d);
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("id", 2);
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("departement", converterD.convertDtoToEntity(dtoD));
		assertThat(retour.get(1)).hasFieldOrPropertyWithValue("temperatureMax", 30d);
		
	}
	
	@Test
	@Override
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		List<Meteo> retour = converterM.convertListUpdateDtoToEntity(null);
		assertTrue(retour.isEmpty());
	}
	
	@Test
	@Override
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(69);
		dtoD.setName("rhone");
		
		MeteoCreateDto dtoM1 = new MeteoCreateDto();
		dtoM1.setTempMax(25d);
		dtoM1.setTempMin(20d);
		dtoM1.setRain(5d);
		dtoM1.setRadiation(210d);
		dtoM1.setEtp(5d);
		dtoM1.setDateMeteo(LocalDate.parse("2020-01-01"));
		dtoM1.setDepartement(dtoD);
		
		MeteoCreateDto dtoM2 = new MeteoCreateDto();
		dtoM2.setTempMax(30d);
		dtoM2.setTempMin(20d);
		dtoM2.setRain(5d);
		dtoM2.setRadiation(210d);
		dtoM2.setEtp(5d);
		dtoM2.setDateMeteo(LocalDate.parse("2020-05-05"));
		dtoM2.setDepartement(dtoD);
		
		List<MeteoCreateDto> liste = new ArrayList<>();
		liste.add(dtoM1);
		liste.add(dtoM2);
		Page<MeteoCreateDto> page = new PageImpl<>(liste);
		
		Page<Meteo> retour = converterM.convertPageCreateDtoToEntity(page);
		
		assertThat(retour.toList()).hasSize(2).hasOnlyElementsOfType(Meteo.class);
		assertThat(retour.getNumberOfElements()).isEqualTo(2);
		assertThat(retour.getNumber()).isEqualTo(0);
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("departement", converterD.convertDtoToEntity(dtoD));
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("temperatureMax", 25d);
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("departement", converterD.convertDtoToEntity(dtoD));
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("temperatureMax", 30d);
		
	}
	
	@Test
	@Override
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage() {
		Page<Meteo> retour = converterM.convertPageCreateDtoToEntity(null);
		assertTrue(retour.isEmpty());
		
	}
	
	@Test
	@Override
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo1 = new Meteo();
		meteo1.setTemperatureMax(25d);
		meteo1.setTemperatureMin(20d);
		meteo1.setPluie(5d);
		meteo1.setEnsoleillement(120d);
		meteo1.setEvapoTranspirationPotentielle(5d);
		meteo1.setDate(LocalDate.parse("2020-01-01"));
		meteo1.setDepartement(departement);
		
		Meteo meteo2 = new Meteo();
		meteo2.setTemperatureMax(30d);
		meteo2.setTemperatureMin(20d);
		meteo2.setPluie(5d);
		meteo2.setEnsoleillement(120d);
		meteo2.setEvapoTranspirationPotentielle(5d);
		meteo2.setDate(LocalDate.parse("2020-05-05"));
		meteo2.setDepartement(departement);
		
		List<Meteo> liste = new ArrayList<>();
		liste.add(meteo1);
		liste.add(meteo2);
		Page<Meteo> page = new PageImpl<>(liste);
		
		Page<MeteoCreateDto> retour = converterM.convertPageEntityToCreateDto(page);
		
		assertThat(retour).hasSize(2).hasOnlyElementsOfType(MeteoCreateDto.class);
		assertThat(retour.getNumberOfElements()).isEqualTo(2);
		assertThat(retour.getNumber()).isEqualTo(0);
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("departement", converterD.convertEntityToDto(departement));
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("tempMax", 25d);
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("departement", converterD.convertEntityToDto(departement));
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("tempMax", 30d);
		
		
	}
	
	@Test
	@Override
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage() {
		Page<MeteoCreateDto> retour = converterM.convertPageEntityToCreateDto(null);
		assertTrue(retour.isEmpty());
		
	}
	
	@Test
	@Override
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		DepartementDto dtoD = new DepartementDto();
		dtoD.setDepNum(69);
		dtoD.setName("rhone");
		
		MeteoUpdateDto dtoM1 = new MeteoUpdateDto();
		dtoM1.setIdentifier(1);
		dtoM1.setTempMax(25d);
		dtoM1.setTempMin(20d);
		dtoM1.setRain(5d);
		dtoM1.setRadiation(210d);
		dtoM1.setEtp(5d);
		dtoM1.setDateMeteo(LocalDate.parse("2020-01-01"));
		dtoM1.setDepartement(dtoD);
		
		MeteoUpdateDto dtoM2 = new MeteoUpdateDto();
		dtoM2.setIdentifier(2);
		dtoM2.setTempMax(30d);
		dtoM2.setTempMin(20d);
		dtoM2.setRain(5d);
		dtoM2.setRadiation(210d);
		dtoM2.setEtp(5d);
		dtoM2.setDateMeteo(LocalDate.parse("2020-05-05"));
		dtoM2.setDepartement(dtoD);
		
		List<MeteoUpdateDto> liste = new ArrayList<>();
		liste.add(dtoM1);
		liste.add(dtoM2);
		Page<MeteoUpdateDto> page = new PageImpl<>(liste);
		
		Page<Meteo> retour = converterM.convertPageUpdateDtoToEntity(page);
		
		assertThat(retour).hasSize(2).hasOnlyElementsOfType(Meteo.class);
		assertThat(retour.getNumberOfElements()).isEqualTo(2);
		assertThat(retour.getNumber()).isEqualTo(0);
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("id", 1);
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("departement", converterD.convertDtoToEntity(dtoD));
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("temperatureMax", 25d);
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("id", 2);
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("departement", converterD.convertDtoToEntity(dtoD));
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("temperatureMax", 30d);
		
		
	}
	
	@Test
	@Override
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage() {
		Page<Meteo> retour = converterM.convertPageUpdateDtoToEntity(null);
		assertTrue(retour.isEmpty());
		
	}
	
	@Test
	@Override
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		Departement departement = new Departement();
		departement.setNumeroDep(69);
		departement.setNom("rhone");
		
		Meteo meteo1 = new Meteo();
		meteo1.setId(1);
		meteo1.setTemperatureMax(25d);
		meteo1.setTemperatureMin(20d);
		meteo1.setPluie(5d);
		meteo1.setEnsoleillement(120d);
		meteo1.setEvapoTranspirationPotentielle(5d);
		meteo1.setDate(LocalDate.parse("2020-01-01"));
		meteo1.setDepartement(departement);
		
		Meteo meteo2 = new Meteo();
		meteo2.setId(2);
		meteo2.setTemperatureMax(30d);
		meteo2.setTemperatureMin(20d);
		meteo2.setPluie(5d);
		meteo2.setEnsoleillement(120d);
		meteo2.setEvapoTranspirationPotentielle(5d);
		meteo2.setDate(LocalDate.parse("2020-05-05"));
		meteo2.setDepartement(departement);
		
		List<Meteo> liste = new ArrayList<>();
		liste.add(meteo1);
		liste.add(meteo2);
		Page<Meteo> page = new PageImpl<>(liste);
		
		Page<MeteoUpdateDto> retour = converterM.convertPageEntityToUpdateDto(page);
		
		assertThat(retour).hasSize(2).hasOnlyElementsOfType(MeteoUpdateDto.class);
		assertThat(retour.getNumberOfElements()).isEqualTo(2);
		assertThat(retour.getNumber()).isEqualTo(0);
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("identifier", 1);
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("departement", converterD.convertEntityToDto(departement));
		assertThat(retour.toList().get(0)).hasFieldOrPropertyWithValue("tempMax", 25d);
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("identifier", 2);
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("departement", converterD.convertEntityToDto(departement));
		assertThat(retour.toList().get(1)).hasFieldOrPropertyWithValue("tempMax", 30d);
		
		
	}
	
	@Test
	@Override
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage() {
		Page<MeteoUpdateDto> retour = converterM.convertPageEntityToUpdateDto(null);
		assertTrue(retour.isEmpty());
		
	}

}
