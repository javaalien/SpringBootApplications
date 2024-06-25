package com.validation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Table(name = "users")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	@NotEmpty
	@Size(min = 2, message = "user name should have at least 2 characters")
	private String name;

	@Email
	@NotEmpty
	private String email;

	@Size(min = 8, message = "password should have at least 8 characters")
	@NotEmpty
	private String password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id, @NotEmpty @Size(min = 2, message = "user name should have at least 2 characters") String name,
			@Email @NotEmpty String email,
			@Size(min = 8, message = "password should have at least 8 characters") @NotEmpty String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
