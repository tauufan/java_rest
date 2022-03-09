package com.bjb.api.service.socket;

import id.co.vsi.common.crypto.SymetricCryptoHandler;
import java.io.*;
import java.net.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SocketTG {
	private final static Logger logger = LoggerFactory.getLogger(SocketTG.class);
	
	private String type = "AES";
	private String mode = "ECB";
	private String padding = "PKCS5Padding";
	private String key = "tgadapterbjb2012";
//	timeout 3 menit
	private int timeout;
	private byte cEndMessageByte;
	
	private SymetricCryptoHandler enkriptor;
	
	public SocketTG() {
		setTimeout(3);
		setcEndMessageByte((byte)-0x01);
	}
	
	
	public String sendDataSocketTG(String host, int port,String msg) {
        String result = "";
//        final byte cEndMessageByte = -0x01;
        Socket requestSocket;
        StringBuilder concatnator = new StringBuilder();
        
	
       try {
            enkriptor = new SymetricCryptoHandler(getType(), getMode(), getPadding(), getKey(), null);
            logger.info(" === Connect to === "+ host+":"+port );
            requestSocket = new Socket(host, port);
            requestSocket.setSoTimeout(getTimeout());
            ByteArrayOutputStream tRequestByteStream = null;
            tRequestByteStream = new ByteArrayOutputStream();
            
            String sendMsg = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_ENCRYPT, msg);
//            System.out.println("Message from bjb FAST : " + sendMsg.toString());
            
            tRequestByteStream.write(sendMsg.getBytes());
            tRequestByteStream.write(cEndMessageByte);
            
//            System.out.println(tRequestByteStream.toByteArray());
            logger.info(" === Request to === "+ host+":"+port+" "+msg );
            requestSocket.getOutputStream().write(tRequestByteStream.toByteArray());
            requestSocket.getOutputStream().flush();
//            requestSocket.getOutputStream().close();
            byte tMessageByte = cEndMessageByte;
            
             while ((tMessageByte = (byte) requestSocket.getInputStream().read()) != cEndMessageByte) {
                /*untuk menampilkan ingoing per char */
//                System.out.println(tMessageByte + "-" +(char) tMessageByte);
                
//                sb.append((char) tMessageByte);
                concatnator.append((char) tMessageByte);
            }
            	
             result = concatnator.toString();
             
//             System.out.println("Response Message to bjb FAST : " + result);
             
             result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, result);
//             result = concatnator.toString();
             logger.info(" === Response from === "+ host+":"+port+" "+result );
             requestSocket.getOutputStream().close();
             requestSocket.close();
             logger.info(" === Disconnect from === "+ host+":"+port );
             concatnator = clearStringBuilder(concatnator);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
        return result;
    }
	
	private StringBuilder clearStringBuilder(StringBuilder sb) {
        sb.delete(0, sb.length());
        return sb;
      }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getPadding() {
		return padding;
	}

	public void setPadding(String padding) {
		this.padding = padding;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout*60*1000;
	}


	public byte getcEndMessageByte() {
		return cEndMessageByte;
	}


	public void setcEndMessageByte(byte cEndMessageByte) {
		this.cEndMessageByte = cEndMessageByte;
	}
	
	
}
