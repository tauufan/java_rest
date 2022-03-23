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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.api.exeption.error.RecordNotFoundException;
import com.bjb.api.model2.CimDwh;
import com.bjb.api.repository2.CimDwhRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="Customer Information & Maintenance", tags = "CIM DWH")
public class CimDwhController {
	private final static Logger logger = LoggerFactory.getLogger(CimDwhController.class);
	
	@Autowired
	private CimDwhRepository repository;
	
	@GetMapping("/api/cim_dwh")
    @ApiOperation(value = "List CIM DWH")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Map<String, Object>> getAllCimDwh(
    		HttpServletRequest request,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    		){
		logger.info("Api "+request.getMethod()+" CIM "+request.getRemoteAddr());
		try {
			List<CimDwh> cim = new ArrayList<CimDwh>();
			Pageable paging = PageRequest.of(page, size);
	        Page<CimDwh> pageTuts = null;
	        pageTuts = repository.findAll(paging);
	        cim = pageTuts.getContent();
	        Map<String, Object> response = new HashMap<>();
	        response.put("cim",cim);
	        response.put("currentPage", pageTuts.getNumber());
	        response.put("totalItems", pageTuts.getTotalElements());
	        response.put("totalPages", pageTuts.getTotalPages());
	        ObjectWriter ow = new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writer();
			logger.info("Api "+request.getMethod()+" Cim Response "+ow.writeValueAsString(response));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Api "+request.getMethod()+" Cim Response "+e.toString());
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/api/cim_dwh/{nama_lengkap}")
    @ApiOperation(value = "Search CIM By nama lengkap")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    List<CimDwh> findbyNama_lengkap(HttpServletRequest request, @PathVariable String nama_lengkap) {
    	
    	    List<CimDwh> cim = repository.findByBg0006(nama_lengkap);
	    	if (cim.isEmpty()) {
	        	throw new RecordNotFoundException(nama_lengkap);
	    	}
	    	
	    	return cim;
    }
	
}
