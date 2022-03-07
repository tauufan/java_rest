package com.bjb.api.service.AccountBalance;

import com.bjb.api.helper.Header;
import com.bjb.api.helper.MPI;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class AccountBalance {
	
	
	public AccountBalance() {
		
	}
	
	public String GetAccountBalance(MPI mpi, Header header) throws JsonProcessingException {
		String res = "";
		
		header.setMPI(mpi);
        
        ObjectWriter ow = new ObjectMapper()
        		.setSerializationInclusion(Include.NON_NULL)
        		.writer()
        		.withDefaultPrettyPrinter();
        
        res = ow.writeValueAsString(header);
		
		return res;
	}
	
	public String GetAccountBalance(String ZLEAN) throws JsonProcessingException {
		String res = "";
		
		Header hdr = new Header();
        MPI mpi = new MPI();
        mpi.setZLEAN(ZLEAN);
		
        hdr.setMPI(mpi);
        
        ObjectWriter ow = new ObjectMapper()
        		.setSerializationInclusion(Include.NON_NULL)
        		.writer()
        		.withDefaultPrettyPrinter();
        
        res = ow.writeValueAsString(hdr);
		
		return res;
	}
	
	public String GetAccountBalance(String ZLAB, String ZLAN, String ZLAS) throws JsonProcessingException {
		String res = "";
		
		Header hdr = new Header();
        MPI mpi = new MPI();
        mpi.setZLAB(ZLAB);
        mpi.setZLAN(ZLAN);
        mpi.setZLAS(ZLAS);
		
        hdr.setMPI(mpi);
        
        ObjectWriter ow = new ObjectMapper()
        		.setSerializationInclusion(Include.NON_NULL)
        		.writer()
        		.withDefaultPrettyPrinter();
        
        res = ow.writeValueAsString(hdr);
		
		return res;
	}
	
}
