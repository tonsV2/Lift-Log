package dk.fitfit.liftlog.domain;

import org.springframework.hateoas.Identifiable;

public interface FirstClassDomainObject extends Identifiable<Long> {
	@Override
	Long getId();
}
