package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.PeriodeUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Periode;
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

		return makeUpdateDtoResponse(serviceResponse);
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

		return makeUpdateDtoResponse(serviceResponse);
	}

	@Override
	public ResponseEntity<ResponseDto<U>> readById(int id) {

		ServiceResponse<E> serviceResponse = service.readById(id);
		
		return makeUpdateDtoResponse(serviceResponse);


	}

	@Override
	public ResponseEntity<ResponseDto<Page<U>>> readAll(int p) {
		
		ServiceResponse<Page<E>> serviceResponse = service.readAll(p);
		
		return makeUpdateDtoListResponse(serviceResponse);
		
	}
	
	/** Regroupe les elements communs des méthodes qui retournent des updateDto
	 * @param serviceResponse Reponse d'une méthode de la couche service de la méthode
	 * @return Response Entity contenant la responseDto et le UpdateDto
	 */
	protected ResponseEntity<ResponseDto<U>> makeUpdateDtoResponse (ServiceResponse<E> serviceResponse){
		
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
	
	/**Regroupe les elements communs des méthodes qui retournent des listes d'updateDto
	 * @param serviceResponse Reponse de la couche service de la méthode
	 * @return Response Entity contenant la responseDto et une liste d'updateDto
	 */
	protected ResponseEntity<ResponseDto<Page<U>>> makeUpdateDtoListResponse(
			ServiceResponse<Page<E>> serviceResponse) {

		ResponseDto<Page<U>> responseDto = new ResponseDto<Page<U>>();
		Page<U> periodeList = converter.convertListEntityToUpdateDto(serviceResponse.getBody());

		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(periodeList);
		responseDto.setError(false);
		return ResponseEntity.ok(responseDto);

	}

}
