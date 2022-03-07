package com.bjb.api.helper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

public class MPI {
	
	@JsonIgnore
	private String accuse = "externalaccount";

	private String ZLEAN;
	private String ZLAB;
	private String ZLAN;
	private String ZLAS;
	
	public MPI() {
		
	}
	
	public MPI(String zLEAN, String zLAB, String zLAN, String zLAS) {
		super();
		ZLEAN = zLEAN;
		ZLAB = zLAB;
		ZLAN = zLAN;
		ZLAS = zLAS;
	}
	
	public String getAccuse() {
		return accuse;
	}
	public void setAccuse(String accuse) {
		this.accuse = accuse;
	}
	public String getZLEAN() {
		return ZLEAN;
	}
	public void setZLEAN(String zLEAN) {
		ZLEAN = zLEAN;
	}
	public String getZLAB() {
		return ZLAB;
	}
	public void setZLAB(String zLAB) {
		ZLAB = zLAB;
	}
	public String getZLAN() {
		return ZLAN;
	}
	public void setZLAN(String zLAN) {
		ZLAN = zLAN;
	}
	public String getZLAS() {
		return ZLAS;
	}
	public void setZLAS(String zLAS) {
		ZLAS = zLAS;
	}
	
	
}
