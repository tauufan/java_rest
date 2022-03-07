/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bjb.api.helper;

import id.co.vsi.common.crypto.SymetricCryptoHandler;
import java.io.*;
import java.net.*;

/**
 *
 * @author MJ
 */
public class socketHelper {

    Socket requestSocket;
    //BufferedOutputStream baos;
    //OutputStreamWriter osw;
    //BufferedInputStream bis;
    //InputStreamReader isr;
    String message;
    //StringWriter sw;
    private boolean socketReady = false;
    private String lastError = "";
    private String host;
    private String port;
    private boolean enkrip = false;
    private String type = "AES";
    private String mode = "ECB";
    private String padding = "PKCS5Padding";
    private String key;    
    private final int thousand = 1000;
    private final int minute = 60;
    private StringBuilder concatnator = new StringBuilder();
    // 0 means no timeout
    private int timeout = 3;
    private SymetricCryptoHandler enkriptor;

    public socketHelper(String host, int port) {
        try {
            //1. creating a socket to connect to the server
            this.host = host;
            this.port = String.valueOf(port);
//            this.key = key;
            requestSocket = new Socket(host, port);
            concatnator = clearStringBuilder(concatnator);
            concatnator = concatnator.append("Connected to ");
            System.out.println("sockeeeeet = " + requestSocket);
            concatnator = concatnator.append(host);
            concatnator = concatnator.append(" in port ");
            concatnator = concatnator.append(port);
            System.out.println(concatnator.toString());
            //System.out.println("Create Socket OK : "+System.currentTimeMillis());
            //2. get Input and Output streams
            //out = new ObjectOutputStream(requestSocket.getOutputStream());
            //System.out.println("Get handle of output");
            //out.flush();
            //in = new ObjectInputStream(requestSocket.getInputStream());   
            //System.out.println("Get handle of input");
            socketReady = true;
            setEnkrip(false);
            //enkriptor = new SymetricCryptoHandler(getType(), getMode(), getPadding(), getKey(), null);
        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (Exception exc) {
            socketReady = false;
            lastError = exc.getMessage();
            exc.printStackTrace();
        }
    }
    
    public socketHelper(String host, int port,String key) {
        try {
            //1. creating a socket to connect to the server
            this.host = host;
            this.port = String.valueOf(port);
            this.key = key;
            //System.out.println("New Socket : "+System.currentTimeMillis());
            requestSocket = new Socket(host, port);
            concatnator = clearStringBuilder(concatnator);
            concatnator = concatnator.append("Connected to ");
//            System.out.println("sockeeeeet = " + requestSocket);
            concatnator = concatnator.append(host);
            concatnator = concatnator.append(" in port ");
            concatnator = concatnator.append(port);
            System.out.println(concatnator.toString());
            //System.out.println("Create Socket OK : "+System.currentTimeMillis());
            //2. get Input and Output streams
            //out = new ObjectOutputStream(requestSocket.getOutputStream());
            //System.out.println("Get handle of output");
            //out.flush();
            //in = new ObjectInputStream(requestSocket.getInputStream());   
            //System.out.println("Get handle of input");
            socketReady = true;
            enkriptor = new SymetricCryptoHandler(getType(), getMode(), getPadding(), getKey(), null);
        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (Exception exc) {
            socketReady = false;
            lastError = exc.getMessage();
            exc.printStackTrace();
        }
    }
    
    public socketHelper(String host, int port,String key,int timeout) {
        try {
            //1. creating a socket to connect to the server
            this.host = host;
            this.port = String.valueOf(port);
            this.key = key;
            this.timeout = timeout;
            //System.out.println("New Socket : "+System.currentTimeMillis());
            requestSocket = new Socket(host, port);
//            System.out.println("requestSocket ---- = " + requestSocket);
            concatnator = clearStringBuilder(concatnator);
            concatnator = concatnator.append("Connected to ");
            concatnator = concatnator.append(host);
            concatnator = concatnator.append(" in port ");
            concatnator = concatnator.append(port);
            System.out.println(concatnator.toString());
            //System.out.println("Create Socket OK : "+System.currentTimeMillis());
            //2. get Input and Output streams
            //out = new ObjectOutputStream(requestSocket.getOutputStream());
            //System.out.println("Get handle of output");
            //out.flush();
            //in = new ObjectInputStream(requestSocket.getInputStream());   
            //System.out.println("Get handle of input");
            socketReady = true;
            enkriptor = new SymetricCryptoHandler(getType(), getMode(), getPadding(), getKey(), null);
        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (Exception exc) {
            socketReady = false;
            lastError = exc.getMessage();
            exc.printStackTrace();
        }
    }
    
    public String sendMessage(String msg) {
        String result = "";
        String sendMsg = "";
        //String recMsg = "";
        ByteArrayOutputStream tRequestByteStream = null;
        //BufferedReader br = null;        
        /* end message gapura*/
        final byte cEndMessageByte = -0x01;
        /* end message RPG sssnyo*/
//        final byte cEndMessageByte = 3;
        //StringBuilder sb = new StringBuilder();

        try {

            if (isEnkrip()) {
                sendMsg = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_ENCRYPT, msg);
            } else {
                sendMsg = msg;
            }
            
            //testing only
            //sendMsg = msg;

            //System.out.println("Start Send : "+System.currentTimeMillis());
            tRequestByteStream = new ByteArrayOutputStream();
            tRequestByteStream.write(sendMsg.getBytes());
            tRequestByteStream.write(cEndMessageByte);
            requestSocket.getOutputStream().write(tRequestByteStream.toByteArray());
            //System.out.print("Outgoing :" + tRequestByteStream.toString());

            //System.out.println("Start Write : "+System.currentTimeMillis());
            byte tMessageByte = cEndMessageByte;
            //byte[] b = new byte[1024];
            //int byteRead = 0;

            //testing only
            requestSocket.setSoTimeout(getTimeout() * minute * thousand);

            //tRequestByteStream.reset();

            /*
            while((byteRead=requestSocket.getInputStream().read(b, 0, b.length))!=-1){
            tRequestByteStream.write(b, 0, byteRead);
            }
             */
            //testing only
            concatnator = clearStringBuilder(concatnator);
//            concatnator.append(msg);
            
            
            //testing only
            while ((tMessageByte = (byte) requestSocket.getInputStream().read()) != cEndMessageByte) {
                /*untuk menampilkan ingoing per char */
//                System.out.print(tMessageByte + "-" +(char) tMessageByte);
                
                //sb.append((char) tMessageByte);
                concatnator.append((char) tMessageByte);
            }

            //System.out.println("End Write : "+System.currentTimeMillis());

            //System.out.print("Incoming :" + concatnator.toString());
           
            //testing only
            if (isEnkrip()) {
                result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, concatnator.toString());
                //result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, tRequestByteStream.toString());
            } else {
                //result = sb.toString();
                result = concatnator.toString();
                //result = tRequestByteStream.toString();
            }

            requestSocket.getOutputStream().flush();
            //System.out.println("socket closed : "+requestSocket.isClosed());
            //System.out.println("socket connected : "+requestSocket.isConnected());

        } catch (Exception exc) {
            lastError = exc.getMessage();
            exc.printStackTrace();
        } finally {
            //testing only
            try {
                if (tRequestByteStream != null) {
                    tRequestByteStream.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            
            try {
                requestSocket.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            concatnator = clearStringBuilder(concatnator);
            concatnator = concatnator.append("Disconnected form ");
            concatnator = concatnator.append(host);
            concatnator = concatnator.append(" in port ");
            concatnator = concatnator.append(port);

            System.out.println(concatnator.toString());
             
            //System.out.println("socket closed : "+requestSocket.isClosed());
            //System.out.println("socket connected : "+requestSocket.isConnected());
        }


        return result;

    }
    
    public String sendMessageNolNol(String msg) {
        String result = "";
        String sendMsg = "";
        //String recMsg = "";
        ByteArrayOutputStream tRequestByteStream = null;
        //BufferedReader br = null;        
        /* end message gapura*/
        final byte cEndMessageByte = 0x00;
        /* end message RPG sssnyo*/
//        final byte cEndMessageByte = 3;
        //StringBuilder sb = new StringBuilder();

        try {

            if (isEnkrip()) {
                sendMsg = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_ENCRYPT, msg);
            } else {
                sendMsg = msg;
            }
            
            //testing only
            //sendMsg = msg;

            //System.out.println("Start Send : "+System.currentTimeMillis());
            tRequestByteStream = new ByteArrayOutputStream();
            tRequestByteStream.write(sendMsg.getBytes());
            tRequestByteStream.write(cEndMessageByte);
            requestSocket.getOutputStream().write(tRequestByteStream.toByteArray());
            //System.out.print("Outgoing :" + tRequestByteStream.toString());

            //System.out.println("Start Write : "+System.currentTimeMillis());
            byte tMessageByte = cEndMessageByte;
            //byte[] b = new byte[1024];
            //int byteRead = 0;

            //testing only
            requestSocket.setSoTimeout(getTimeout() * minute * thousand);

            //tRequestByteStream.reset();

            /*
            while((byteRead=requestSocket.getInputStream().read(b, 0, b.length))!=-1){
            tRequestByteStream.write(b, 0, byteRead);
            }
             */
            //testing only
            concatnator = clearStringBuilder(concatnator);
//            concatnator.append(msg);
            
            
            //testing only
            while ((tMessageByte = (byte) requestSocket.getInputStream().read()) != cEndMessageByte) {
                /*untuk menampilkan ingoing per char */
//                System.out.print(tMessageByte + "-" +(char) tMessageByte);
                
                //sb.append((char) tMessageByte);
                concatnator.append((char) tMessageByte);
            }

            //System.out.println("End Write : "+System.currentTimeMillis());

            //System.out.print("Incoming :" + concatnator.toString());
           
            //testing only
            if (isEnkrip()) {
                result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, concatnator.toString());
                //result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, tRequestByteStream.toString());
            } else {
                //result = sb.toString();
                result = concatnator.toString();
                //result = tRequestByteStream.toString();
            }

            requestSocket.getOutputStream().flush();
            //System.out.println("socket closed : "+requestSocket.isClosed());
            //System.out.println("socket connected : "+requestSocket.isConnected());

        } catch (Exception exc) {
            lastError = exc.getMessage();
            exc.printStackTrace();
        } finally {
            //testing only
            try {
                if (tRequestByteStream != null) {
                    tRequestByteStream.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            
            try {
                requestSocket.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            concatnator = clearStringBuilder(concatnator);
            concatnator = concatnator.append("Disconnected form ");
            concatnator = concatnator.append(host);
            concatnator = concatnator.append(" in port ");
            concatnator = concatnator.append(port);

            System.out.println(concatnator.toString());
             
            //System.out.println("socket closed : "+requestSocket.isClosed());
            //System.out.println("socket connected : "+requestSocket.isConnected());
        }


        return result;

    }
    
    public String sendMessageWithoutEndMessage2(String msg) {
        String result = "";
        String sendMsg = "";
        //String recMsg = "";
        ByteArrayOutputStream tRequestByteStream = null;
        //BufferedReader br = null;        
        /* end message gapura*/
        final byte cEndMessageByte = -0x01;


        try {

            if (isEnkrip()) {
                sendMsg = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_ENCRYPT, msg);
            } else {
                sendMsg = msg;
            }
            
            //testing only
            //sendMsg = msg;

            //System.out.println("Start Send : "+System.currentTimeMillis());
            tRequestByteStream = new ByteArrayOutputStream();
            tRequestByteStream.write(sendMsg.getBytes());
            requestSocket.getOutputStream().write(tRequestByteStream.toByteArray());
            System.out.print("Outgoing :" + tRequestByteStream.toString());

            //System.out.println("Start Write : "+System.currentTimeMillis());
            byte tMessageByte = cEndMessageByte;
            //byte[] b = new byte[1024];
            //int byteRead = 0;

            //testing only
            requestSocket.setSoTimeout(getTimeout() * minute * thousand);

            //tRequestByteStream.reset();

            /*
            while((byteRead=requestSocket.getInputStream().read(b, 0, b.length))!=-1){
            tRequestByteStream.write(b, 0, byteRead);
            }
             */
            //testing only
            concatnator = clearStringBuilder(concatnator);
//            concatnator.append(msg);
            
            
//            System.out.println(requestSocket.getInputStream().available() );
            while(true){
                tMessageByte = (byte) requestSocket.getInputStream().read();
                
                concatnator.append((char) tMessageByte);
                
                if (requestSocket.getInputStream().available() <= 0) break;
            }

            //System.out.println("End Write : "+System.currentTimeMillis());

            //result = sb.toString();
           
            //testing only
            if (isEnkrip()) {
                result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, concatnator.toString());
                //result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, tRequestByteStream.toString());
            } else {
                //result = sb.toString();
                result = concatnator.toString();
                //result = tRequestByteStream.toString();
            }

            requestSocket.getOutputStream().flush();
            //System.out.println("socket closed : "+requestSocket.isClosed());
            //System.out.println("socket connected : "+requestSocket.isConnected());

        } catch (Exception exc) {
            lastError = exc.getMessage();
            exc.printStackTrace();
        } finally {
            //testing only
            try {
                if (tRequestByteStream != null) {
                    tRequestByteStream.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            
            try {
                requestSocket.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            concatnator = clearStringBuilder(concatnator);
            concatnator = concatnator.append("Disconnected form ");
            concatnator = concatnator.append(host);
            concatnator = concatnator.append(" in port ");
            concatnator = concatnator.append(port);

            System.out.println(concatnator.toString());
             
            //System.out.println("socket closed : "+requestSocket.isClosed());
            //System.out.println("socket connected : "+requestSocket.isConnected());
        }


        return result;
    }
    
    public String sendMessageWithoutEndMessage(String msg) {
        String result = "";
        String sendMsg = "";
        //String recMsg = "";
        ByteArrayOutputStream tRequestByteStream = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        final byte cEndMessageByte = -0x01;

        try {

            if (isEnkrip()) {
                sendMsg = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_ENCRYPT, msg);
            } else {
                sendMsg = msg;
            }

            System.out.println("Start Send : " + System.currentTimeMillis());
            tRequestByteStream = new ByteArrayOutputStream();
            tRequestByteStream.write(sendMsg.getBytes());
            requestSocket.getOutputStream().write(tRequestByteStream.toByteArray());

            System.out.println("Start Write : " + System.currentTimeMillis());
            byte tMessageByte = cEndMessageByte;
            byte[] b = new byte[1024];
            int byteRead = 0;

            requestSocket.setSoTimeout(getTimeout() * minute * thousand);

            //tRequestByteStream.reset();

            /*
            while((byteRead=requestSocket.getInputStream().read(b, 0, b.length))!=-1){
            tRequestByteStream.write(b, 0, byteRead);
            }
             */

            while ((tMessageByte = (byte) requestSocket.getInputStream().read()) != cEndMessageByte) {
                sb.append((char) tMessageByte);
            }

            System.out.println("End Write : " + System.currentTimeMillis());

            //result = sb.toString();
            if (isEnkrip()) {
                result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, sb.toString());
                //result = enkriptor.getCryptoMessage(SymetricCryptoHandler.ACTION_DECRYPT, tRequestByteStream.toString());
            } else {
                result = sb.toString();
                //result = tRequestByteStream.toString();
            }

            requestSocket.getOutputStream().flush();
            //System.out.println("socket closed : "+requestSocket.isClosed());
            //System.out.println("socket connected : "+requestSocket.isConnected());

        } catch (Exception exc) {
            lastError = exc.getMessage();
            exc.printStackTrace();
        } finally {
            try {
                if (tRequestByteStream != null) {
                    tRequestByteStream.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }

            try {
                requestSocket.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }

            System.out.println("socket closed : " + requestSocket.isClosed());
            System.out.println("socket connected : " + requestSocket.isConnected());
        }


        return result;

    }

    public boolean killSocket() {
        boolean result = false;

        try {
            if (requestSocket != null) {
                if (!requestSocket.isClosed()) {
                    requestSocket.close();
                    result = true;
                    concatnator = clearStringBuilder(concatnator);
                    concatnator = concatnator.append("Disconnected form ");
                    concatnator = concatnator.append(host);
                    concatnator = concatnator.append(" in port ");
                    concatnator = concatnator.append(port);

                    System.out.println(concatnator.toString());
                }
            } else {
                result = true;
            }
        } catch (Exception exc) {
            result = false;
            exc.printStackTrace();
        }

        return result;
    }

    private StringBuilder clearStringBuilder(StringBuilder sb) {
        sb.delete(0, sb.length());
        return sb;
    }

//    public String sendMessage(String msg){
//        
//        String result = "";
//        String message = "";
//        final byte        cEndMessageByte       = -0x01;
//        
//        try {
//            //process send Message
//            //out = new ObjectOutputStream(requestSocket.getOutputStream());
//            baos = new BufferedOutputStream(requestSocket.getOutputStream());
//            osw = new OutputStreamWriter(baos, "UTF-8");
//            osw.flush();
//            
//            msg += "\n";
//            osw.write(msg);
//            osw.flush();
//            System.out.println(msg);
//
//            //process receive message
//            requestSocket.setSoTimeout(1000*10);
//            bis = new BufferedInputStream(requestSocket.getInputStream());
//            
//            /*
//            ByteArrayOutputStream bao = new ByteArrayOutputStream();
//            
//            byte[] buf = new byte[1024];
//            int len = 0;
//            
//            while (bis.read(buf) > 0){
//                //System.out.println("result stream : "+ new String(buf));
//                //result.concat(new String(buf));
//                bao.write(buf);
//            }
//            */
//            byte tMessageByte = cEndMessageByte;
//            StringBuffer sb = new StringBuffer();
//
//            while ((tMessageByte = (byte) requestSocket.getInputStream().read()) != cEndMessageByte) {
//                sb.append((char) tMessageByte);
//            }
//            result = sb.toString();
//            //result = new String(bao.toByteArray());
//            
//            //isr = new InputStreamReader(bis,"UTF-8");
//            
//            //in = new ObjectInputStream(requestSocket.getInputStream());
//            //result = (String) isr.
//            
//            System.out.println("result : "+result);    
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }finally{
//            try{
//                bis.close();
//                //isr.close();
//                osw.close();
//                baos.close();
//                requestSocket.close();
//            }catch(Exception exc){
//                exc.printStackTrace();
//            }
//        }
//
//        return result;   
//    }
    /**
     * @return the socketReady
     */
    public boolean isSocketReady() {
        return socketReady;
    }

    /**
     * @param socketReady the socketReady to set
     */
    public void setSocketReady(boolean socketReady) {
        this.socketReady = socketReady;
    }

    /**
     * @return the lastError
     */
    public String getLastError() {
        return lastError;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the enkrip
     */
    public boolean isEnkrip() {
        return enkrip;
    }

    /**
     * @param enkrip the enkrip to set
     */
    public void setEnkrip(boolean enkrip) {
        this.enkrip = enkrip;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the padding
     */
    public String getPadding() {
        return padding;
    }

    /**
     * @param padding the padding to set
     */
    public void setPadding(String padding) {
        this.padding = padding;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
