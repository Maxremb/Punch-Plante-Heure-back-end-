package com.fr.adaming.converter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;




public abstract class AbstractConverter<C, U, E> implements IConverter<C, U, E> {
	
	
	@Override
	public Page<E> convertListCreateDtoToEntity(Page<C> listeCreateDto) {
		Page<E> listeRetour = new PageImpl<E>(null);
		for(C p : listeCreateDto) {
			
			listeRetour.and(convertCreateDtoToEntity(p));
		}
		return listeRetour;
	}

	@Override
	public Page<C> convertListEntityToCreateDto(Page<E> listeEntity) {
		Page<C> listeRetour = new PageImpl<C>(null);
		for(E p: listeEntity) {
			listeRetour.and(convertEntityToCreateDto(p));
		}
		return listeRetour;
	}

	@Override
	public Page<E> convertListUpdateDtoToEntity(Page<U> listeUpdateDto) {
		Page<E> listeRetour = new PageImpl<E>(null);
		for(U p:listeUpdateDto) {
			listeRetour.and(convertUpdateDtoToEntity(p));
		}
		return listeRetour;
	}

	@Override
	public Page<U> convertListEntityToUpdateDto(Page<E> listeEntity) {
		Page<U> listeRetour = new PageImpl<U>(null);
		for(E p: listeEntity) {
			listeRetour.and(convertEntityToUpdateDto(p));
		}
		return listeRetour;
	}

}
