package com.fr.adaming.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.ModuleWebApplication;

/**
 * Classe de test du converter Retention Implémente l'interface IConverterTests
 * 
 * @author maxime
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest(classes = ModuleWebApplication.class)
public class RetentionConverterTest implements IConverterTests {

	@Autowired
	private RetentionConverter convert;

	@Override
	@Test
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		

	}

	@Override
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub

	}

}
