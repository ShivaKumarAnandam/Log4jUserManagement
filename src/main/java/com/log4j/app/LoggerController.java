package com.log4j.app;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {
	
	@Autowired
	LoggerServicer loggerServicer;
	
	@PostMapping
	public User postData(@RequestBody @Valid User user) {
		return loggerServicer.post(user);
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Integer id){
		return loggerServicer.getById(id);
	}
	
	@GetMapping("/getall")
	public List<User> getUsers(){
		return loggerServicer.get();
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Integer id) {
		return loggerServicer.deleteById(id);
	}
	
	@PutMapping("/put/{id}")
	public User put(@PathVariable Integer id,@RequestBody User user) {
		return loggerServicer.putById(id,user);
	}
	
	@PatchMapping("/patch/{id}")
	public User patch(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
		return loggerServicer.patchById(id,fields);
	}
}






