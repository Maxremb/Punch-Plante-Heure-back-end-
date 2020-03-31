package com.fr.adaming.converter;

import com.fr.adaming.dto.UserCreateDto;
import com.fr.adaming.entity.User;

public class UserConverter {

	public static User convertDtoToUser(UserCreateDto dto) {
		if (dto == null) {
			return null;
		}
		User user = new User();
		user.setNom(dto.getLastName());
		user.setPrenom(dto.getFirstName());
		return user;
	}

	public static UserCreateDto convertUserToDto(User entity) {
		if (entity == null) {
			return null;
		}
		UserCreateDto dto = new UserCreateDto();
		dto.setFirstName(entity.getPrenom());
		dto.setLastName(entity.getNom());
		return dto;
	}
}
