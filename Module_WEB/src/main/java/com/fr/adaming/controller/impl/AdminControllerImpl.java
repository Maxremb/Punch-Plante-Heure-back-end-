package com.fr.adaming.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.AbstractController;
import com.fr.adaming.dto.AdminCreateDto;
import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping (path = "/admin")
@RestController
@CrossOrigin
@Slf4j
public class AdminControllerImpl extends AbstractController<AdminCreateDto, AdminUpdateDto, Admin>{
	
	@Autowired
	private IAdminService adminService;
	
	@GetMapping (path = "/pseudo")
	public ResponseEntity<ResponseDto<AdminUpdateDto>> readByPseudonyme(@RequestParam (name = "pseudo") String pseudo) {
		return null;
		
	}
	
	@GetMapping (path = "/mail")
	public ResponseEntity<ResponseDto<AdminUpdateDto>> readByEmail (@RequestParam (name = "mail") String mail) {
		return null;
		
	}
	
	
	@GetMapping (path = "/mailAndPwd")
	public ResponseEntity<ResponseDto<AdminUpdateDto>> readByEmailAndPwd (@RequestParam (name = "mail") String mail, @RequestParam (name = "pwd") String pwd){
		return null;
		
	}
	
	
	@GetMapping (path = "/exists/mail")
	public ResponseEntity<ResponseDto<Boolean>> existsByMail (@RequestParam (name = "mail") String mail) {
		log.info("Controller: méthode existsByMail appelée");

		boolean result = adminService.existsByEmail(mail);
		ResponseDto<Boolean> responseDto = new ResponseDto<>();

		if (result) {
			responseDto.setError(false);
			responseDto.setMessage("L'admin existe dans la DB");
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage("Aucun admin n'existe avec mail : " + mail);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}
	
	@GetMapping (path = "/exists/pseudo")
	public ResponseEntity<ResponseDto<Boolean>> existsByPseudo (@RequestParam (name = "pseudo") String pseudo) {
		log.info("Controller: méthode existsByPseudo appelée");

		boolean result = adminService.existsByPseudonyme(pseudo);
		ResponseDto<Boolean> responseDto = new ResponseDto<>();

		if (result) {
			responseDto.setError(false);
			responseDto.setMessage("L'admin existe dans la DB");
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto.setError(true);
			responseDto.setMessage("Aucun admin n'existe avec pseudo : " + pseudo);
			responseDto.setBody(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
	}
	

}