package com.fr.adaming.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.converter.IConverterDepartement;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.service.IDepartementService;
import com.fr.adaming.service.IService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Isaline<br>
 * 
 *         <b>Description : </b>
 *         <p>
 *         Classe abstraite qui implemente IControllerDepartement Redefinition des méthodes
 *         CRUD
 *         </p>
 *
 * @param <D> DepartementDto
 * @param <M> MeteoUpdateDto
 * @param <E> Entite Departement
 */
@Slf4j
public abstract class AbstractControllerDepartement<D, MU, ME, MC, E> implements IControllerDepartement<D, MU> {

	@Autowired
	private IConverterDepartement<E, D> converter;
	
	@Autowired
	private IConverter<MC, MU, ME> converterMeteo;

	@Autowired
	private IService<E> service;
	
	@Autowired
	private IDepartementService<E, ME> serviceDep;

	@Override
	public ResponseEntity<ResponseDto<D>> create(D dto) {
		
		log.info("Controller: méthode CREATE appelée");

		ServiceResponse<E> serviceResponse = service.create(converter.convertDtoToEntity(dto));

		D returnedDto = converter.convertEntityToDto(serviceResponse.getBody());

		ResponseDto<D> responseDto = new ResponseDto<D>();

		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedDto);

		if (returnedDto != null) {
			responseDto.setError(false);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<D>> deleteById(int id) {
		log.info("Controller: méthode DELETE appelée");
		
		boolean result = service.deleteById(id);
		ResponseDto<D> responseDto = new ResponseDto<>();

		if (result) {
			responseDto.setError(false);
			responseDto.setMessage(WebMappingConstant.SUCCESS_DELETE_BY_ID);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage(WebMappingConstant.SUCCESS_DELETE_BY_ID);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<D>> update(D dto) {
		
		log.info("Controller: méthode UPDATE appelée");

		ServiceResponse<E> serviceResponse = service.update(converter.convertDtoToEntity(dto));

		D returnedDto = converter.convertEntityToDto(serviceResponse.getBody());

		ResponseDto<D> responseDto = new ResponseDto<D>();
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedDto);

		if (returnedDto != null) {
			responseDto.setError(false);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<D>> readById(int id) {
		
		log.info("Controller: méthode READBYID appelée");

		ServiceResponse<E> serviceResponse = service.readById(id);

		D returnedDto = converter.convertEntityToDto(serviceResponse.getBody());
		
		ResponseDto<D> responseDto = new ResponseDto<D>();
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedDto);

		if (returnedDto != null) {
			responseDto.setError(false);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<Page<D>>> readAll(int p) {
		
		log.info("Controller: méthode READALL appelée");
		
		ServiceResponse<Page<E>> serviceResponse = service.readAll(p);
		
		Page<D> returnedPage = converter.convertPageEntityToDto(serviceResponse.getBody());
		ResponseDto<Page<D>> responseDto = new ResponseDto<>();
		responseDto.setError(false);
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedPage);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	@Override
	public ResponseEntity<ResponseDto<D>> readByName(String name) {
		
		log.info("Controller: méthode READBYNAME appelée");
		
		ServiceResponse<E> serviceResponse = serviceDep.readDepartementByNom(name);
		
		ResponseDto<D> responseDto = new ResponseDto<>();

		if (serviceResponse.getBody() == null) {
			responseDto.setError(true);
			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		} else {		
		D returned = converter.convertEntityToDto(serviceResponse.getBody());
		responseDto.setError(false);
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returned);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<List<MU>>> readMeteoByNumDep(@Positive int id) {
		
		log.info("Controller: méthode READMETEOBYNUMDEP appelée");
		
		ServiceResponse<List<ME>> serviceResponse = serviceDep.readMeteoByNumeroDep(id);
		
		ResponseDto<List<MU>> responseDto = new ResponseDto<List<MU>>();
		
		if (serviceResponse.getBody() == null) {
			responseDto.setError(true);
			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		} else {
			List<MU> returnedList = converterMeteo.convertListEntityToUpdateDto(serviceResponse.getBody());
			responseDto.setError(false);
			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(returnedList);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		}
	}

}
