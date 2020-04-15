package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Utilisateur;

/**
 * <p>
 * Classe de Test pour les Converter de l'entite Utilisateur.<br>
 * Implemente l'interface IConverter.
 * </p>
 * 
 * @author Clara Cadet / Isaline MILLET
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class UtilisateurConverterTest implements IConverterTests {

	@Autowired
	private UtilisateurConverter userConverter;

	@Override
	@Test
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		UtilisateurCreateDto dto = new UtilisateurCreateDto();
		dto.setFirstName("Tony");
		dto.setLastName("Stark");

		Utilisateur user = userConverter.convertCreateDtoToEntity(dto);

		assertThat(user.getNom()).isEqualTo("Stark");
		assertThat(user.getPrenom()).isEqualTo("Tony");

	}

	@Override
	@Test
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		UtilisateurCreateDto dto = null;

		Utilisateur user = userConverter.convertCreateDtoToEntity(dto);

		assertNull(user);

	}

	@Override
	@Test
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		UtilisateurUpdateDto dto = new UtilisateurUpdateDto();
		dto.setIdentifier(18);
		dto.setFirstName("Tony");
		dto.setLastName("Stark");

		Utilisateur user = userConverter.convertUpdateDtoToEntity(dto);

		assertThat(user.getNom()).isEqualTo("Stark");
		assertThat(user.getPrenom()).isEqualTo("Tony");
		assertTrue(user.getId() == 18);

	}

	@Override
	@Test
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		UtilisateurUpdateDto dto = null;

		Utilisateur user = userConverter.convertUpdateDtoToEntity(dto);

		assertNull(user);

	}

	@Override
	@Test
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		Utilisateur user = new Utilisateur();
		user.setPrenom("Tony");
		user.setNom("Stark");
		user.setId(18);

		UtilisateurCreateDto dto = userConverter.convertEntityToCreateDto(user);

		assertThat(dto.getLastName()).isEqualTo("Stark");
		assertThat(dto.getFirstName()).isEqualTo("Tony");

	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		UtilisateurCreateDto dto = userConverter.convertEntityToCreateDto(null);

		assertNull(dto);

	}

	@Override
	@Test
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		Utilisateur user = new Utilisateur();
		user.setId(18);
		user.setPrenom("Tony");
		user.setNom("Stark");

		UtilisateurUpdateDto dto = userConverter.convertEntityToUpdateDto(user);

		assertThat(dto.getLastName()).isEqualTo("Stark");
		assertThat(dto.getFirstName()).isEqualTo("Tony");

	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		UtilisateurUpdateDto dto = userConverter.convertEntityToUpdateDto(null);

		assertNull(dto);

	}

	@Override
	@Test
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		Utilisateur user = new Utilisateur();
		user.setId(18);
		user.setPrenom("Tony");
		user.setNom("Stark");
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		liste.add(user);
		user.setId(89);
		liste.add(user);

		List<UtilisateurCreateDto> dtos = userConverter.convertListEntityToCreateDto(liste);

		assertTrue(dtos.size() == 2);
		assertThat(dtos.get(0).getFirstName()).isEqualTo("Tony");

	}

	@Override
	@Test
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		List<UtilisateurCreateDto> dtos = userConverter.convertListEntityToCreateDto(null);

		assertTrue(dtos.size() == 0);

	}

	@Override
	@Test
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		UtilisateurCreateDto dto = new UtilisateurCreateDto();
		dto.setFirstName("Piper");
		dto.setLastName("Stark");
		List<UtilisateurCreateDto> dtos = new ArrayList<UtilisateurCreateDto>();
		dtos.add(dto);
		dtos.add(dto);

		List<Utilisateur> liste = userConverter.convertListCreateDtoToEntity(dtos);

		assertTrue(liste.size() == 2);
		assertThat(liste.get(0).getPrenom()).isEqualTo("Piper");

	}

	@Override
	@Test
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {

		List<Utilisateur> liste = userConverter.convertListCreateDtoToEntity(null);

		assertTrue(liste.size() == 0);

	}

	@Override
	@Test
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		UtilisateurUpdateDto dto = new UtilisateurUpdateDto();
		dto.setIdentifier(19);
		dto.setFirstName("Piper");
		dto.setLastName("Stark");
		List<UtilisateurUpdateDto> dtos = new ArrayList<UtilisateurUpdateDto>();
		dtos.add(dto);
		dtos.add(dto);

		List<Utilisateur> liste = userConverter.convertListUpdateDtoToEntity(dtos);

		assertTrue(liste.size() == 2);
		assertThat(liste.get(1).getPrenom()).isEqualTo("Piper");

	}

	@Override
	@Test
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {

		List<Utilisateur> liste = userConverter.convertListUpdateDtoToEntity(null);

		assertTrue(liste.size() == 0);

	}

	@Override
	@Test
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		UtilisateurUpdateDto dto = new UtilisateurUpdateDto();
		dto.setFirstName("Tony");
		dto.setLastName("Stark");
		List<UtilisateurUpdateDto> dtos = new ArrayList<UtilisateurUpdateDto>();
		dtos.add(dto);
		dto.setFirstName("Piper");
		dtos.add(dto);

		List<Utilisateur> liste = userConverter.convertListUpdateDtoToEntity(dtos);

		assertTrue(liste.size() == 2);
		assertThat(liste.get(1).getPrenom()).isEqualTo("Piper");

	}

	@Override
	@Test
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {

		List<Utilisateur> liste = userConverter.convertListUpdateDtoToEntity(null);

		assertTrue(liste.size() == 0);

	}

	@Override
	@Test
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		UtilisateurCreateDto dto = new UtilisateurCreateDto();
		dto.setFirstName("Piper");
		dto.setLastName("Stark");
		List<UtilisateurCreateDto> dtos = new ArrayList<UtilisateurCreateDto>();
		dtos.add(dto);
		dtos.add(dto);

		Page<UtilisateurCreateDto> pageable = new PageImpl<UtilisateurCreateDto>(dtos);

		Page<Utilisateur> page = userConverter.convertPageCreateDtoToEntity(pageable);

		assertNotNull(page);
		assertThat(page.toList()).hasOnlyElementsOfType(Utilisateur.class);
		assertThat(page.getNumberOfElements()).isEqualTo(2);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage() {
		Page<Utilisateur> page = userConverter.convertPageCreateDtoToEntity(null);

		assertNotNull(page);
		assertThat(page.getNumberOfElements()).isEqualTo(0);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		Utilisateur user = new Utilisateur();
		user.setId(18);
		user.setPrenom("Tony");
		user.setNom("Stark");
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		liste.add(user);
		user.setId(89);
		liste.add(user);
		
		Page<Utilisateur> pageable = new PageImpl<Utilisateur>(liste);
		
		Page<UtilisateurCreateDto> page = userConverter.convertPageEntityToCreateDto(pageable);

		assertNotNull(page);
		assertThat(page.toList()).hasOnlyElementsOfType(UtilisateurCreateDto.class);
		assertThat(page.getNumberOfElements()).isEqualTo(2);
		assertThat(page.getNumber()).isEqualTo(0);

	}

	@Override
	@Test
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage() {
		Page<UtilisateurCreateDto> page = userConverter.convertPageEntityToCreateDto(null);

		assertNotNull(page);
		assertThat(page.getNumberOfElements()).isEqualTo(0);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		UtilisateurUpdateDto dto = new UtilisateurUpdateDto();
		dto.setFirstName("Tony");
		dto.setLastName("Stark");
		List<UtilisateurUpdateDto> dtos = new ArrayList<UtilisateurUpdateDto>();
		dtos.add(dto);
		dto.setFirstName("Piper");
		dtos.add(dto);
		
		Page<UtilisateurUpdateDto> pageable = new PageImpl<UtilisateurUpdateDto>(dtos);

		Page<Utilisateur> page = userConverter.convertPageUpdateDtoToEntity(pageable);

		assertNotNull(page);
		assertThat(page.toList()).hasOnlyElementsOfType(Utilisateur.class);
		assertThat(page.getNumberOfElements()).isEqualTo(2);
		assertThat(page.getNumber()).isEqualTo(0);

	}

	@Override
	@Test
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage() {
		Page<UtilisateurUpdateDto> page = userConverter.convertPageEntityToUpdateDto(null);

		assertNotNull(page);
		assertThat(page.getNumberOfElements()).isEqualTo(0);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		Utilisateur user = new Utilisateur();
		user.setId(18);
		user.setPrenom("Tony");
		user.setNom("Stark");
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		liste.add(user);
		user.setId(89);
		liste.add(user);
		
		Page<Utilisateur> pageable = new PageImpl<Utilisateur>(liste);
		
		Page<UtilisateurUpdateDto> page = userConverter.convertPageEntityToUpdateDto(pageable);

		assertNotNull(page);
		assertThat(page.toList()).hasOnlyElementsOfType(UtilisateurUpdateDto.class);
		assertThat(page.getNumberOfElements()).isEqualTo(2);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage() {
		Page<Utilisateur> page = userConverter.convertPageUpdateDtoToEntity(null);

		assertNotNull(page);
		assertThat(page.getNumberOfElements()).isEqualTo(0);
		assertThat(page.getNumber()).isEqualTo(0);
	}

}
