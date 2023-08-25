package br.com.rony.ecommerce.application.mappers.product;

import java.util.List;

import br.com.rony.ecommerce.application.dto.product.CustomerSearchFilterDTO;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;

public interface CustomerSearchFilterMapper {

	CustomerSearchFilterDTO toDTO(List<Department> customerSearch);

}
