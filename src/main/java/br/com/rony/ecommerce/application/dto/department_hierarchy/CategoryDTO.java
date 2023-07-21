package br.com.rony.ecommerce.application.dto.department_hierarchy;

import br.com.rony.ecommerce.application.dto.commons.NameDTO;

public class CategoryDTO extends NameDTO {
	
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
