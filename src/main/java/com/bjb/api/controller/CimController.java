package com.bjb.api.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.api.exeption.error.RecordNotFoundException;
import com.bjb.api.helper.HelperClass;
import com.bjb.api.model.Cim;
import com.bjb.api.repository.CimRepository;


@RestController
public class CimController {
	
	private final static Logger logger = LoggerFactory.getLogger(CimController.class);
	
	@Autowired
    private CimRepository repository;
	
	// Find
    @GetMapping("/api/cim")
    public ResponseEntity<Map<String, Object>> getAllCims(
    		HttpServletRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
          ) {
        logger.info("Api "+request.getMethod()+" CIM "+request.getLocalAddr());
        
        try {
          List<Cim> cim = new ArrayList<Cim>();
          Pageable paging = PageRequest.of(page, size);
          
          Page<Cim> pageTuts = null;
          pageTuts = repository.findAll(paging);
          cim = pageTuts.getContent();
          
//          System.out.println(cim.size());
          Map<String, Object> response = new HashMap<>();
          response.put("cim", cim);
          response.put("currentPage", pageTuts.getNumber());
          response.put("totalItems", pageTuts.getTotalElements());
          response.put("totalPages", pageTuts.getTotalPages());
          return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }
	
//    @GetMapping("/api/cim/{cif}")
//    List<Cim> findbyCif(@PathVariable String cif) {
//    	List<Cim> cim = repository.findByCif_(cif);
//        if (cim == null) {
//        	throw new BookNotFoundException(cif);
//		}
//    	return cim;
//    }
    
    @GetMapping("/api/cim/{cif}")
    List<Cim> findbyCif(HttpServletRequest request, @PathVariable String cif) {
    	
    	    List<Cim> cim = repository.findByCif_(cif);
	    	if (cim.isEmpty()) {
	        	throw new RecordNotFoundException(cif);
	    	}
	    	int i = 0;
	    	HelperClass hlp = new HelperClass();
	    	while (i < cim.size()) {
	    		cim.get(i).setTanggal_lahir_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_lahir()));
	        	cim.get(i).setTanggal_berakhir_kartu_id_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_berakhir_kartu_id()));
	        	cim.get(i).setTanggal_buka_nasabah_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_buka_nasabah()));
	        cim.get(i).setTanggal_berakhir_kitas_kitap_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_berakhir_kitas_kitap()));
	        	cim.get(i).setTanggal_lahir_pasangan_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_lahir_pasangan()));
	        	i++;
	        }
	    	return cim;
    }
    
    //return 201 instead of 200
//    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/api/cim")
    ResponseEntity<Object> newCim(@Valid @RequestBody Cim newCim, Error err) {
    		newCim.setCreateby("API");
    		Timestamp now = Timestamp.from(Instant.now());
    		newCim.setCreatedate(now);
    		Cim resp = repository.save(newCim);
        return new ResponseEntity<Object>(resp, HttpStatus.CREATED);
    }
    
    
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
    
    
    
}
