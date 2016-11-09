package com.artsoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.error.CustomError;
import com.artsoft.model.Category;
import com.artsoft.model.ServiceModel;
import com.artsoft.service.CategoryService;
import com.artsoft.service.ServiceModelService;
import com.artsoft.service.ServiceModelServiceImpl;

@RestController
@RequestMapping("/service_control")
@EnableWebMvc
public class ServiceController {

	
	@Autowired 
	ServiceModelService serviceModelService;
	
	@Autowired
	CategoryService categoryService;
	
	
	@RequestMapping(value = "/add", headers = {"Accept=*/*" }, produces = "application/json", method = RequestMethod.POST)
	public Object add(@RequestBody ServiceModel service){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		Category category = categoryService.findById(service.getCategory().getCategoryId());
		if(category != null){
			for(ServiceModel s : category.getServices()){
				if(s.getServiceName().equals(service.getServiceName().toUpperCase())){
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("name");
					error.setErrorMessage("Service '"+service.getServiceName()+"' already exists in this category.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
				}
			}
			
			service.setCategory(category);
			int insertedServiceId = serviceModelService.insert(service);
				
			if(insertedServiceId > 0){
				ServiceModel s = serviceModelService.findById(insertedServiceId);
				if(s != null){
					response.put("insertedService", s);
				} else{
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("service object");
					error.setErrorMessage("Failed to fetch inserted service.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error					
				}
			} 
			
		} else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("category object");
			error.setErrorMessage("Failed to fetch category.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
