package com.bjb.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.api.model2.Aim;
import com.bjb.api.repository2.AimRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="Customer Information & Maintenance", tags = "AIM")
public class AimController {
	private final static Logger logger = LoggerFactory.getLogger(AimController.class);
	@Autowired
	private AimRepository repo;
	
	// Find
    @GetMapping("/api/aim")
    @ApiOperation(value = "List Aim")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> getAllAim(
    		HttpServletRequest request,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    		){
    	logger.info("Api "+request.getMethod()+" Aim "+request.getRemoteAddr());
    	try {
			List<Aim> aim = new ArrayList<Aim>();
			Pageable paging = PageRequest.of(page, size);
	        Page<Aim> pageTuts = null;
	        pageTuts = repo.findAll(paging);
	        aim = pageTuts.getContent();
	        Map<String, Object> response = new HashMap<>();
	        response.put("aim",aim);
	        response.put("currentPage", pageTuts.getNumber());
	        response.put("totalItems", pageTuts.getTotalElements());
	        response.put("totalPages", pageTuts.getTotalPages());
	        ObjectWriter ow = new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writer();
			logger.info("Api "+request.getMethod()+" AIM Response "+ow.writeValueAsString(response));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Api "+request.getMethod()+" AIM Response "+e.toString());
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}}
}
