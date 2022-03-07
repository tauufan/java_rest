package com.bjb.api.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class Header {
	
	Date dt = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdfr = new SimpleDateFormat("HHmmssSSSS");
	
	private String PC = "001001";
	private String PCC = "5";
	private String ST = sdfr.format(dt);
	private String CC = "0001";
	private String FC = "AB";
	private String DT = sdf.format(dt);
	private String MC = "90023";
	private String MT = "9200";
	private String CID = "TG-CB-00-1000069";
	
	MPI mpi = new MPI();
	
	private MPI MPI = mpi;
	
	public Header() {
		
	}
	
	public Header(String pC, String pCC, String sT, String cC, String fC, String dT, String mC, String mT,
			String cID, MPI mPI) {
		super();
		PC = pC;
		PCC = pCC;
		ST = sT;
		CC = cC;
		FC = fC;
		DT = dT;
		MC = mC;
		MT = mT;
		CID = cID;
		MPI = mPI;
	}
	public String getPC() {
		return PC;
	}
	public void setPC(String pC) {
		PC = pC;
	}
	public String getPCC() {
		return PCC;
	}
	public void setPCC(String pCC) {
		PCC = pCC;
	}
	public String getST() {
		return ST;
	}
	public void setST(String sT) {
		ST = sT;
	}
	public String getCC() {
		return CC;
	}
	public void setCC(String cC) {
		CC = cC;
	}
	public String getFC() {
		return FC;
	}
	public void setFC(String fC) {
		FC = fC;
	}
	public String getDT() {
		return DT;
	}
	public void setDT(String dT) {
		DT = dT;
	}
	public String getMC() {
		return MC;
	}
	public void setMC(String mC) {
		MC = mC;
	}
	public String getMT() {
		return MT;
	}
	public void setMT(String mT) {
		MT = mT;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}

	public MPI getMPI() {
		return MPI;
	}

	public void setMPI(MPI mPI) {
		MPI = mPI;
	}
	
}
