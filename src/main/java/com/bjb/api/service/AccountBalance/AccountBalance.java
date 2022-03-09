package com.bjb.api.service.AccountBalance;

import com.bjb.api.helper.Header;
import com.bjb.api.helper.MPI;
import com.bjb.api.service.SocketTG;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountBalance {
	
        private final static Logger logger = LoggerFactory.getLogger(AccountBalance.class);
	
        private String host;
        private int port;
        
	public AccountBalance() {
		setHost("10.6.226.216");
                setPort(48484);
	}
	
	public String GetAccountBalance(MPI mpi, Header header) throws JsonProcessingException {
		String res = "";
		
		header.setMpi(mpi);
        
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
        mpi.setZlean(ZLEAN);
		
        hdr.setMpi(mpi);
        
        ObjectWriter ow = new ObjectMapper()
        		.setSerializationInclusion(Include.NON_NULL)
        		.writer();
        
        res = ow.writeValueAsString(hdr);
		
//        logger.info(" === Connect to === "+ getHost()+":"+getPort() );
//        logger.info(" === Request to === "+ getHost()+":"+getPort()+" "+res );
        SocketTG sockt = new SocketTG();
        res = sockt.sendDataSocketTG(getHost(), getPort() , res);
//        logger.info(" === Response from === "+ getHost()+":"+getPort()+" "+res );
//        logger.info(" === Disconnect from === "+ getHost()+":"+getPort() );
        return res;
	}
	
	public String GetAccountBalance(String ZLAB, String ZLAN, String ZLAS) throws JsonProcessingException {
		String res = "";
		
		Header hdr = new Header();
        MPI mpi = new MPI();
        mpi.setZlab(ZLAB);
        mpi.setZlan(ZLAN);
        mpi.setZlas(ZLAS);
		
        hdr.setMpi(mpi);
        
        ObjectWriter ow = new ObjectMapper()
        		.setSerializationInclusion(Include.NON_NULL)
        		.writer();
//        		.withDefaultPrettyPrinter();
        
        res = ow.writeValueAsString(hdr);
//        logger.info(" === Connect to === "+ getHost()+":"+getPort() );
//        logger.info(" === Request to === "+ getHost()+":"+getPort()+" "+res );
        SocketTG sockt = new SocketTG();
        res = sockt.sendDataSocketTG(getHost(), getPort() , res);
//        logger.info(" === Response from === "+ getHost()+":"+getPort()+" "+res );
//        logger.info(" === Disconnect from === "+ getHost()+":"+getPort() );	
	return res;
	}

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
	
        
}
