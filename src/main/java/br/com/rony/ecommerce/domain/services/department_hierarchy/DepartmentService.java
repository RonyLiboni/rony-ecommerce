package br.com.rony.ecommerce.domain.services.department_hierarchy;

import java.util.List;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;

public interface DepartmentService {

	Long create(Department department);

	Department findBy(Long id);

	List<Department> findAll();

	void updateBy(Long id, Department department);

	Department findByNameWithAllRelatedDataLoaded(String name);

}
