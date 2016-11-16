package com.artsoft.controller;

import java.util.HashMap;
import java.util.List;
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
import com.artsoft.model.AppUser;
import com.artsoft.model.ProviderCategoryList;
import com.artsoft.model.ProviderService;
import com.artsoft.service.AppUserService;
import com.artsoft.service.ProviderCategoryListService;
import com.artsoft.service.ProviderServiceService;

@RestController
@RequestMapping("/provider_category_control")
@EnableWebMvc
public class ProviderCategoryListController {

	
	@Autowired
	ProviderCategoryListService providerCategoryListService;
	
	@Autowired
	ProviderServiceService providerServiceImpl;
	
	@Autowired
	AppUserService appUserService;
	
	
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
	public Object addServiceToList(@RequestBody ProviderService providerService){
		
		Map<String,Object> response = new HashMap<String, Object>();
		int insertedProviderServiceId = 0;
		
		if(providerServiceImpl.isProviderServiceAsserted(providerService)){
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("provider service object");
			error.setErrorMessage("Service already in the list.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		else{
			insertedProviderServiceId = providerServiceImpl.insert(providerService);
			if(insertedProviderServiceId > 0){
				response.put("insertedProviderServiceId", insertedProviderServiceId);
			}else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("provider service object");
				error.setErrorMessage("Error registering details.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}
		
		
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
	
	
	
	
	
	@RequestMapping(value = "/list/{providerId}/all",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.GET)
	public Object getAll(@PathVariable("providerId") int providerId){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		AppUser provider = appUserService.findById(providerId);
		if(provider != null){
			ProviderCategoryList providerCategoryList = new ProviderCategoryList();
			providerCategoryList.setProvider(provider);
			
			List<ProviderCategoryList> providerCategoryLists = providerCategoryListService.findAll();
			if( (providerCategoryLists != null) && (!providerCategoryLists.isEmpty()) ){
				response.put("providerCategoryLists", providerCategoryLists);
			}else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("provider category list");
				error.setErrorMessage("Failed to fetch provider's lists.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("provider object");
			error.setErrorMessage("Failed to fetch provider.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/delete/list/{listId}",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object deleteList(@PathVariable("listId") int listId){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(listId > 0){
			ProviderCategoryList providerCategoryList = providerCategoryListService.findByProviderCategoryListId(listId);
			if(providerCategoryList != null){
				try{
					providerCategoryListService.delete(providerCategoryList);
					response.put("msg", "List deleted.");
				}catch(Exception ex){
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("provider category object");
					error.setErrorMessage("Failed to delete list.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
				}
			}else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("provider category object");
				error.setErrorMessage("Failed to fetch provider category list.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("listId");
			error.setErrorMessage("Incorrect value for list id. '" + listId + "'.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	@RequestMapping(value = "/delete/service/{serviceId}/from/list/{listId}",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object deleteServiceFromList(@PathVariable("serviceId") int serviceId, @PathVariable("listId") int listId){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(serviceId > 0 && listId > 0){
			ProviderService providerService = providerServiceImpl.findById(serviceId);
			if(providerService != null){
				ProviderCategoryList providerCategoryList = new ProviderCategoryList();
				providerCategoryList.setProviderCategoryListId(listId);
				
				providerService.setProviderCategoryList(providerCategoryList);
				
				try{
					providerServiceImpl.delete(providerService);
					response.put("msg", "Service deleted.");
				}catch(Exception ex){
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("provider service object");
					error.setErrorMessage("Failed to delete service.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error	
				}
				
			}else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("provider service object");
				error.setErrorMessage("Failed to fetch service.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error		
			}
		}else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("serviceId && listId");
			error.setErrorMessage("Incorrect value for list id: '" + listId + "' and service id : '" + serviceId + "'.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value = "/update/service",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object updateServiceDetails(@RequestBody ProviderService providerService){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(providerService != null){
			try{
				providerServiceImpl.update(providerService);
				response.put("msg", "Service details updated.");
			}catch(Exception ex){
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("provider service object");
				error.setErrorMessage("Failed to update service details.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error	
			}
		}else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("provider service object");
			error.setErrorMessage("Invalid value for service object.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error	
		}
		
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
