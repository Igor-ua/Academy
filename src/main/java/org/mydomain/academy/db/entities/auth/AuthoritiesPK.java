package org.mydomain.academy.db.entities.auth;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AuthoritiesPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "username")
	private Users users;

	@Column(nullable = false, length = 50)
	private String authority;

	protected AuthoritiesPK() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AuthoritiesPK that = (AuthoritiesPK) o;

		if (users != null ? !users.equals(that.users) : that.users != null) return false;
		return !(authority != null ? !authority.equals(that.authority) : that.authority != null);

	}

	@Override
	public int hashCode() {
		int result = users != null ? users.hashCode() : 0;
		result = 31 * result + (authority != null ? authority.hashCode() : 0);
		return result;
	}
}
