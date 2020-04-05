package com.fr.adaming.controller.impl;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.ModuleWebApplication;
import com.fr.adaming.controller.AbstractTestMethods;
import com.fr.adaming.controller.IControllerTests;
import com.fr.adaming.dto.JardinUpdateDto;

@SpringBootTest(classes = ModuleWebApplication.class)
@AutoConfigureMockMvc
public class JardinControllerTests extends AbstractTestMethods<JardinUpdateDto> implements IControllerTests {

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

}
