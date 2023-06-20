package br.com.rony.ecommerce.adapters.domain.entities.commons;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityWithNameAndLongId extends EntityWithLongId {
	
	private String name;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityWithNameAndLongId other = (EntityWithNameAndLongId) obj;
		return Objects.equals(name, other.name);
	}		
	
}
