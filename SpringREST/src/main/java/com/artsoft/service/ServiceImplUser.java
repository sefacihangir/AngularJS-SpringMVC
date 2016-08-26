package com.artsoft.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.model.User;

@Service("userService")
@Transactional
public class ServiceImplUser implements ServiceUser{

	
	static List<User> users = new ArrayList();
	static{
		createTestList();
	}
	
	@Override
	public List<User> getAllUsers() {
		return users;
	}
	
	
	private static void createTestList(){
		
		for(int i = 0; i < 5; i++){
			User u = new User();
			u.setUserId(i + 1);
			u.setFirstName("FirstName"+(i+1));
			u.setLastName("LastName"+(i+1));
			u.setAge(i+22);
			
			users.add(u);
		}
	}


	@Override
	public User getUserById(long userId) {
		for(User user : users){
			if(user.getUserId() == userId){
				return user;
			}
		}
		return null;
	}
	
	
}
