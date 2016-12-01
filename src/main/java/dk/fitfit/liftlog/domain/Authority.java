package dk.fitfit.liftlog.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authority implements DomainObject, GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String authority;

	@Override
	public Long getId() {
		return null;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
