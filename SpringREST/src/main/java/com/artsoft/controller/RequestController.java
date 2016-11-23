package com.artsoft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.error.CustomError;
import com.artsoft.model.AppUser;
import com.artsoft.model.MailModel;
import com.artsoft.model.Request;
import com.artsoft.service.AppUserService;
import com.artsoft.service.MailService;
import com.artsoft.service.RequestService;

@RestController
@RequestMapping("/request_control")
@EnableWebMvc
public class RequestController {
	
	
	@Autowired
	RequestService requestService;
	
	@Autowired
	AppUserService appUserService;
	
	@Autowired
	MailService mailService;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER') or hasRole('ROLE_PROVIDER')")
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
	
	
	
	
	
	@PreAuthorize("hasRole('ROLE_PROVIDER')")
	@RequestMapping(value = "/update/status/for/request",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object updateRequestStatus(@RequestBody Request request){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(request != null){
			try{
				requestService.update(request);
				response.put("msg", "Status updated.");
				
				Request r = requestService.findById(request.getRequestId());
				if(r != null){
					AppUser user = appUserService.findById(request.getAppuser().getAppUserId());
					if(user != null){
						// send notification mail
						MailModel mail = new MailModel();
						mail.setFrom("berar1994@gmail.com");
						mail.setTo(user.getEmail());
						mail.setSubject("Request #"+r.getRequestId()+" -- Updated status");
						mail.setContent("Hello " + user.getFirstName() + " " + user.getLastName() + ",\n" 
									  + "This is a reminder regarding your request status\n "
								      + "Request #" + r.getRequestId() + "\n"
								      + "request date : " + r.getRequestDate() + "\n"
								      + "desired date : " + r.getDesiredDate() + "\n"
								      + "desired hour : " + r.getDesiredHour() + "\n"
								      + "total cost : "   + r.getTotalCost()   + "\n"
								      + "STATUS : " + r.getRequestState().getDescription());
						
						mailService.sendEmail(mail);
						
					}
					else{
						CustomError error = new CustomError();
						error.setHasError(true);
						error.setErrorOnField("appuser object");
						error.setErrorMessage("Failed to fetch requester user.");
						response.put("error", error);
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
					}
					
				}
				else{
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("request object");
					error.setErrorMessage("Failed to fetch updated request.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
				}
			
				
				
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
	
	
	
	
	@RequestMapping(value = "/add",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object addRequest(@RequestBody Request request){
		
		Map<String,Object> response = new HashMap<String, Object>();
		int insertedRequestId = 0;
		
		if(request != null){
			insertedRequestId = requestService.insert(request);
			
			if(insertedRequestId > 0){
				response.put("insertedRequestId", insertedRequestId);
			}else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("request object");
				error.setErrorMessage("Failed to register details.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		} else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("request object");
			error.setErrorMessage("Incorrect value provided.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value = "/delete/{id}",headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.POST)
	public Object deleteRequest(@PathVariable("id") int requestId){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		if(requestId != 0){
			Request request = requestService.findById(requestId);
			if(request != null){
				try{
					requestService.delete(request);
					response.put("msg", "Request deleted.");
				}catch(Exception e){
					CustomError error = new CustomError();
					error.setHasError(true);
					error.setErrorOnField("request object");
					error.setErrorMessage("Failed to delete request.");
					response.put("error", error);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
				}
			}
			else{
				CustomError error = new CustomError();
				error.setHasError(true);
				error.setErrorOnField("request object");
				error.setErrorMessage("Failed to fetch request.");
				response.put("error", error);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
			}
		}else{
			CustomError error = new CustomError();
			error.setHasError(true);
			error.setErrorOnField("requestId");
			error.setErrorMessage("Incorrect value provided.");
			response.put("error", error);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	// return with error
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
