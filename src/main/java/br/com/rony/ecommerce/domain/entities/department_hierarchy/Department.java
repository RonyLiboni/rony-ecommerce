package br.com.rony.ecommerce.domain.entities.department_hierarchy;

import java.util.Set;

public interface Department {

	Long getId();

	String getName();

	void setName(String name);

	Set<SubDepartment> getSubDepartments();

}
