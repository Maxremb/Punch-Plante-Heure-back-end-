package com.fr.adaming.dto;

import java.util.List;



import com.fr.adaming.entity.Periode;
import com.fr.adaming.enums.Sol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlanteModelCreateDto {


	private String commun;
	private String scientifique;
	private List<Periode> periodes;
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
	private List<PlanteModelUpdateDto> positive;
	private List<PlanteModelUpdateDto> negative;
}
