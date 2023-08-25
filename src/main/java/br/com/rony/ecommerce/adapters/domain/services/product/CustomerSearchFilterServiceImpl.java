package br.com.rony.ecommerce.adapters.domain.services.product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.rony.ecommerce.data.repository.department_hierarchy.DepartmentRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;
import br.com.rony.ecommerce.domain.services.product.CustomerSearchFilterService;

@Service
public class CustomerSearchFilterServiceImpl implements CustomerSearchFilterService {
	
	private final DepartmentRepository departmentRepository;
	
	public CustomerSearchFilterServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> customerSearch(String productName, BigDecimal startPrice, BigDecimal endPrice, Collection<String> categoriesDTO,
			Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO) {
		return departmentRepository.customerSearchFilter(productName, startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO);
	}

}
