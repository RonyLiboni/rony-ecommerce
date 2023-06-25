package br.com.rony.ecommerce.application.dto.commons;

import jakarta.validation.constraints.NotNull;

public abstract class IdDTO {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
