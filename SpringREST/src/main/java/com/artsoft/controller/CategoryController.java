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
import com.artsoft.model.Category;
import com.artsoft.service.CategoryService;

@RestController
@RequestMapping("/category_control")
@EnableWebMvc
public class CategoryController {
	
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/add", headers = {"Accept=*/*" }, produces = "application/json", method = RequestMethod.POST)
	public Object add(@RequestBody Category category){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		if((categoryService.findByName(category.getCategoryName())) != null ){
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("name");
			error.setErrorMessage("Category '"+category.getCategoryName()+"' already exists.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		} 
		else{
			int insertedCategoryId = categoryService.insert(category);
			if(insertedCategoryId > 0){
				response.put("categoryId", insertedCategoryId);
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("category object");
				error.setErrorMessage("Error registering details.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	

	
	@RequestMapping(value = "/all", headers = {"Accept=*/*" }, produces = "application/json", method = RequestMethod.GET)
	public Object findAll(){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		List<Category> categories = categoryService.findAll();
		if( (categories != null) && (! categories.isEmpty()) ){
			response.put("categoryList", categories);
		} else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("category object");
			error.setErrorMessage("No categories found.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/update", headers = {"Accept=*/*" }, produces = "application/json", method = RequestMethod.POST)
	public Object update(@RequestBody Category category){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(!categoryService.isCategoryNameAvailable(category)){
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("name");
			error.setErrorMessage("Category '"+category.getCategoryName()+"' already exists.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		} 
		else{
			try{
				categoryService.update(category);
				
				Category c = categoryService.findById(category.getCategoryId());
				if(c != null){
					response.put("updatedCategory", c);
				} else {
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("category object");
					error.setErrorMessage("Failed to fetch updated category.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error		
				}
				
			} catch(Exception ex){
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("category object");
				error.setErrorMessage("Failed to update category.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/delete/{id}", headers = {"Accept=*/*" }, produces = "application/json", method = RequestMethod.POST)
	public Object delete(@PathVariable("id") int categoryId){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(categoryId != 0){
			Category category = categoryService.findById(categoryId);
			if(category != null){
				categoryService.delete(category);
				response.put("msg", "Category deleted.");
			} else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("category object");
				error.setErrorMessage("Failed to fetch category.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
			
		} else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("categoryId");
			error.setErrorMessage("Incorrect value for category id. '"+ categoryId + "'");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
}
