package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.ResponseDto;
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
	IConverter<C, U, E> converter;

	@Autowired
	IService<E> service;

	@Override
	public ResponseEntity<ResponseDto<U>> create(C dto) {
		U returnedDto = converter
				.convertEntityToUpdateDto(service.create(converter.convertCreateDtoToEntity(dto)).getBody());
		ResponseDto<U> responseDto = new ResponseDto<U>();

		if (returnedDto != null) {
			responseDto.setError(false);
			responseDto.setMessage(WebMappingConstant.SUCCESS_CREATE);
			responseDto.setBody(returnedDto);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage(WebMappingConstant.FAIL_CREATE);
			responseDto.setBody(returnedDto);
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
		U returnedDto = converter
				.convertEntityToUpdateDto(service.update(converter.convertUpdateDtoToEntity(dto)).getBody());
		ResponseDto<U> responseDto = new ResponseDto<U>();

		if (returnedDto != null) {
			responseDto.setError(false);
			responseDto.setMessage(WebMappingConstant.SUCCESS_UPDATE);
			responseDto.setBody(returnedDto);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage(WebMappingConstant.FAIL_UPDATE);
			responseDto.setBody(returnedDto);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<U>> readById(int id) {
		U returnedDto = converter.convertEntityToUpdateDto(service.readById(id).getBody());
		ResponseDto<U> responseDto = new ResponseDto<U>();

		if (returnedDto != null) {
			responseDto.setError(false);
			responseDto.setMessage(WebMappingConstant.SUCCESS_READ_BY_ID);
			responseDto.setBody(returnedDto);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage(WebMappingConstant.FAIL_READ_BY_ID);
			responseDto.setBody(returnedDto);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<List<U>>> readAll() {
		List<U> returnedList = converter.convertListEntityToUpdateDto(service.readAll().getBody());
		ResponseDto<List<U>> responseDto = new ResponseDto<List<U>>();
		responseDto.setError(false);
		responseDto.setMessage(WebMappingConstant.SUCCESS_READ_ALL);
		responseDto.setBody(returnedList);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

}
