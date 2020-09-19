package com.precavido.app.dto;

import org.hibernate.validator.constraints.NotBlank;

public class PasswordDTO {
	
	private long id;
	
	private byte[] image;
	
	@NotBlank(message="Por favor, digite uma senha!")
	private String password;
	
	@NotBlank(message="Por favor, digite a confirmação da senha!")
	private String confirmPassword;
	
	@NotBlank(message="Por favor, digite a senha atual!")
	private String currentPassword;

	public PasswordDTO() {
	}

	public PasswordDTO(long id, byte[] image, String password, String confirmPassword, String currentPassword) {
		super();
		this.id = id;
		this.image = image;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.currentPassword = currentPassword;
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

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	
}
