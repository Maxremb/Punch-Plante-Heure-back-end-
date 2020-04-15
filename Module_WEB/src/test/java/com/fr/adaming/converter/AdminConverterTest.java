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
import com.fr.adaming.dto.AdminCreateDto;
import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.entity.Admin;

/**
 * <p>
 * Classe de Test pour les Converter de l'entite Admin.<br>
 * Implemente l'interface IConverter.
 * </p>
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 *
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class AdminConverterTest implements IConverterTests {

	@Autowired
	private AdminConverter adminConverter;

	@Override
	@Test
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		AdminCreateDto dto = new AdminCreateDto();
		dto.setMail("mail");
		dto.setPseudo("pseudo");
		dto.setPwd("pwd");

		Admin admin = adminConverter.convertCreateDtoToEntity(dto);

		assertThat(admin.getEmail()).isEqualTo(dto.getMail());
		assertThat(admin.getPseudonyme()).isEqualTo(dto.getPseudo());
		assertThat(admin.getMdp()).isEqualTo(dto.getPwd());
	}

	@Override
	@Test
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		AdminCreateDto dto = null;

		Admin admin = adminConverter.convertCreateDtoToEntity(dto);

		assertNull(admin);
	}

	@Override
	@Test
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		AdminUpdateDto dto = new AdminUpdateDto();
		dto.setIdentifier(1);
		dto.setMail("mail");
		dto.setPseudo("pseudo");
		dto.setPwd("pwd");

		Admin admin = adminConverter.convertUpdateDtoToEntity(dto);

		assertThat(admin.getId()).isEqualTo(dto.getIdentifier());
		assertThat(admin.getEmail()).isEqualTo(dto.getMail());
		assertThat(admin.getPseudonyme()).isEqualTo(dto.getPseudo());
		assertThat(admin.getMdp()).isEqualTo(dto.getPwd());
	}

	@Override
	@Test
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		AdminUpdateDto dto = null;

		Admin admin = adminConverter.convertUpdateDtoToEntity(dto);

		assertNull(admin);
	}

	@Override
	@Test
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		Admin admin = new Admin();
		admin.setEmail("mail");
		admin.setPseudonyme("pseudo");
		admin.setMdp("mdp");

		AdminCreateDto dto = adminConverter.convertEntityToCreateDto(admin);

		assertThat(dto.getMail()).isEqualTo(admin.getEmail());
		assertThat(dto.getPseudo()).isEqualTo(admin.getPseudonyme());
		assertThat(dto.getPwd()).isEqualTo(admin.getMdp());
	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		Admin admin = null;

		AdminCreateDto dto = adminConverter.convertEntityToCreateDto(admin);

		assertNull(dto);
	}

	@Override
	@Test
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setEmail("mail");
		admin.setPseudonyme("pseudo");
		admin.setMdp("mdp");

		AdminUpdateDto dto = adminConverter.convertEntityToUpdateDto(admin);

		assertThat(dto.getIdentifier()).isEqualTo(admin.getId());
		assertThat(dto.getMail()).isEqualTo(admin.getEmail());
		assertThat(dto.getPseudo()).isEqualTo(admin.getPseudonyme());
		assertThat(dto.getPwd()).isEqualTo(admin.getMdp());
	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		Admin admin = null;

		AdminUpdateDto dto = adminConverter.convertEntityToUpdateDto(admin);

		assertNull(dto);
	}

	@Override
	@Test
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setEmail("mail");
		admin.setPseudonyme("pseudo");
		admin.setMdp("mdp");

		List<Admin> liste = new ArrayList<Admin>();

		liste.add(admin);

		admin.setId(2);

		liste.add(admin);

		List<AdminCreateDto> dtos = adminConverter.convertListEntityToCreateDto(liste);

		assertTrue(dtos.size() == 2);
		assertThat(dtos.get(0).getMail()).isEqualTo(admin.getEmail());
	}

	@Override
	@Test
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		List<Admin> liste = new ArrayList<Admin>();

		List<AdminCreateDto> dtos = adminConverter.convertListEntityToCreateDto(liste);

		assertTrue(dtos.isEmpty());
	}

	@Override
	@Test
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		AdminCreateDto dto = new AdminCreateDto();
		dto.setMail("mail");
		dto.setPseudo("pseudo");
		dto.setPwd("pwd");

		List<AdminCreateDto> liste = new ArrayList<AdminCreateDto>();

		liste.add(dto);

		List<Admin> admin = adminConverter.convertListCreateDtoToEntity(liste);

		assertTrue(admin.size() == 1);
		assertThat(admin.get(0).getEmail()).isEqualTo(dto.getMail());
	}

	@Override
	@Test
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		List<AdminCreateDto> liste = new ArrayList<AdminCreateDto>();

		List<Admin> admin = adminConverter.convertListCreateDtoToEntity(liste);

		assertTrue(admin.isEmpty());
	}

	@Override
	@Test
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setEmail("mail");
		admin.setPseudonyme("pseudo");
		admin.setMdp("mdp");

		List<Admin> liste = new ArrayList<Admin>();

		liste.add(admin);

		admin.setId(2);

		liste.add(admin);

		List<AdminUpdateDto> dtos = adminConverter.convertListEntityToUpdateDto(liste);

		assertTrue(dtos.size() == 2);
		assertThat(dtos.get(0).getMail()).isEqualTo(admin.getEmail());
	}

	@Override
	@Test
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		List<Admin> liste = new ArrayList<Admin>();

		List<AdminUpdateDto> dtos = adminConverter.convertListEntityToUpdateDto(liste);

		assertTrue(dtos.isEmpty());
	}

	@Override
	@Test
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		AdminUpdateDto dto = new AdminUpdateDto();
		dto.setIdentifier(1);
		dto.setMail("mail");
		dto.setPseudo("pseudo");
		dto.setPwd("pwd");

		List<AdminUpdateDto> liste = new ArrayList<AdminUpdateDto>();

		liste.add(dto);

		dto.setIdentifier(2);

		liste.add(dto);

		List<Admin> admin = adminConverter.convertListUpdateDtoToEntity(liste);

		assertTrue(admin.size() == 2);
		assertThat(admin.get(0).getEmail()).isEqualTo(dto.getMail());
	}

	@Override
	@Test
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		List<AdminUpdateDto> liste = new ArrayList<AdminUpdateDto>();

		List<Admin> admin = adminConverter.convertListUpdateDtoToEntity(liste);

		assertTrue(admin.isEmpty());
	}

	@Override
	@Test
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		AdminCreateDto dto = new AdminCreateDto();
		dto.setMail("mail");
		dto.setPseudo("pseudo");
		dto.setPwd("pwd");

		List<AdminCreateDto> liste = new ArrayList<AdminCreateDto>();

		liste.add(dto);

		Page<AdminCreateDto> pageable = new PageImpl<AdminCreateDto>(liste);

		Page<Admin> page = adminConverter.convertPageCreateDtoToEntity(pageable);

		assertNotNull(page);
		assertThat(page.toList()).hasOnlyElementsOfType(Admin.class);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage() {
		Page<Admin> page = adminConverter.convertPageCreateDtoToEntity(null);

		assertNotNull(page);
		assertThat(page.getNumberOfElements()).isEqualTo(0);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setEmail("mail");
		admin.setPseudonyme("pseudo");
		admin.setMdp("mdp");

		List<Admin> liste = new ArrayList<Admin>();

		liste.add(admin);

		admin.setId(2);

		liste.add(admin);

		Page<Admin> pageable = new PageImpl<Admin>(liste);

		Page<AdminCreateDto> page = adminConverter.convertPageEntityToCreateDto(pageable);

		assertNotNull(page);
		assertThat(page.toList()).hasOnlyElementsOfType(AdminCreateDto.class);
		assertThat(page.getNumberOfElements()).isEqualTo(2);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage() {
		Page<AdminCreateDto> page = adminConverter.convertPageEntityToCreateDto(null);

		assertNotNull(page);
		assertThat(page.getNumberOfElements()).isEqualTo(0);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		AdminUpdateDto dto = new AdminUpdateDto();
		dto.setMail("mail");
		dto.setPseudo("pseudo");
		dto.setPwd("pwd");

		List<AdminUpdateDto> liste = new ArrayList<AdminUpdateDto>();

		liste.add(dto);

		Page<AdminUpdateDto> pageable = new PageImpl<AdminUpdateDto>(liste);

		Page<Admin> page = adminConverter.convertPageUpdateDtoToEntity(pageable);

		assertNotNull(page);
		assertThat(page.toList()).hasOnlyElementsOfType(Admin.class);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage() {
		Page<Admin> page = adminConverter.convertPageUpdateDtoToEntity(null);

		assertNotNull(page);
		assertThat(page.getNumberOfElements()).isEqualTo(0);
		assertThat(page.getNumber()).isEqualTo(0);

	}

	@Override
	@Test
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		Admin admin = new Admin();
		admin.setId(1);
		admin.setEmail("mail");
		admin.setPseudonyme("pseudo");
		admin.setMdp("mdp");

		List<Admin> liste = new ArrayList<Admin>();

		liste.add(admin);

		admin.setId(2);

		liste.add(admin);

		Page<Admin> pageable = new PageImpl<Admin>(liste);

		Page<AdminUpdateDto> page = adminConverter.convertPageEntityToUpdateDto(pageable);

		assertNotNull(page);
		assertThat(page.toList()).hasOnlyElementsOfType(AdminUpdateDto.class);
		assertThat(page.getNumberOfElements()).isEqualTo(2);
		assertThat(page.getNumber()).isEqualTo(0);
	}

	@Override
	@Test
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage() {
		Page<AdminUpdateDto> page = adminConverter.convertPageEntityToUpdateDto(null);

		assertNotNull(page);
		assertThat(page.getNumberOfElements()).isEqualTo(0);
		assertThat(page.getNumber()).isEqualTo(0);
	}

}
