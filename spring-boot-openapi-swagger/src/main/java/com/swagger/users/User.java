package com.swagger.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq")
	private Long id;

	@Column(nullable = false)
	@NotEmpty()
	private String name;

	@Column(nullable = false, unique = true)
	@NotEmpty
	@Email(message = "Invalid email")
	private String email;

	@Column(nullable = false)
	@NotEmpty
	@Size(min = 4)
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleEnum role;

}
