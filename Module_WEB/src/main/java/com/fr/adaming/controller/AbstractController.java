package com.fr.adaming.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.enums.Role;
import com.fr.adaming.security.interfaces.ISessionService2;
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

	protected Map<String, Role> niveauDAcces = new HashMap<String, Role>();

	@Autowired
	protected IConverter<C, U, E> converter;

	@Autowired
	protected IService<E> service;

	@Autowired
	protected ISessionService2 sService;

	@Override
	public ResponseEntity<ResponseDto<U>> create(C dto) {

		log.info("Controller: méthode CREATE appelée");

		Role userRole = sService.getUserRole();

		ServiceResponse<E> serviceResponse;

		try {

			if (accessControl("create", userRole)) {

				serviceResponse = service.create(converter.convertCreateDtoToEntity(dto));

			} else {
				log.warn("Create: Acces refusé");
				serviceResponse = new ServiceResponse<E>("Create: Acces refusé", null);
			}

			return makeUpdateDtoResponse(serviceResponse);
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Controller create" + e.getMessage());
			ResponseDto<U> responseDto = new ResponseDto<U>();
			responseDto.setError(true);
			responseDto.setMessage("Erreur méthode Abstract Controller create" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}

	}

	@Override
	public ResponseEntity<ResponseDto<U>> deleteById(int id) {

		log.info("Controller: méthode DELETE appelée");

		try {
			boolean result = service.deleteById(id);
			ResponseDto<U> responseDto = new ResponseDto<U>();

			if (result) {
				responseDto.setError(false);
				responseDto.setMessage("Suppression réussie");
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				responseDto.setError(true);
				responseDto.setMessage("Erreur pendant la suppression de l'entité: " + id);
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Controller deleteById" + e.getMessage());
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseDto<U>> update(U dto) {

		log.info("Controller: méthode UPDATE appelée");

		try {
			ServiceResponse<E> serviceResponse = service.update(converter.convertUpdateDtoToEntity(dto));
			return makeUpdateDtoResponse(serviceResponse);
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Controller update" + e.getMessage());
			ResponseDto<U> responseDto = new ResponseDto<U>();
			responseDto.setError(true);
			responseDto.setMessage("Erreur méthode Abstract Controller update" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}
	}

	@Override
	public ResponseEntity<ResponseDto<U>> readById(int id) {

		log.info("Controller: méthode READBYID appelée");

		try {
			ServiceResponse<E> serviceResponse = service.readById(id);
			return makeUpdateDtoResponse(serviceResponse);
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Controller readById" + e.getMessage());
			ResponseDto<U> responseDto = new ResponseDto<U>();
			responseDto.setError(true);
			responseDto.setMessage("Erreur méthode Abstract Controller readById" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}

	}

	@Override
	public ResponseEntity<ResponseDto<Page<U>>> readAll(int p) {

		log.info("Controller: méthode READALL appelée");

		try {
			ServiceResponse<Page<E>> serviceResponse = service.readAll(p);
			return makeUpdateDtoPageResponse(serviceResponse);
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Controller readAll" + e.getMessage());
			ResponseDto<Page<U>> responseDto = new ResponseDto<>();
			responseDto.setError(true);
			responseDto.setMessage("Erreur méthode Abstract Controller readAll" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
		}

	}

	/**
	 * Regroupe les elements communs des méthodes qui retournent des updateDto
	 * 
	 * @param serviceResponse Reponse d'une méthode de la couche service de la
	 *                        méthode
	 * @return Response Entity contenant la ResponseDto. Body: UpdateDto
	 */
	protected ResponseEntity<ResponseDto<U>> makeUpdateDtoResponse(ServiceResponse<E> serviceResponse) {

		log.debug("Controller: makeUpdateDtoResponse");

		try {
			ResponseDto<U> responseDto = new ResponseDto<U>();
			responseDto.setMessage(serviceResponse.getMessage());

			if (serviceResponse.getBody() != null) {

				log.debug("Controller/makeUpdateDtoResponse: retour de la couche service NON-NULL");

				U returnedDto = converter.convertEntityToUpdateDto(serviceResponse.getBody());
				responseDto.setBody(returnedDto);
				responseDto.setError(false);

				return ResponseEntity.status(HttpStatus.OK).body(responseDto);

			} else {

				log.debug("Controller/makeUpdateDtoResponse: Statut 400. Cause: " + serviceResponse.getMessage());

				responseDto.setError(true);
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);

			}
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Controller makeUpdateDtoResponse" + e.getMessage());
			return null;
		}

	}

	/**
	 * Regroupe les elements communs des méthodes qui retournent des listes
	 * d'updateDto
	 * 
	 * @param serviceResponse Reponse de la couche service de la méthode
	 * @return Response Entity contenant la ResponseDto. Body: une liste d'updateDto
	 */
	protected ResponseEntity<ResponseDto<List<U>>> makeUpdateDtoListResponse(ServiceResponse<List<E>> serviceResponse) {

		log.debug("Controller: makeUpdateDtoListResponse");

		try {
			ResponseDto<List<U>> responseDto = new ResponseDto<List<U>>();
			List<U> periodeList = converter.convertListEntityToUpdateDto(serviceResponse.getBody());

			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(periodeList);
			responseDto.setError(false);
			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Controller makeUpdateDtoListResponse" + e.getMessage());
			return null;
		}

	}

	/**
	 * Regroupe les elements communs des méthodes qui retournent des listes
	 * d'updateDto
	 * 
	 * @param serviceResponse Reponse de la couche service de la méthode
	 * @return Response Entity contenant la ResponseDto. Body: Une page d'updateDto
	 */
	protected ResponseEntity<ResponseDto<Page<U>>> makeUpdateDtoPageResponse(ServiceResponse<Page<E>> serviceResponse) {

		log.debug("Controller: makeUpdateDtoPageResponse");

		try {
			ResponseDto<Page<U>> responseDto = new ResponseDto<Page<U>>();
			Page<U> periodeList = converter.convertPageEntityToUpdateDto(serviceResponse.getBody());

			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(periodeList);
			responseDto.setError(false);

			return ResponseEntity.ok(responseDto);
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Controller makeUpdateDtoPageResponse" + e.getMessage());
			return null;
		}

	}

	private boolean accessControl(String key, Role userRole) {
		Role lowestRole = this.niveauDAcces.get(key);

		return (lowestRole == Role.None) || (userRole == lowestRole)
				|| ((lowestRole == Role.Utilisateur) && (userRole == Role.Admin));
	}

}
