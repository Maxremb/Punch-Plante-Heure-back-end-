package com.fr.adaming.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageResponseDto<T> extends ResponseDto<T> {
	
	int maxPages;

}
