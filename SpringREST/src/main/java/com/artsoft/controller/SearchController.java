package com.artsoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artsoft.error.CustomError;

@RestController
@RequestMapping("/search_control")
public class SearchController {
	
	
	@RequestMapping(value = "/all/providers/for/service/{id}",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.GET)
	public Object searchProvidersByCountyAndCity(@RequestParam("id") int serviceId){
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(serviceId != 0){
			
		}
		else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("serviceId");
			error.setErrorMessage("Invalid value provided.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	

}
