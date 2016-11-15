package com.artsoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.error.CustomError;
import com.artsoft.model.ProviderCategoryList;
import com.artsoft.service.ProviderCategoryListService;

@RestController
@RequestMapping("/provider_category_control")
@EnableWebMvc
public class ProviderCategoryListController {

	
	@Autowired
	ProviderCategoryListService providerCategoryListService;
	
	
	@RequestMapping(value = "/add/service/list",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object addServiceList(@RequestBody ProviderCategoryList providerCategoryList){
		
		Map<String,Object> response = new HashMap<String, Object>();
		int insertedListId = 0;
		
		if(!providerCategoryListService.isListNameAvailable(providerCategoryList)){
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("list name");
			error.setErrorMessage("List '" + providerCategoryList.getListName() + "' already exists.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		else{
			insertedListId = providerCategoryListService.insert(providerCategoryList);
			if(insertedListId > 0){
				response.put("insertedListId", insertedListId);
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("provider category object");
				error.setErrorMessage("Error registering details.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value = "/add/service/to/list",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object addServiceToList(){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value = "/list/{id}",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.GET)
	public Object getListById(@PathVariable("id") int listId){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(listId != 0){
			ProviderCategoryList providerCategoryList = providerCategoryListService.findByProviderCategoryListId(listId);
			if(providerCategoryList != null){
				response.put("providerCategoryList", providerCategoryList);
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("provider category list object");
				error.setErrorMessage("Failed to fetch provider category list.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}
		else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("listId");
			error.setErrorMessage("Incorrect value for list id. '"+ listId + "'");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
}
