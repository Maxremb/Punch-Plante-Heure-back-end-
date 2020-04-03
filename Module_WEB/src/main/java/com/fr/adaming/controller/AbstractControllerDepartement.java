package com.fr.adaming.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.converter.IConverterDepartement;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.service.IDepartementService;
import com.fr.adaming.service.IService;

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
	public ResponseEntity<ResponseDto<?>> deleteById(int id) {
		boolean result = service.deleteById(id);
		ResponseDto<?> responseDto = new ResponseDto<Object>();

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
	public ResponseEntity<ResponseDto<List<D>>> readAll() {
		
		ServiceResponse<List<E>> serviceResponse = service.readAll();
		
		List<D> returnedList = converter.convertListEntityToDto(serviceResponse.getBody());
		ResponseDto<List<D>> responseDto = new ResponseDto<List<D>>();
		responseDto.setError(false);
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedList);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	@Override
	public ResponseEntity<ResponseDto<D>> readByName(String name) {
		ServiceResponse<E> serviceResponse = serviceDep.readDepartementByNom(name);
		
		D returned = converter.convertEntityToDto(serviceResponse.getBody());
		ResponseDto<D> responseDto = new ResponseDto<D>();
		responseDto.setError(false);
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returned);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@Override
	public ResponseEntity<ResponseDto<List<MU>>> readMeteoByNumDep(@Positive int id) {
		ServiceResponse<List<ME>> serviceResponse = serviceDep.readMeteoByNumeroDep(id);
		
		List<MU> returnedList = converterMeteo.convertListEntityToUpdateDto(serviceResponse.getBody());
		ResponseDto<List<MU>> responseDto = new ResponseDto<List<MU>>();
		responseDto.setError(false);
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedList);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

}
