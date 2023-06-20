package br.com.rony.ecommerce.application.dto.commons;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public abstract class NameDTO {
	
	@NotBlank
	@Length(min = 2, max = 50)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
