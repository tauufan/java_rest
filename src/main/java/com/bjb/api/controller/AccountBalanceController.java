package com.bjb.api.controller;

import com.bjb.api.helper.MPI;
import com.bjb.api.service.AccountBalance.AccountBalance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author L114
 */
@RestController
@Api(description="Account Balance")
public class AccountBalanceController {
    private final static Logger logger = LoggerFactory.getLogger(AccountBalanceController.class);
    
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/api/ab/eksternal")
    @ApiOperation(value = "Account Balance Extenal Number")
    ResponseEntity<Object> Ab(@RequestBody MPI mpi) {
	    	AccountBalance ab = new AccountBalance();
                String norek = mpi.getZlean();
                String res;
                try {
                	logger.info("Request AB to middleware "+mpi.toString());
                    res = ab.GetAccountBalance(norek);
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonParser jp = new JsonParser();
                    JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
                    String rc = jsonObject.get("RC").getAsString();
                    if (rc.equalsIgnoreCase("0000")) {
                        jsonObject = jsonObject.getAsJsonObject("MPO").getAsJsonObject("0");
                        jsonObject.addProperty("ResponseCode", rc);
                        String JsonString = gson.toJson(jsonObject).toString();
                        logger.info("Response AB from middleware "+res);
                        return new ResponseEntity<Object>(JsonString, HttpStatus.OK);
                    }else{
                        JsonObject jserr = new JsonObject();
                        jserr.addProperty("ResponseCode", rc);
                        jserr.add("ErrorMessage", jsonObject.get("RCMSG").getAsJsonObject());
                        String JsonString = gson.toJson(jserr).toString();
                        logger.error("Response AB from middleware "+res);
                        return new ResponseEntity<Object>(JsonString, HttpStatus.NOT_FOUND);
                    }
                } catch (Exception e) {
                    res = e.toString();
                    	logger.error("Response AB from middleware "+res);
                        return new ResponseEntity<Object>(res, HttpStatus.INTERNAL_SERVER_ERROR);
                }
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/api/ab/internal")
    @ApiOperation(value = "Account Balance Internal Number")
    ResponseEntity<Object> AbInternal(@RequestBody MPI mpi) {
	    	AccountBalance ab = new AccountBalance();
                String zlab = mpi.getZlab();
                String zlan = mpi.getZlan();
                String zlas = mpi.getZlas();
                String res;
                try {
                	logger.info("Request AB to middleware "+mpi.toString());
                    res = ab.GetAccountBalance(zlab,zlan,zlas);
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonParser jp = new JsonParser();
                    JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
                    String rc = jsonObject.get("RC").getAsString();
                    if (rc.equalsIgnoreCase("0000")) {
                        jsonObject = jsonObject.getAsJsonObject("MPO").getAsJsonObject("0");
                        jsonObject.addProperty("ResponseCode", rc);
                        String JsonString = gson.toJson(jsonObject).toString();
                        logger.info("Response AB from middleware "+res);
                        return new ResponseEntity<Object>(JsonString, HttpStatus.OK);
                    }else{
                        JsonObject jserr = new JsonObject();
                        jserr.addProperty("ResponseCode", rc);
                        jserr.add("ErrorMessage", jsonObject.get("RCMSG").getAsJsonObject());
                        String JsonString = gson.toJson(jserr).toString();
                        logger.error("Response AB from middleware "+res);
                        return new ResponseEntity<Object>(JsonString, HttpStatus.NOT_FOUND);
                    }
                } catch (Exception e) {
                    res = e.toString();
                    	logger.error("Response AB from middleware "+res);
                        return new ResponseEntity<Object>(res, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        
    }
}
