package br.com.rony.ecommerce.application.dto.department_hierarchy;

import java.util.List;

import br.com.rony.ecommerce.application.dto.commons.NameDTO;

public class DepartmentDTO extends NameDTO{
	
	private List<SubDepartmentDTO> subDepartments;
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SubDepartmentDTO> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(List<SubDepartmentDTO> subDepartments) {
		this.subDepartments = subDepartments;
	} 
	
}
