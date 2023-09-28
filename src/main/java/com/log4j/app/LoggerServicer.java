package com.log4j.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

@Service
public class LoggerServicer {
	private static final Logger log = LogManager.getLogger(LoggerServicer.class);
	List<User> users = new ArrayList<User>(
			Arrays.asList(new User(1, "shiva" , "shiva@gmail.com"),
						  new User(2, "sai" , "sai@gmail.com")));

	public User post(@Valid User user) {
		log.info("data added successfully..."+user);
		return users.add(user)?user:null;
	}
	
	public User getById(Integer id) {	
		try {
			User user = users.stream()
				    .filter(u -> u.getId() == id)
				    .findFirst().get();
//				    .orElse(null); // This handles the case when no user is found

				log.info("data is..." + user);
				return user;
		}
		catch(NoSuchElementException e) {
			log.error("no data present..."+e);
			return null;
		}
	}
	
	public List<User> get(){
		if(!users.isEmpty()) {
			log.info("List of users is..."+users);
			return users;
		}else {
			log.error("no data present...");
			return null;
		}
	}

	public boolean deleteById(Integer id) {
		
		try {
			User user = users.stream()
				    .filter(u -> u.getId() == id)
				    .findFirst().get();

				log.info("data with id "+id+" has been deleted successfully...");
				return users.remove(user);
		}
		catch (Exception e) {
			log.warn("Exception Raised..."+e);
			return false;
		}
	}

	public User putById(Integer id, User user) {
		try {
			User u = users.stream()
				    .filter(usa -> usa.getId() == id)
				    .findFirst().get();
			log.info("Previous data before PUT..."+u);
			u.setId(user.getId());
			u.setName(user.getName());
			u.setEmail(user.getEmail());
			log.info("Present data after PUT..."+u);
			return u;		
		}
		catch (Exception e) {
			log.warn("Exception Raised..."+e);
			return null;
		}
	}

	public User patchById(Integer id, Map<String, Object> fields) {
		try {
			User u = users.stream()
				    .filter(usa -> usa.getId() == id)
				    .findFirst().get();
				 log.info("Previous data before PATCH..."+u);
			
			  fields.forEach((Key, Value)->{
				Field field = ReflectionUtils.findRequiredField(User.class, Key);
				      field.setAccessible(true);
					  ReflectionUtils.setField(field, u, Value);
				 log.info("Present data after PATCH..."+u);	
			  });
			return u;		
		}
		catch (Exception e) {
			log.warn("Exception Raised..."+e);
			return null;
		}
	}
	
	
	
	
	
	
	
	
}
