package com.fr.adaming.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseDto<T> {
	
	boolean error;
	String message;
	T body;

}
