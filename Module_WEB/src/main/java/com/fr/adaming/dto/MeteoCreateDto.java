package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'objet météo à communiquer au front lors de la création
 * 
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MeteoCreateDto {

	private double tempMax;

	private double tempMin;

	private double rain;

	private double radiation;

	private double etp;

	@Column(nullable = false)
	private LocalDate dateMeteo;

	@Column(nullable = false)
	private DepartementDto departement;

}
