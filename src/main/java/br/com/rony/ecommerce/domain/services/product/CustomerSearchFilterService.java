package br.com.rony.ecommerce.domain.services.product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;

public interface CustomerSearchFilterService {

	List<Department> customerSearch(String productName, BigDecimal startPrice, BigDecimal endPrice,
			Collection<String> categoriesDTO, Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO);

}
