package br.com.rony.ecommerce.adapters.application.mappers.product;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.rony.ecommerce.application.dto.commons.PageResource;
import br.com.rony.ecommerce.application.dto.product.CustomerSearchFilterDTO;
import br.com.rony.ecommerce.application.dto.product.ProductDTO;
import br.com.rony.ecommerce.application.mappers.product.CustomerSearchFilterMapper;
import br.com.rony.ecommerce.application.mappers.product.ProductMapper;
import br.com.rony.ecommerce.domain.entities.Product.Product;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;

@Component
public class CustomerSearchFilterMapperImpl implements CustomerSearchFilterMapper {
	
	private final ProductMapper productMapper;
	
	public CustomerSearchFilterMapperImpl(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	public CustomerSearchFilterDTO toFilterDTO(List<Department> customerSearch) {
		var customerSearchFilterDTO = new CustomerSearchFilterDTO();
		customerSearch.forEach(cs -> {
			customerSearchFilterDTO.getDepartments().add(cs.getName());
			addSubDepartments(cs.getSubDepartments(), customerSearchFilterDTO);
		});
		return customerSearchFilterDTO;
	}

	private void addSubDepartments(Set<SubDepartment> subDepartments, CustomerSearchFilterDTO customerSearchFilterDTO) {
		subDepartments.forEach(sd->{
			customerSearchFilterDTO.getSubDepartments().add(sd.getName());
			addCategories(sd.getCategories(), customerSearchFilterDTO);
		});
	}

	private void addCategories(Set<Category> categories, CustomerSearchFilterDTO customerSearchFilterDTO) {
		categories.forEach(c-> customerSearchFilterDTO.getCategories().add(c.getName()));
	}

	@Override
	public PageResource<ProductDTO> toPageResource(Collection<Product> products, Long totalCount) {
		return productMapper.toPageResource(products, totalCount);
	}

}
