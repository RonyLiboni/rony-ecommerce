package br.com.rony.ecommerce.adapters.application.mappers.product;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.rony.ecommerce.application.dto.product.CustomerSearchFilterDTO;
import br.com.rony.ecommerce.application.mappers.product.CustomerSearchFilterMapper;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;

@Component
public class CustomerSearchFilterMapperImpl implements CustomerSearchFilterMapper {

	@Override
	public CustomerSearchFilterDTO toDTO(List<Department> customerSearch) {
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

}
