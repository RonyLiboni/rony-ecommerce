package br.com.rony.ecommerce.data.repository.department_hierarchy;

import java.util.List;

import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;

public interface DepartmentRepository extends BasicRepository<Department, Long>{

	List<Department> findAll();

	Department findByIdWithAllRelatedDataLoaded(Long id);

}
