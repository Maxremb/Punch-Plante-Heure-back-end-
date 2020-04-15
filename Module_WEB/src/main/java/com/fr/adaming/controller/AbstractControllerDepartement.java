package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
 *         Classe abstraite qui implemente IControllerDepartement Redefinition
 *         des méthodes CRUD + readByName + readMeteoByNumDep
 *         </p>
 *
 * @param <D>  DepartementDto
 * @param <MU> MeteoUpdateDto
 * @param <MC> MeteoCreateDto
 * @param <ME> Meteo entité
 * @param <E>  Departement entité
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

		try {
			ServiceResponse<E> serviceResponse = service.create(converter.convertDtoToEntity(dto));

			D returnedDto = converter.convertEntityToDto(serviceResponse.getBody());

			ResponseDto<D> responseDto = new ResponseDto<D>();

			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(returnedDto);

			if (returnedDto != null) {
				responseDto.setError(false);
				log.info("Controller: méthode CREATE - Succes");
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				responseDto.setError(true);
				log.info("Controller: méthode CREATE - Erreur");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Dep Controller create" + e.getMessage());
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseDto<D>> deleteById(int id) {
		log.info("Controller: méthode DELETE appelée");

		try {
			boolean result = service.deleteById(id);
			ResponseDto<D> responseDto = new ResponseDto<>();

			if (result) {
				responseDto.setError(false);
				responseDto.setMessage("Suppression réussie");
				responseDto.setBody(null);
				log.info("Controller: méthode DELETE - Succes");
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				responseDto.setError(true);
				responseDto.setMessage("Erreur pendant la suppression de l'entité: " + id);
				responseDto.setBody(null);
				log.info("Controller: méthode DELETE - Erreur");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Dep Controller deleteById" + e.getMessage());
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseDto<D>> update(D dto) {

		log.info("Controller: méthode UPDATE appelée");

		try {
			ServiceResponse<E> serviceResponse = service.update(converter.convertDtoToEntity(dto));

			D returnedDto = converter.convertEntityToDto(serviceResponse.getBody());

			ResponseDto<D> responseDto = new ResponseDto<D>();
			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(returnedDto);

			if (returnedDto != null) {
				responseDto.setError(false);
				log.info("Controller: méthode UPDATE - Succes");
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				responseDto.setError(true);
				log.info("Controller: méthode UPDATE - Echec");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Dep Controller update" + e.getMessage());
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseDto<D>> readById(int id) {

		log.info("Controller: méthode READBYID appelée");

		try {
			ServiceResponse<E> serviceResponse = service.readById(id);

			D returnedDto = converter.convertEntityToDto(serviceResponse.getBody());

			ResponseDto<D> responseDto = new ResponseDto<D>();
			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(returnedDto);

			if (returnedDto != null) {
				responseDto.setError(false);
				log.info("Controller: méthode READBYID - Succes");
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				responseDto.setError(true);
				log.info("Controller: méthode READBYID - Erreur");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Dep Controller readById" + e.getMessage());
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseDto<Page<D>>> readAll(int p) {

		log.info("Controller: méthode READALL appelée");

		try {
			ServiceResponse<Page<E>> serviceResponse = service.readAll(p);

			Page<D> returnedPage = converter.convertPageEntityToDto(serviceResponse.getBody());
			ResponseDto<Page<D>> responseDto = new ResponseDto<>();
			responseDto.setError(false);
			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(returnedPage);
			log.info("Controller: méthode READALL - Succes");
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Dep Controller readAll" + e.getMessage());
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseDto<List<D>>> readAllList() {

		log.info("Controller: méthode READALL liste appelée");

		try {
			ServiceResponse<List<E>> serviceResponse = serviceDep.readAllList();

			List<D> listReturned = converter.convertListEntityToDto(serviceResponse.getBody());
			ResponseDto<List<D>> responseDto = new ResponseDto<List<D>>();
			responseDto.setError(false);
			responseDto.setMessage(serviceResponse.getMessage());
			responseDto.setBody(listReturned);
			log.info("Controller: méthode READALL liste - Succes");
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);

		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Dep Controller realAllList" + e.getMessage());
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseDto<D>> readByName(String name) {

		log.info("Controller: méthode READBYNAME appelée");

		try {
			ServiceResponse<E> serviceResponse = serviceDep.readDepartementByNom(name);

			ResponseDto<D> responseDto = new ResponseDto<>();

			if (serviceResponse.getBody() == null) {
				responseDto.setError(true);
				responseDto.setMessage(serviceResponse.getMessage());
				responseDto.setBody(null);
				log.info("Controller: méthode READBYNAME - Erreur");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			} else {
				D returned = converter.convertEntityToDto(serviceResponse.getBody());
				responseDto.setError(false);
				responseDto.setMessage(serviceResponse.getMessage());
				responseDto.setBody(returned);
				log.info("Controller: méthode READBYNAME - Succes");
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Dep Controller readByName" + e.getMessage());
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseDto<Page<MU>>> readMeteoByNumDep(int id, int page, int elementsPerPage,
			String sortName) {

		log.info("Controller: méthode READMETEOBYNUMDEP appelée");

		try {
			
			Pageable pageable = PageRequest.of(page, elementsPerPage, Sort.by(sortName).descending());
			
			ServiceResponse<Page<ME>> serviceResponse = serviceDep.readMeteoByNumeroDep(pageable, id);

			ResponseDto<Page<MU>> responseDto = new ResponseDto<Page<MU>>();

			if (serviceResponse.getBody() == null) {
				responseDto.setError(true);
				responseDto.setMessage(serviceResponse.getMessage());
				responseDto.setBody(null);
				log.info("Controller: méthode READMETEOBYNUMDEP - Erreur");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			} else {
				Page<MU> returnedList = converterMeteo.convertPageEntityToUpdateDto(serviceResponse.getBody());
				responseDto.setError(false);
				responseDto.setMessage(serviceResponse.getMessage());
				responseDto.setBody(returnedList);
				log.info("Controller: méthode READMETEOBYNUMDEP - Succes");
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Abstract Dep Controller readMeteoByNumDep" + e.getMessage());
			return null;
		}
	}

}
