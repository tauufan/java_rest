package com.bjb.api.helper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MPI {
	
	@JsonIgnore
	private String accuse = "externalaccount";
	
	@JsonProperty("ZLEAN")
	private String zlean;
	@JsonProperty("ZLAB")
	private String zlab;
	@JsonProperty("ZLAN")
	private String zlan;
	@JsonProperty("ZLAS")
	private String zlas;
	
	public MPI() {
		
	}

	
	public MPI(String zlean, String zlab, String zlan, String zlas) {
		super();
		this.zlean = zlean;
		this.zlab = zlab;
		this.zlan = zlan;
		this.zlas = zlas;
	}


	@Override
	public String toString() {
		return "MPI [zlean=" + zlean + ", zlab=" + zlab + ", zlan=" + zlan + ", zlas=" + zlas + "]";
	}


	public String getAccuse() {
		return accuse;
	}

	public void setAccuse(String accuse) {
		this.accuse = accuse;
	}

	public String getZlean() {
		return zlean;
	}

	public void setZlean(String zlean) {
		this.zlean = zlean;
	}

	public String getZlab() {
		return zlab;
	}

	public void setZlab(String zlab) {
		this.zlab = zlab;
	}

	public String getZlan() {
		return zlan;
	}

	public void setZlan(String zlan) {
		this.zlan = zlan;
	}

	public String getZlas() {
		return zlas;
	}

	public void setZlas(String zlas) {
		this.zlas = zlas;
	}
	
		
}
