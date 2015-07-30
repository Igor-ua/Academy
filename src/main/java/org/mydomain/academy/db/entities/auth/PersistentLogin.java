package org.mydomain.academy.db.entities.auth;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {

	@Id
	@Column(nullable = false, length = 64)
	private String series;

	@Column(nullable = false, length = 64)
	private String username;

	@Column(nullable = false, length = 64)
	private String token;

	private Date last_used;

	@PrePersist
	protected void onCreate() {
		last_used = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		last_used = new Date();
	}

}
