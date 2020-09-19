package com.precavido.app.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class ProfileDTO {
	
	private long id;
	
	private byte[] image;
	
	@NotBlank(message="Por favor, digite seu nome completo!")
	private String name;
	
	@NotBlank(message="Por favor, digite seu sobrenome!")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message="Por favor, digite de seu nascimento!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	
	public ProfileDTO() {}
	
	public ProfileDTO(long id, byte[] image, String name, String lastName, Date birthday) {
		this.id = id;
		this.image = image;
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

}
