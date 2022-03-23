package com.bjb.api.model2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Immutable
@Table(name = "vw_cim_harian")
@Subselect("SELECT row_number() OVER (ORDER BY BGCUS) as id, cim.* FROM vw_cim_harian cim")
public class CimDwh {
	
	@Id
	private Long id;
	
	@JsonProperty("BGLPV")
	private String bglpv;
	
	@JsonProperty("BGIGR")
	private String bgigr;
	
	@JsonProperty("CIF")
	private String bgcus;
	
	@JsonProperty("NAMA_LENGKAP")
	private String bg0006;
	
	public CimDwh() {
		
	}
	
	public CimDwh(Long id, String bglpv, String bgigr, String bgcus, String bg0006) {
		super();
		this.id = id;
		this.bglpv = bglpv;
		this.bgigr = bgigr;
		this.bgcus = bgcus;
		this.bg0006 = bg0006;
	}

	public Long getId() {
		return id;
	}

	public String getBglpv() {
		return bglpv;
	}

	public String getBgigr() {
		return bgigr;
	}

	public String getBgcus() {
		return bgcus;
	}

	public String getBg0006() {
		return bg0006;
	}
	
	
}
