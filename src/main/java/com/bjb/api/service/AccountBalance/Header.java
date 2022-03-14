package com.bjb.api.service.AccountBalance;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Header {
	
	Date Dt = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdfr = new SimpleDateFormat("HHmmssSSSS");
	
	@JsonProperty("PC")
	private String pc = "001001";
	@JsonProperty("PCC")
	private String pcc = "5";
	@JsonProperty("ST")
	private String st = sdfr.format(Dt);
	@JsonProperty("CC")
	private String cc = "0001";
	@JsonProperty("FC")
	private String fc = "AB";
	@JsonProperty("DT")
	private String dt = sdf.format(Dt);
	@JsonProperty("MC")
	private String mc = "90023";
	@JsonProperty("MT")
	private String mt = "9200";
	@JsonProperty("CID")
	private String cid = "TG-CB-00-1000069";
	@JsonProperty("SID")
	private String sid = "singleUserIDWebTeller";
	MPI Mpi = new MPI();
	
	@JsonProperty("MPI")
	private MPI mpi = Mpi;
	
	public Header() {
		
	}

	public Header(String pc, String pcc, String st, String cc, String fc, String dt, String mc, String mt, String cid,
			String sid, MPI mpi) {
		super();
		this.pc = pc;
		this.pcc = pcc;
		this.st = st;
		this.cc = cc;
		this.fc = fc;
		this.dt = dt;
		this.mc = mc;
		this.mt = mt;
		this.cid = cid;
		this.sid = sid;
		this.mpi = mpi;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getPcc() {
		return pcc;
	}

	public void setPcc(String pcc) {
		this.pcc = pcc;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public MPI getMpi() {
		return mpi;
	}

	public void setMpi(MPI mpi) {
		this.mpi = mpi;
	}

	
}
