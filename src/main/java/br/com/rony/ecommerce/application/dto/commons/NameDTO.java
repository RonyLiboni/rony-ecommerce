package br.com.rony.ecommerce.application.dto.commons;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public abstract class NameDTO {
	
	@NotBlank
	@Size(min = 2, max = 150)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
