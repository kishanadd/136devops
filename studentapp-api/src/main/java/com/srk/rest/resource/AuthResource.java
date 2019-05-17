package com.srk.rest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srk.exception.CustomWebException;
import com.srk.rest.response.ErrorResponse;


/**
 * REST controller for managing Student Login.
 */
@RestController
@RequestMapping("/unauthorized")
public class AuthResource {

	private final Logger log = LoggerFactory.getLogger(AuthResource.class);
        
    
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ErrorResponse> exceptionHandler() throws CustomWebException{
    	ErrorResponse error;
		try {
			error = new ErrorResponse();
			error.setErrorCode("ERR_2001");
			error.setHttpStatus(HttpStatus.UNAUTHORIZED.value());
			error.setErrorMessage("Unauthorized");
		} catch (Exception e) {
			throw new CustomWebException(HttpStatus.BAD_REQUEST.value(), "Unauthorized");
		}
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
	}
}
