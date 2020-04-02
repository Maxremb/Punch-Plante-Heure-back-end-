package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fr.adaming.enums.Sol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Léa
 *
 *         Le dictionnaire des données botanique du site.
 *         AutoJointure sur les associations positives et négatives
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlanteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(length = 30, nullable = false)
	String nomCommun;

	@Column(length = 30, unique = true, nullable = false)
	String nomScientifique;

	@Column
	@OneToMany
	List<Periode> dates;

	@Column
	int intervalArrosage;

	@Column
	String ensoleillementOpti;

	@Column
	int humiditeopti;

	@Column
	Sol solOpti;

	@Column
	int repiquage;

	@Column
	int temperatureMin;

	@Column
	int temperatureMax;

	@Column
	String description;

	@Column
	boolean toxicite;

	@Column
	String photo;

	@Column
	@ManyToMany
	List<PlanteModel> assoPositive;

	@Column
	@ManyToMany
	List<PlanteModel> assoNegative;

}
