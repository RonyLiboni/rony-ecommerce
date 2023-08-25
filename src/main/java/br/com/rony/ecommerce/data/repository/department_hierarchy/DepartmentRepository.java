package br.com.rony.ecommerce.data.repository.department_hierarchy;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import br.com.rony.ecommerce.data.repository.BasicRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;

public interface DepartmentRepository extends BasicRepository<Department, Long>{

	List<Department> findAll();

	Department findByNameWithAllRelatedDataLoaded(String name);

	List<Department> customerSearchFilter(String productName, BigDecimal startPrice, BigDecimal endPrice,
			Collection<String> categoriesDTO, Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO);

}
