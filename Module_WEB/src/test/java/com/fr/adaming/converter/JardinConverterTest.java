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
import com.fr.adaming.dto.JardinCreateDto;
import com.fr.adaming.dto.JardinUpdateDto;
import com.fr.adaming.dto.MeteoUpdateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Meteo;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.enums.Sol;

/**
 * Classe de tests du converter Jardin Implémente l'interface IConverterTests
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class JardinConverterTest implements IConverterTests {

	@Autowired
	private JardinConverter jardinConverter;

	@Autowired
	private DepartementConverter departementConverter;

	@Autowired
	private UtilisateurConverter utilisateurConverteur;

	@Override
	@Test
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		JardinCreateDto dto = new JardinCreateDto();

		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");
		depDto.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		UtilisateurUpdateDto utilUpDto = new UtilisateurUpdateDto();
		utilUpDto.setIdentifier(1);

		dto.setGround(Sol.Argileux);
		dto.setName("nom4Test");
		dto.setLength((float) 10.0);
		dto.setWidth((float) 10.0);
		dto.setDept(depDto);
		dto.setUser(utilUpDto);

		Jardin returnedJardin = jardinConverter.convertCreateDtoToEntity(dto);

		assertThat(returnedJardin.getClass()).isEqualTo(Jardin.class);
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("sol", dto.getGround());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("longueur", dto.getLength());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("largeur", dto.getWidth());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto.getDept()));
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto.getUser()));
	}

	@Override
	@Test
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		assertNull(jardinConverter.convertCreateDtoToEntity(null));
	}

	@Override
	@Test
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		JardinUpdateDto dto = new JardinUpdateDto();

		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");
		depDto.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		UtilisateurUpdateDto utilUpDto = new UtilisateurUpdateDto();
		utilUpDto.setIdentifier(1);

		dto.setIdentifier(1);
		dto.setGround(Sol.Argileux);
		dto.setName("nom4Test");
		dto.setLength((float) 10.0);
		dto.setWidth((float) 10.0);
		dto.setDept(depDto);
		dto.setUser(utilUpDto);

		Jardin returnedJardin = jardinConverter.convertUpdateDtoToEntity(dto);

		assertThat(returnedJardin.getClass()).isEqualTo(Jardin.class);
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("id", dto.getIdentifier());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("sol", dto.getGround());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("longueur", dto.getLength());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("largeur", dto.getWidth());
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto.getDept()));
		assertThat(returnedJardin).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto.getUser()));
	}

	@Override
	@Test
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		assertNull(jardinConverter.convertUpdateDtoToEntity(null));
	}

	@Override
	@Test
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		Jardin jardin = new Jardin();

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomDep4Test");
		dep.setMeteoDep(new ArrayList<Meteo>());

		Utilisateur util = new Utilisateur();
		util.setId(1);

		jardin.setId(1);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		JardinCreateDto returnedJardinCDto = jardinConverter.convertEntityToCreateDto(jardin);

		assertThat(returnedJardinCDto.getClass()).isEqualTo(JardinCreateDto.class);
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("ground", jardin.getSol());
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("name", jardin.getNom());
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("length", jardin.getLongueur());
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("width", jardin.getLargeur());
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin.getDepartement()));
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin.getUtilisateur()));
	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		assertNull(jardinConverter.convertEntityToCreateDto(null));
	}

	@Override
	@Test
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		Jardin jardin = new Jardin();

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomDep4Test");
		dep.setMeteoDep(new ArrayList<Meteo>());

		Utilisateur util = new Utilisateur();
		util.setId(1);

		jardin.setId(1);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		JardinCreateDto returnedJardinCDto = jardinConverter.convertEntityToUpdateDto(jardin);

		assertThat(returnedJardinCDto.getClass()).isEqualTo(JardinUpdateDto.class);
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("ground", jardin.getSol());
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("name", jardin.getNom());
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("length", jardin.getLongueur());
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("width", jardin.getLargeur());
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin.getDepartement()));
		assertThat(returnedJardinCDto).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin.getUtilisateur()));
	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		assertNull(jardinConverter.convertEntityToUpdateDto(null));
	}

	@Override
	@Test
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		Jardin jardin = new Jardin();

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomDep4Test");
		dep.setMeteoDep(new ArrayList<Meteo>());

		Utilisateur util = new Utilisateur();
		util.setId(1);

		jardin.setId(1);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		Jardin jardin2 = new Jardin();

		jardin.setId(2);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test2");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		List<Jardin> liste = new ArrayList<>();
		liste.add(jardin);
		liste.add(jardin2);

		List<JardinCreateDto> returnedListeCDto = jardinConverter.convertListEntityToCreateDto(liste);

		assertThat(returnedListeCDto).hasSize(2).hasOnlyElementsOfType(JardinCreateDto.class);
		assertThat(returnedListeCDto.get(0)).hasFieldOrPropertyWithValue("ground", jardin.getSol());
		assertThat(returnedListeCDto.get(0)).hasFieldOrPropertyWithValue("name", jardin.getNom());
		assertThat(returnedListeCDto.get(0)).hasFieldOrPropertyWithValue("length", jardin.getLongueur());
		assertThat(returnedListeCDto.get(0)).hasFieldOrPropertyWithValue("width", jardin.getLargeur());
		assertThat(returnedListeCDto.get(0)).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin.getDepartement()));
		assertThat(returnedListeCDto.get(0)).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin.getUtilisateur()));
		assertThat(returnedListeCDto.get(1)).hasFieldOrPropertyWithValue("ground", jardin2.getSol());
		assertThat(returnedListeCDto.get(1)).hasFieldOrPropertyWithValue("name", jardin2.getNom());
		assertThat(returnedListeCDto.get(1)).hasFieldOrPropertyWithValue("length", jardin2.getLongueur());
		assertThat(returnedListeCDto.get(1)).hasFieldOrPropertyWithValue("width", jardin2.getLargeur());
		assertThat(returnedListeCDto.get(1)).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin2.getDepartement()));
		assertThat(returnedListeCDto.get(1)).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin2.getUtilisateur()));
	}

	@Override
	@Test
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		JardinCreateDto dto = new JardinCreateDto();

		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");
		depDto.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		UtilisateurUpdateDto utilUpDto = new UtilisateurUpdateDto();
		utilUpDto.setIdentifier(1);

		dto.setGround(Sol.Argileux);
		dto.setName("nom4Test");
		dto.setLength((float) 10.0);
		dto.setWidth((float) 10.0);
		dto.setDept(depDto);
		dto.setUser(utilUpDto);

		JardinCreateDto dto2 = new JardinCreateDto();

		dto2.setGround(Sol.Argileux);
		dto2.setName("nom4Test2");
		dto2.setLength((float) 10.0);
		dto2.setWidth((float) 10.0);
		dto2.setDept(depDto);
		dto2.setUser(utilUpDto);

		List<JardinCreateDto> liste = new ArrayList<>();
		liste.add(dto);
		liste.add(dto2);

		List<Jardin> returnedListeJardin = jardinConverter.convertListCreateDtoToEntity(liste);

		assertThat(returnedListeJardin).hasSize(2).hasOnlyElementsOfType(Jardin.class);
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("sol", dto.getGround());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("longueur", dto.getLength());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("largeur", dto.getWidth());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto.getDept()));
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto.getUser()));
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("sol", dto2.getGround());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("nom", dto2.getName());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("longueur", dto2.getLength());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("largeur", dto2.getWidth());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto2.getDept()));
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto2.getUser()));
	}

	@Override
	@Test
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		Jardin jardin = new Jardin();

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomDep4Test");
		dep.setMeteoDep(new ArrayList<Meteo>());

		Utilisateur util = new Utilisateur();
		util.setId(1);

		jardin.setId(1);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		Jardin jardin2 = new Jardin();

		jardin.setId(2);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test2");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		List<Jardin> liste = new ArrayList<>();
		liste.add(jardin);
		liste.add(jardin2);

		List<JardinUpdateDto> returnedListeUpDto = jardinConverter.convertListEntityToUpdateDto(liste);

		assertThat(returnedListeUpDto).hasSize(2).hasOnlyElementsOfType(JardinUpdateDto.class);
		assertThat(returnedListeUpDto.get(0)).hasFieldOrPropertyWithValue("identifier", jardin.getId());
		assertThat(returnedListeUpDto.get(0)).hasFieldOrPropertyWithValue("ground", jardin.getSol());
		assertThat(returnedListeUpDto.get(0)).hasFieldOrPropertyWithValue("name", jardin.getNom());
		assertThat(returnedListeUpDto.get(0)).hasFieldOrPropertyWithValue("length", jardin.getLongueur());
		assertThat(returnedListeUpDto.get(0)).hasFieldOrPropertyWithValue("width", jardin.getLargeur());
		assertThat(returnedListeUpDto.get(0)).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin.getDepartement()));
		assertThat(returnedListeUpDto.get(0)).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin.getUtilisateur()));
		assertThat(returnedListeUpDto.get(1)).hasFieldOrPropertyWithValue("identifier", jardin2.getId());
		assertThat(returnedListeUpDto.get(1)).hasFieldOrPropertyWithValue("ground", jardin2.getSol());
		assertThat(returnedListeUpDto.get(1)).hasFieldOrPropertyWithValue("name", jardin2.getNom());
		assertThat(returnedListeUpDto.get(1)).hasFieldOrPropertyWithValue("length", jardin2.getLongueur());
		assertThat(returnedListeUpDto.get(1)).hasFieldOrPropertyWithValue("width", jardin2.getLargeur());
		assertThat(returnedListeUpDto.get(1)).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin2.getDepartement()));
		assertThat(returnedListeUpDto.get(1)).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin2.getUtilisateur()));
	}

	@Override
	@Test
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		JardinUpdateDto dto = new JardinUpdateDto();

		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");
		depDto.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		UtilisateurUpdateDto utilUpDto = new UtilisateurUpdateDto();
		utilUpDto.setIdentifier(1);

		dto.setIdentifier(1);
		dto.setGround(Sol.Argileux);
		dto.setName("nom4Test");
		dto.setLength((float) 10.0);
		dto.setWidth((float) 10.0);
		dto.setDept(depDto);
		dto.setUser(utilUpDto);

		JardinUpdateDto dto2 = new JardinUpdateDto();

		dto2.setIdentifier(2);
		dto2.setGround(Sol.Argileux);
		dto2.setName("nom4Test2");
		dto2.setLength((float) 10.0);
		dto2.setWidth((float) 10.0);
		dto2.setDept(depDto);
		dto2.setUser(utilUpDto);

		List<JardinUpdateDto> liste = new ArrayList<>();
		liste.add(dto);
		liste.add(dto2);

		List<Jardin> returnedListeJardin = jardinConverter.convertListUpdateDtoToEntity(liste);

		assertThat(returnedListeJardin).hasSize(2).hasOnlyElementsOfType(Jardin.class);
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("id", dto.getIdentifier());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("sol", dto.getGround());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("longueur", dto.getLength());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("largeur", dto.getWidth());
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto.getDept()));
		assertThat(returnedListeJardin.get(0)).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto.getUser()));
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("id", dto2.getIdentifier());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("sol", dto2.getGround());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("nom", dto2.getName());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("longueur", dto2.getLength());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("largeur", dto2.getWidth());
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto2.getDept()));
		assertThat(returnedListeJardin.get(1)).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto2.getUser()));
	}

	@Override
	@Test
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		JardinCreateDto dto = new JardinCreateDto();

		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");
		depDto.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		UtilisateurUpdateDto utilUpDto = new UtilisateurUpdateDto();
		utilUpDto.setIdentifier(1);

		dto.setGround(Sol.Argileux);
		dto.setName("nom4Test");
		dto.setLength((float) 10.0);
		dto.setWidth((float) 10.0);
		dto.setDept(depDto);
		dto.setUser(utilUpDto);

		JardinCreateDto dto2 = new JardinCreateDto();

		dto2.setGround(Sol.Argileux);
		dto2.setName("nom4Test2");
		dto2.setLength((float) 10.0);
		dto2.setWidth((float) 10.0);
		dto2.setDept(depDto);
		dto2.setUser(utilUpDto);

		List<JardinCreateDto> liste = new ArrayList<>();
		liste.add(dto);
		liste.add(dto2);
		Page<JardinCreateDto> page = new PageImpl<>(liste);

		Page<Jardin> returnedPageJardin = jardinConverter.convertPageCreateDtoToEntity(page);

		assertThat(returnedPageJardin).isNotNull();
		assertThat(returnedPageJardin.toList()).hasSize(2).hasOnlyElementsOfType(Jardin.class);
		assertThat(returnedPageJardin.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageJardin.getNumber()).isEqualTo(0);
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("sol", dto.getGround());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("longueur", dto.getLength());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("largeur", dto.getWidth());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto.getDept()));
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto.getUser()));
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("sol", dto2.getGround());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("nom", dto2.getName());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("longueur", dto2.getLength());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("largeur", dto2.getWidth());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto2.getDept()));
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto2.getUser()));
	}

	@Override
	@Test
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		Jardin jardin = new Jardin();

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomDep4Test");
		dep.setMeteoDep(new ArrayList<Meteo>());

		Utilisateur util = new Utilisateur();
		util.setId(1);

		jardin.setId(1);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		Jardin jardin2 = new Jardin();

		jardin.setId(2);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test2");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		List<Jardin> liste = new ArrayList<>();
		liste.add(jardin);
		liste.add(jardin2);

		Page<Jardin> page = new PageImpl<>(liste);

		Page<JardinCreateDto> returnedPageCDto = jardinConverter.convertPageEntityToCreateDto(page);

		assertThat(returnedPageCDto).isNotNull();
		assertThat(returnedPageCDto.toList()).hasSize(2).hasOnlyElementsOfType(JardinCreateDto.class);
		assertThat(returnedPageCDto.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageCDto.getNumber()).isEqualTo(0);
		assertThat(returnedPageCDto.toList().get(0)).hasFieldOrPropertyWithValue("ground", jardin.getSol());
		assertThat(returnedPageCDto.toList().get(0)).hasFieldOrPropertyWithValue("name", jardin.getNom());
		assertThat(returnedPageCDto.toList().get(0)).hasFieldOrPropertyWithValue("length", jardin.getLongueur());
		assertThat(returnedPageCDto.toList().get(0)).hasFieldOrPropertyWithValue("width", jardin.getLargeur());
		assertThat(returnedPageCDto.toList().get(0)).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin.getDepartement()));
		assertThat(returnedPageCDto.toList().get(0)).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin.getUtilisateur()));
		assertThat(returnedPageCDto.toList().get(1)).hasFieldOrPropertyWithValue("ground", jardin2.getSol());
		assertThat(returnedPageCDto.toList().get(1)).hasFieldOrPropertyWithValue("name", jardin2.getNom());
		assertThat(returnedPageCDto.toList().get(1)).hasFieldOrPropertyWithValue("length", jardin2.getLongueur());
		assertThat(returnedPageCDto.toList().get(1)).hasFieldOrPropertyWithValue("width", jardin2.getLargeur());
		assertThat(returnedPageCDto.toList().get(1)).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin2.getDepartement()));
		assertThat(returnedPageCDto.toList().get(1)).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin2.getUtilisateur()));
	}

	@Override
	@Test
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		JardinUpdateDto dto = new JardinUpdateDto();

		DepartementDto depDto = new DepartementDto();
		depDto.setDepNum(1);
		depDto.setName("nomDep4Test");
		depDto.setWeatherDep(new ArrayList<MeteoUpdateDto>());

		UtilisateurUpdateDto utilUpDto = new UtilisateurUpdateDto();
		utilUpDto.setIdentifier(1);

		dto.setIdentifier(1);
		dto.setGround(Sol.Argileux);
		dto.setName("nom4Test");
		dto.setLength((float) 10.0);
		dto.setWidth((float) 10.0);
		dto.setDept(depDto);
		dto.setUser(utilUpDto);

		JardinUpdateDto dto2 = new JardinUpdateDto();

		dto2.setIdentifier(2);
		dto2.setGround(Sol.Argileux);
		dto2.setName("nom4Test2");
		dto2.setLength((float) 10.0);
		dto2.setWidth((float) 10.0);
		dto2.setDept(depDto);
		dto2.setUser(utilUpDto);

		List<JardinUpdateDto> liste = new ArrayList<>();
		liste.add(dto);
		liste.add(dto2);
		Page<JardinUpdateDto> page = new PageImpl<>(liste);

		Page<Jardin> returnedPageJardin = jardinConverter.convertPageUpdateDtoToEntity(page);

		assertThat(returnedPageJardin).isNotNull();
		assertThat(returnedPageJardin.toList()).hasSize(2).hasOnlyElementsOfType(Jardin.class);
		assertThat(returnedPageJardin.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageJardin.getNumber()).isEqualTo(0);
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("id", dto.getIdentifier());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("sol", dto.getGround());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("longueur", dto.getLength());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("largeur", dto.getWidth());
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto.getDept()));
		assertThat(returnedPageJardin.toList().get(0)).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto.getUser()));
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("id", dto2.getIdentifier());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("sol", dto2.getGround());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("nom", dto2.getName());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("longueur", dto2.getLength());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("largeur", dto2.getWidth());
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("departement",
				departementConverter.convertDtoToEntity(dto2.getDept()));
		assertThat(returnedPageJardin.toList().get(1)).hasFieldOrPropertyWithValue("utilisateur",
				utilisateurConverteur.convertUpdateDtoToEntity(dto2.getUser()));
	}

	@Override
	@Test
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		Jardin jardin = new Jardin();

		Departement dep = new Departement();
		dep.setNumeroDep(1);
		dep.setNom("nomDep4Test");
		dep.setMeteoDep(new ArrayList<Meteo>());

		Utilisateur util = new Utilisateur();
		util.setId(1);

		jardin.setId(1);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		Jardin jardin2 = new Jardin();

		jardin.setId(2);
		jardin.setSol(Sol.Argileux);
		jardin.setNom("nom4Test2");
		jardin.setLongueur((float) 10);
		jardin.setLargeur((float) 10);
		jardin.setDepartement(dep);
		jardin.setUtilisateur(util);

		List<Jardin> liste = new ArrayList<>();
		liste.add(jardin);
		liste.add(jardin2);

		Page<Jardin> page = new PageImpl<>(liste);

		Page<JardinUpdateDto> returnedPageUpDto = jardinConverter.convertPageEntityToUpdateDto(page);

		assertThat(returnedPageUpDto).isNotNull();
		assertThat(returnedPageUpDto.toList()).hasSize(2).hasOnlyElementsOfType(JardinUpdateDto.class);
		assertThat(returnedPageUpDto.getNumberOfElements()).isEqualTo(2);
		assertThat(returnedPageUpDto.getNumber()).isEqualTo(0);
		assertThat(returnedPageUpDto.toList().get(0)).hasFieldOrPropertyWithValue("ground", jardin.getSol());
		assertThat(returnedPageUpDto.toList().get(0)).hasFieldOrPropertyWithValue("name", jardin.getNom());
		assertThat(returnedPageUpDto.toList().get(0)).hasFieldOrPropertyWithValue("length", jardin.getLongueur());
		assertThat(returnedPageUpDto.toList().get(0)).hasFieldOrPropertyWithValue("width", jardin.getLargeur());
		assertThat(returnedPageUpDto.toList().get(0)).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin.getDepartement()));
		assertThat(returnedPageUpDto.toList().get(0)).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin.getUtilisateur()));
		assertThat(returnedPageUpDto.toList().get(1)).hasFieldOrPropertyWithValue("ground", jardin2.getSol());
		assertThat(returnedPageUpDto.toList().get(1)).hasFieldOrPropertyWithValue("name", jardin2.getNom());
		assertThat(returnedPageUpDto.toList().get(1)).hasFieldOrPropertyWithValue("length", jardin2.getLongueur());
		assertThat(returnedPageUpDto.toList().get(1)).hasFieldOrPropertyWithValue("width", jardin2.getLargeur());
		assertThat(returnedPageUpDto.toList().get(1)).hasFieldOrPropertyWithValue("dept",
				departementConverter.convertEntityToDto(jardin2.getDepartement()));
		assertThat(returnedPageUpDto.toList().get(1)).hasFieldOrPropertyWithValue("user",
				utilisateurConverteur.convertEntityToUpdateDto(jardin2.getUtilisateur()));
	}

}
