package com.edea.tasklist.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {
	
	private Long id;

	@Email(message = "Formato do Email inválido.")
	private String email;

	@NotEmpty(message = "A Senha não pode ser vazia.")
	@Length(min = 6, max = 25, message = "A Senha conter entre 6 e 25 caracteres.")
	private String password;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
