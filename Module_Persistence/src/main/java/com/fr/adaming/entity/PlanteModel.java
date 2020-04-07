
package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fr.adaming.enums.Sol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Léa
 *
 *         Le dictionnaire des données botanique du site. AutoJointure sur les
 *         associations positives et négatives
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PlanteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 30)
	private String nomCommun;

	@Column(length = 30, nullable = false)
	private String nomScientifique;

//	@Column
//	@OneToMany
//	private List<Periode> dates;

	@Column(columnDefinition = "INT DEFAULT 0")
	private int intervalArrosage;

	@Column
	private String ensoleillementOpti;

	@Column(columnDefinition = "INT DEFAULT 0")
	private int humiditeopti;

	@Column
	private Sol solOpti;

	@Column(columnDefinition = "INT DEFAULT 0")
	private int repiquage;

	@Column(columnDefinition = "INT DEFAULT 0")
	private int temperatureMin;

	@Column(columnDefinition = "INT DEFAULT 0")
	private int temperatureMax;

	@Column
	private String description;

	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private boolean toxicite;

	@Column
	private String photo;

	// TODO faire des tables de jointure avec une entité famille pour les asso positives et negatives
	
	@Column
	@Transient
	private String[] assoPositive;

	@Column
	@Transient
	private String[] assoNegative;
	
	@Column
	private String famille;
	
	@Column
	private String vegetation ;
	
	@Column
	private String feuillage ;
	
	@Column(columnDefinition = "FLOAT DEFAULT 0")
	private float hauteur ;

	public PlanteModel(int id, String nomCommun, String nomScientifique, String photo) {
		super();
		this.id = id;
		this.nomCommun = nomCommun;
		this.nomScientifique = nomScientifique;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "PlanteModel [id=" + id + ", nomCommun=" + nomCommun + ", nomScientifique=" + nomScientifique + "]";
	}

}
