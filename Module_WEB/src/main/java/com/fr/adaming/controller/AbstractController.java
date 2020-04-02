package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.service.IService;

//TODO adapter au front

/**
 * @author Gregoire<br>
 * 
 *         <b>Description : </b>
 *         <p>
 *         Classe abstraite qui implemente IController Redefinition des méthodes
 *         CRUD
 *         </p>
 *
 * @param <C> CreateDto
 * @param <U> UpdateDto
 * @param <E> Entite
 */
public abstract class AbstractController<C, U, E> implements IController<C, U> {

	@Autowired
	protected IConverter<C, U, E> converter;

	@Autowired
	protected IService<E> service;

	@Override
	public ResponseEntity<ResponseDto<U>> create(C dto) {

		ServiceResponse<E> serviceResponse = service.create(converter.convertCreateDtoToEntity(dto));

		U returnedDto = converter.convertEntityToUpdateDto(serviceResponse.getBody());

		ResponseDto<U> responseDto = new ResponseDto<U>();

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
	public ResponseEntity<ResponseDto<U>> update(U dto) {

		ServiceResponse<E> serviceResponse = service.update(converter.convertUpdateDtoToEntity(dto));

		U returnedDto = converter.convertEntityToUpdateDto(serviceResponse.getBody());

		ResponseDto<U> responseDto = new ResponseDto<U>();
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
	public ResponseEntity<ResponseDto<U>> readById(int id) {

		ServiceResponse<E> serviceResponse = service.readById(id);

		U returnedDto = converter.convertEntityToUpdateDto(serviceResponse.getBody());
		
		ResponseDto<U> responseDto = new ResponseDto<U>();
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
	public ResponseEntity<ResponseDto<List<U>>> readAll() {
		
		ServiceResponse<List<E>> serviceResponse = service.readAll();
		
		List<U> returnedList = converter.convertListEntityToUpdateDto(serviceResponse.getBody());
		ResponseDto<List<U>> responseDto = new ResponseDto<List<U>>();
		responseDto.setError(false);
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedList);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	


}
