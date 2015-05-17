package org.mydomain.academy.db.entities.auth;

import org.springframework.stereotype.Component;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component

@Entity
@Table(name = "authorities")

public class Authorities {

	@EmbeddedId
	private AuthoritiesPK id;

	public Authorities() {
	}
}
