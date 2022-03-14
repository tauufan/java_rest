package com.bjb.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.api.exeption.error.RecordNotFoundException;
import com.bjb.api.model.User;
import com.bjb.api.repository.UsersRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="Client User Information & Maintenance", tags = "User")
public class UserController {
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UsersRepository repository;
	
	@Autowired PasswordEncoder encypt;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/api/client/{username}")
    @ApiOperation(value = "List Client User")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	List<User> findbyUsername(HttpServletRequest request, @PathVariable String username) throws JsonProcessingException {
		logger.info("Api "+request.getMethod()+" User "+request.getRemoteAddr());
		
	    List<User> user = repository.findByUsername(username);
	    ObjectWriter ow = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writer();
	    if (user.isEmpty()) {
	    	logger.info("Api "+request.getMethod()+" User Response : NULL User");
        	throw new RecordNotFoundException(username);
    	}else {
    		user.get(0).setPassword("");
    	    logger.info("Api "+request.getMethod()+" User Response "+ow.writeValueAsString(user));
        	return user;
    	}
	    
	}
	
	@PutMapping("/api/client/{username}")
	@ApiOperation(value = "Save Or Update Client User By Username")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	User saveOrUpdateUser(HttpServletRequest request, @RequestBody User newUser, @PathVariable String username) throws JsonProcessingException {
		logger.info("Api "+request.getMethod()+" User "+request.getRemoteAddr());
	    User getUser = repository.findByUsername_(username);
		String pswd = newUser.getPassword();
		String pswdEncrypt = encypt.encode(newUser.getPassword());
		ObjectWriter ow = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writer();
		if (getUser == null) {
			newUser.setUsername(username);
			newUser.setPassword(pswdEncrypt);
			repository.save(newUser);
			newUser.setPassword(pswd);
			logger.info("Api "+request.getMethod()+" User Response :"+ow.writeValueAsString(newUser));
			return newUser;
		}else {
			Long id = getUser.getId();
			return repository.findById(id)
					.map(x -> {
						x.setUsername(username);
						x.setRole(newUser.getRole());
						x.setPassword(pswdEncrypt);
						x.setEnabled(newUser.isEnabled());
						repository.save(x);
						newUser.setId(id);
						newUser.setUsername(username);
						newUser.setPassword(pswd);
						try {
							logger.info("Api "+request.getMethod()+" User Response :"+ow.writeValueAsString(newUser));
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
						return newUser;
					})
					.orElseGet(() -> {
						try {
							logger.info("Api "+request.getMethod()+" User Response :"+ow.writeValueAsString(newUser));
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
	                    return repository.save(newUser);
	                });
		}
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
	
	
}
