package br.com.rony.ecommerce.application.dto.department_hierarchy;

import br.com.rony.ecommerce.application.dto.commons.NameDTO;

public class SubDepartmentFormDTO extends NameDTO{
	
	private DepartmentIdDTO department;

	public DepartmentIdDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentIdDTO department) {
		this.department = department;
	}

}
