
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
	private int id;

	@Column(length = 30)
	private String nomCommun;

	@Column(length = 30, nullable = false, unique = true)
	private String nomScientifique;

	@Column
	@OneToMany
	private List<Periode> dates;

	@Column
	private int intervalArrosage;

	@Column
	private String ensoleillementOpti;

	@Column
	private int humiditeopti;

	@Column
	private Sol solOpti;

	@Column
	private int repiquage;

	@Column
	private int temperatureMin;

	@Column
	private int temperatureMax;

	@Column
	private String description;

	@Column
	private boolean toxicite;

	@Column
	private String photo;

	@Column
	@ManyToMany
	private List<PlanteModel> assoPositive;

	@Column
	@ManyToMany
	private List<PlanteModel> assoNegative;

}
