package com.log4j.app;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private Integer id;
	
	@Column
	@NotBlank(message = "Name shouldn't be null !")
	private String name;
	
	@Column
	@Email(message = "Email Shouldn,t be null and @ symbol is mandatory !")
	private String email;
	
}
