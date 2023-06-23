package br.com.rony.ecommerce.application.dto.department_hierarchy;

import br.com.rony.ecommerce.application.dto.commons.NameDTO;

public class CategoryFormDTO extends NameDTO{
	
	private SubDepartmentIdDTO subDepartment;

	public SubDepartmentIdDTO getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(SubDepartmentIdDTO subDepartment) {
		this.subDepartment = subDepartment;
	}

}
