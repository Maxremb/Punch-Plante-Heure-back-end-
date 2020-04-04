package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.service.IService;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public abstract class AbstractController<C, U, E> implements IController<C, U> {

	@Autowired
	protected IConverter<C, U, E> converter;

	@Autowired
	protected IService<E> service;

	@Override
	public ResponseEntity<ResponseDto<U>> create(C dto) {

		log.info("Controller: méthode CREATE appelée");
		
		ServiceResponse<E> serviceResponse = service.create(converter.convertCreateDtoToEntity(dto));

		return makeUpdateDtoResponse(serviceResponse);
	}

	@Override
	public ResponseEntity<ResponseDto<U>> deleteById(int id) {
		
		log.info("Controller: méthode DELETE appelée");
		
		boolean result = service.deleteById(id);
		ResponseDto<U> responseDto = new ResponseDto<U>();

		if (result) {
			responseDto.setError(false);
			responseDto.setMessage(WebMappingConstant.SUCCESS_DELETE_BY_ID);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage(WebMappingConstant.FAIL_DELETE_BY_ID);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<U>> update(U dto) {
		
		log.info("Controller: méthode UPDATE appelée");

		ServiceResponse<E> serviceResponse = service.update(converter.convertUpdateDtoToEntity(dto));

		return makeUpdateDtoResponse(serviceResponse);
	}

	@Override
	public ResponseEntity<ResponseDto<U>> readById(int id) {
		
		log.info("Controller: méthode READBYID appelée");

		ServiceResponse<E> serviceResponse = service.readById(id);
		
		return makeUpdateDtoResponse(serviceResponse);


	}

	@Override
	public ResponseEntity<ResponseDto<Page<U>>> readAll(int p) {
		
		log.info("Controller: méthode READALL appelée");
		
		ServiceResponse<Page<E>> serviceResponse = service.readAll(p);
		
		return makeUpdateDtoPageResponse(serviceResponse);
		
	}
	
	/** Regroupe les elements communs des méthodes qui retournent des updateDto
	 * @param serviceResponse Reponse d'une méthode de la couche service de la méthode
	 * @return Response Entity contenant la ResponseDto. Body: UpdateDto
	 */
	protected ResponseEntity<ResponseDto<U>> makeUpdateDtoResponse (ServiceResponse<E> serviceResponse){
		
		log.debug("Controller: makeUpdateDtoResponse");
		
		U returnedDto = converter.convertEntityToUpdateDto(serviceResponse.getBody());
		
		ResponseDto<U> responseDto = new ResponseDto<U>();
		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(returnedDto);

		if (returnedDto != null) {
			
			log.debug("Controller/makeUpdateDtoResponse: retour de la couche service NON-NULL");
			
			responseDto.setError(false);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			
			log.debug("Controller/makeUpdateDtoResponse: Statut 400. Cause: " + serviceResponse.getMessage());
			
			responseDto.setError(true);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
	}
	
	/**Regroupe les elements communs des méthodes qui retournent des listes d'updateDto
	 * @param serviceResponse Reponse de la couche service de la méthode
	 * @return Response Entity contenant la ResponseDto. Body: une liste d'updateDto
	 */
	protected ResponseEntity<ResponseDto<List<U>>> makeUpdateDtoListResponse(
			ServiceResponse<List<E>> serviceResponse) {
		
		log.debug("Controller: makeUpdateDtoListResponse");

		ResponseDto<List<U>> responseDto = new ResponseDto<List<U>>();
		List<U> periodeList = converter.convertListEntityToUpdateDto(serviceResponse.getBody());

		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(periodeList);
		responseDto.setError(false);
		return ResponseEntity.ok(responseDto);

	}
	
	/**Regroupe les elements communs des méthodes qui retournent des listes d'updateDto
	 * @param serviceResponse Reponse de la couche service de la méthode
	 * @return Response Entity contenant la ResponseDto. Body: Une page d'updateDto
	 */
	protected ResponseEntity<ResponseDto<Page<U>>> makeUpdateDtoPageResponse(
			ServiceResponse<Page<E>> serviceResponse) {

		log.debug("Controller: makeUpdateDtoPageResponse");
		
		ResponseDto<Page<U>> responseDto = new ResponseDto<Page<U>>();
		Page<U> periodeList = converter.convertPageEntityToUpdateDto(serviceResponse.getBody());

		responseDto.setMessage(serviceResponse.getMessage());
		responseDto.setBody(periodeList);
		responseDto.setError(false);
		
		return ResponseEntity.ok(responseDto);

	}

}
