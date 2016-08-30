package com.artsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.artsoft.model.AppUser;
import com.artsoft.service.AppUserService;




@RestController
@RequestMapping("/user_controller")
@EnableWebMvc
public class UserController {
	
	@Autowired
	AppUserService appUserService;
	
	
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public ResponseEntity<List<AppUser>> listAllUsers() {
//        List<AppUser> users = userService.getAllUsers();
//        if(users.isEmpty()){
//            return new ResponseEntity<List<AppUser>>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<List<AppUser>>(users, HttpStatus.OK);
//    }
    
    
    @RequestMapping(value = "/user", headers={"Accept=*/*"}, produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<AppUser> getUser(@RequestParam(value = "email") String email) {
        System.out.println("Fetching User with id " + email);
        AppUser user = appUserService.findByEmail(email);
        if (user == null) {
            System.out.println("User with id " + email + " not found");
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
        System.out.println("HERE");
        return new ResponseEntity<AppUser>(user, HttpStatus.OK);
    }
	
}
