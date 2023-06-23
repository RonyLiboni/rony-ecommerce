package br.com.rony.ecommerce.domain.entities.department_hierarchy;

public interface SubDepartment {

	String getName();

	void setName(String name);

	Long getId();

	Department getDepartment();

	void setDepartment(Department department);

}
