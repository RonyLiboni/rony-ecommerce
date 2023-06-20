package br.com.rony.ecommerce.domain.services.department_hierarchy;

import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;

public interface SubDepartmentService {

	Long create(SubDepartment subDepartment);

	void updateBy(Long id, SubDepartment subDepartment);

}
