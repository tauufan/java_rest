package com.bjb.api.model2;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Immutable
@Table(name = "vw_aim")
@Subselect("SELECT row_number() OVER (ORDER BY B0AB, B0AN, B0AS) as id, aim.* FROM vw_aim aim")
public class Aim {
	
	@Id
	private Long id;
	
	@JsonProperty("B0LPV")
	private String b0lpv;
	
	@JsonProperty("B0AN")
	private String b0an;

	public Aim() {
		
	}
	
	public Aim(Long id, String b0lpv, String b0an) {
		super();
		this.id = id;
		this.b0lpv = b0lpv;
		this.b0an = b0an;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getB0lpv() {
		return b0lpv;
	}

	public void setB0lpv(String b0lpv) {
		this.b0lpv = b0lpv;
	}

	public String getB0an() {
		return b0an;
	}

	public void setB0an(String b0an) {
		this.b0an = b0an;
	}
	
	
}
