package com.precavido.app.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class UserDTO {
	
	@NotBlank(message="Por favor, digite seu nome completo!")
	private String name;
	
	@NotBlank(message="Por favor, digite seu sobrenome!")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message="Por favor, digite de seu nascimento!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	
	@NotBlank(message="Por favor, email obrigatório!")
	@Email(message = "Por favor, digite um email valido!")
	private String email;
	
	@NotBlank(message="Por favor, digite um nome de usuário!")
	private String username;
	
	@NotBlank(message="Por favor, digite uma senha")
	private String password;
	
	@NotBlank(message="Por favor, digite a confiração da senha!")
	private String confirmPassword;
	
	public UserDTO() {}
	
	
	
	public UserDTO(String name, String lastName, Date birthday, String email, String username,
			String password, String confirmPassword) {
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
		this.email = email;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getBirthday() {
		return birthday;
	}



	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getConfirmPassword() {
		return confirmPassword;
	}



	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}



	
	
	
	
}
