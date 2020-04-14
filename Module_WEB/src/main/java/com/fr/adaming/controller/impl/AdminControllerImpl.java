package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.AdminCreateDto;
import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.ConnexionDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.security.TokenManagement;
import com.fr.adaming.service.IAdminService;
import com.fr.adaming.service.IUtilisateurService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(path = "/admin")
@RestController
@CrossOrigin( allowCredentials = "true", origins = "http://localhost:4200")
@Slf4j
public class AdminControllerImpl extends AbstractController<AdminCreateDto, AdminUpdateDto, Admin> {

	@Autowired
	private IAdminService adminService;

	@Autowired
	private IUtilisateurService userService;

	@Autowired
	private IConverter<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur> utilConv;

	@Autowired
	private IConverter<AdminCreateDto, AdminUpdateDto, Admin> adminConv;
	
	@Autowired
	private TokenManagement tokenManagement;

	@GetMapping(path = "/pseudo")
	public ResponseEntity<ResponseDto<AdminUpdateDto>> readByPseudonyme(@RequestParam(name = "pseudo") String pseudo) {
		try {
			ServiceResponse<Admin> resp = adminService.readByPseudonyme(pseudo);
			return makeUpdateDtoResponse(resp);
		} catch (Exception e) {
			log.warn("Erreur méthode Admin Controller readBypseudonyme" + e.getMessage());
			return null;
		}
	}

	@GetMapping(path = "/mail")
	public ResponseEntity<ResponseDto<AdminUpdateDto>> readByEmail(@RequestParam(name = "mail") String mail) {
		try {
			ServiceResponse<Admin> resp = adminService.readByEmail(mail);
			return makeUpdateDtoResponse(resp);
		} catch (Exception e) {
			log.warn("Erreur méthode Admin Controller readByEmail" + e.getMessage());
			return null;
		}
	}

	@GetMapping(path = "/exists/mail")
	public ResponseEntity<ResponseDto<Boolean>> existsByMail(@RequestParam(name = "mail") String mail) {
		log.info("Controller: méthode existsByMail appelée");

		try {
			boolean result = adminService.existsByEmail(mail);
			ResponseDto<Boolean> responseDto = new ResponseDto<>();

			if (result) {
				log.info("Admin existant dans DB");
				responseDto.setError(false);
				responseDto.setMessage("L'admin existe dans la DB");
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				log.info("Aucun admin avec cet email");
				responseDto.setError(true);
				responseDto.setMessage("Aucun admin n'existe avec mail : " + mail);
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Admin Controller existsByEMail" + e.getMessage());
			return null;
		}
	}

	@GetMapping(path = "/exists/pseudo")
	public ResponseEntity<ResponseDto<Boolean>> existsByPseudo(@RequestParam(name = "pseudo") String pseudo) {
		log.info("Controller: méthode existsByPseudo appelée");

		try {
			boolean result = adminService.existsByPseudonyme(pseudo);
			ResponseDto<Boolean> responseDto = new ResponseDto<>();

			if (result) {
				log.info("Admin existant dans DB");
				responseDto.setError(false);
				responseDto.setMessage("L'admin existe dans la DB");
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			} else {
				log.info("Aucun admin n'existe avec ce pseudo");
				responseDto.setError(true);
				responseDto.setMessage("Aucun admin n'existe avec pseudo : " + pseudo);
				responseDto.setBody(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
		} catch (Exception e) {
			log.warn("Erreur méthode Admin Controller existsByPseudo" + e.getMessage());
			return null;
		}
	}

	@PostMapping(path = "/mailAndPwd")
	public ResponseEntity<ConnexionDto> existsByMailandPwd(@RequestBody String[] tableau) {
		ConnexionDto connexionDto = new ConnexionDto();
		try {

			String mail = tableau[0];
			String pwd = tableau[1];
			if (userService.existsByEmailAndMdp(mail, pwd).getBody() != null) {
				log.info("Admin existant dans la DB");
				ServiceResponse<Utilisateur> serviceResponse = userService.existsByEmailAndMdp(mail, pwd);

				UtilisateurUpdateDto returnedUtil = utilConv.convertEntityToUpdateDto(serviceResponse.getBody());
				String token = tokenManagement.makeNewSession(returnedUtil); // Generation de tokens pour la sécurité du front

				connexionDto.setUser(true);
				connexionDto.setBodyAdmin(null);
				connexionDto.setBodyUtil(returnedUtil);
				connexionDto.setToken(token);

				return ResponseEntity.status(HttpStatus.OK).body(connexionDto);

			} else if (adminService.existsByEmailAndMdp(mail, pwd).getBody() != null
					&& userService.existsByEmailAndMdp(mail, pwd).getBody() == null) {
				log.info("Utilisateur existant dans la DB");
				ServiceResponse<Admin> serviceResponse = adminService.existsByEmailAndMdp(mail, pwd);

				AdminUpdateDto returnedAdmin = adminConv.convertEntityToUpdateDto(serviceResponse.getBody());
				String token = tokenManagement.makeNewSession(returnedAdmin);

				connexionDto.setUser(false);
				connexionDto.setBodyAdmin(returnedAdmin);
				connexionDto.setBodyUtil(null);
				connexionDto.setToken(token);

				return ResponseEntity.status(HttpStatus.OK).body(connexionDto);
			} else {
				log.info("Aucune entité ne correspond à ces entrées");
				connexionDto.setUser(false);
				connexionDto.setBodyAdmin(null);
				connexionDto.setBodyUtil(null);
				connexionDto.setToken(null);

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(connexionDto);
			}

		} catch (NullPointerException e) {
			log.info("Null Pointer Exception" + e.getMessage());
			connexionDto.setUser(false);
			connexionDto.setBodyAdmin(null);
			connexionDto.setBodyUtil(null);
			connexionDto.setToken(null);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(connexionDto);
		}

	}

}
