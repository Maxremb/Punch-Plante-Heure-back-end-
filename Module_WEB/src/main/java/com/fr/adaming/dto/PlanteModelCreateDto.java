package com.fr.adaming.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.Sol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'objet plante model à communiquer au front lors de
 * la création
 * 
 * @since 0.0.1-SNAPSHOT
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PlanteModelCreateDto {

	private String commun;
	@NotNull(message = "Le nom scientifique de la plante ne peut pas être null")
	private String scientifique;
//	private List<PeriodeUpdateDto> periodes;
	private int arrosage;
	private String ensoleillement;
	private int humidite;
	private Sol sol;
	private int repiquage;
	private int min;
	private int max;
	private String desc;
	private boolean toxi;
	private String picture;
	private String[] positive;
	private String[] negative;
	private String mifa;
	private String feuille;
	private float height;
	private String veget;
}
