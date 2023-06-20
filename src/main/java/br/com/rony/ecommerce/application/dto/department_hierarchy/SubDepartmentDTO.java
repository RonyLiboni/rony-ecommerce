package br.com.rony.ecommerce.application.dto.department_hierarchy;

import java.util.List;

import br.com.rony.ecommerce.application.dto.commons.NameDTO;

public class SubDepartmentDTO extends NameDTO {
	
	private List<CategoryDTO> categories;

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

}
