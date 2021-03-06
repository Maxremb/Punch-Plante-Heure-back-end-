package com.fr.adaming.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'objet à communiquer au front pour la modification de Jardin
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class JardinUpdateDto extends JardinCreateDto{

	@NotNull
	@Min(value = 1)
	private Integer identifier;

	private double maxReserve;
	
	private double UsefullReserve;
	
}
