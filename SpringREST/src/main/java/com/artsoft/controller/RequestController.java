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
import com.artsoft.model.Request;
import com.artsoft.service.RequestService;

@RestController
@RequestMapping("request_control")
@EnableWebMvc
public class RequestController {
	
	
	@Autowired
	RequestService requestService;
	
	
	@RequestMapping(value = "/all/requests/for/{role}/{id}",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.GET)
	public Object getAllRequestsFor(@PathVariable("role") String role ,@PathVariable("id") int id){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(role != null && !role.trim().equals("")){
			if(id > 0){
				List<Request> requests = requestService.findAllFor(id, role);
				if(requests != null && !requests.isEmpty()){
					response.put("requests", requests);
				}else{
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("request object");
					error.setErrorMessage("No requests found.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
				}
			}
			else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("id");
				error.setErrorMessage("Invalid id provided.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}
		else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("role");
			error.setErrorMessage("Invalid role provided.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	@RequestMapping(value = "/update/status/for/request",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object updateRequestStatus(@RequestBody Request request){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(request != null){
			try{
				requestService.update(request);
				response.put("msg", "Status updated.");
			}catch(Exception ex){
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("request object");
				error.setErrorMessage("Failed to update request status.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("request object");
			error.setErrorMessage("Incorrect value provided.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
