package org.mydomain.academy.db.entities.auth;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component

@Entity
@Table(name = "users")
public class Users {

	@Id
	@Column(nullable = false, length = 50)
	private String username;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false)
	private Boolean enabled;

	public Users() {
	}
}
