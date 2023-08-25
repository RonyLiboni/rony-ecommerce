package br.com.rony.ecommerce.adapters.domain.services.product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.rony.ecommerce.data.repository.department_hierarchy.DepartmentRepository;
import br.com.rony.ecommerce.data.repository.product.ProductRepository;
import br.com.rony.ecommerce.domain.entities.Product.Product;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;
import br.com.rony.ecommerce.domain.services.product.CustomerSearchFilterService;

@Service
public class CustomerSearchFilterServiceImpl implements CustomerSearchFilterService {
	
	private final DepartmentRepository departmentRepository;
	private final ProductRepository productRepository;
	
	public CustomerSearchFilterServiceImpl(DepartmentRepository departmentRepository,
			ProductRepository productRepository) {
		this.departmentRepository = departmentRepository;
		this.productRepository = productRepository;
	}

	@Override
	public List<Department> customerSearch(String productName, BigDecimal startPrice, BigDecimal endPrice, Collection<String> categoriesDTO,
			Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO) {
		return departmentRepository.customerSearchFilter(productName, startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO);
	}
	
	@Override
	public Collection<Product> customerSearch(String productName, String sortDirection, String sortField,
			Integer pageNumber, Integer pageSize, BigDecimal startPrice, BigDecimal endPrice,
			Collection<String> categoriesDTO, Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO) {
		return productRepository.customerSearch(productName, sortDirection, sortField, pageNumber, pageSize, startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO);
	}

	@Override
	public Long customerSearchTotalCount(String productName, BigDecimal startPrice, BigDecimal endPrice,
			Collection<String> categoriesDTO, Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO) {
		return productRepository.customerSearchTotalCount(productName, startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO);
	}

}
