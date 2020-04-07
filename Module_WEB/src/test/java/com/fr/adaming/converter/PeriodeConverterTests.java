package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.entity.Periode;

@SpringBootTest(classes = ModuleWebApplication.class)
public class PeriodeConverterTests implements IConverterTests{
	
	@Autowired
	private PeriodeConverter converter;

	@Test
	@Override
	public void testConvertingCreateDtoToEntity_shouldReturnEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingUpdateDtoToEntity_shouldReturnEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingEntityToCreateDto_shouldReturnCreateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingEntityToUpdateDto_shouldReturnUpdateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListEntityToCreateDto_shouldReturnCreateDtoList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListCreateDtoToEntity_shouldReturnEntityList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListEntityToUpdateDto_shouldReturnUpdateDtoList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListUpdateDtoToEntity_shouldReturnEntityList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingPageCreateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullPageCreateDtoToEntity_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingPageEntityToCreateDto_shouldReturnPageOfCreateDtos() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullPageEntityToCreateDto_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingPageUpdateDtoToEntity_shouldReturnPageOfEntities() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullPageUpdateDtoToEntity_shouldReturnEmptyPage() {
		
		Page<Periode> periode = converter.convertPageUpdateDtoToEntity(null);
		
		assertNotNull(periode);
		assertThat(periode.getContent()).asList().isEmpty();
		
	}

	@Test
	@Override
	public void testConvertingPageEntityToUpdateDto_shouldReturnPageOfUpdateDtos() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullPageEntityToUpdateDto_shouldReturnEmptyPage() {
		// TODO Auto-generated method stub
		
	}

}