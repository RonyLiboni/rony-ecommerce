package br.com.rony.ecommerce.application.mappers.department_hierarchy;

import br.com.rony.ecommerce.application.dto.department_hierarchy.DepartmentDTO;
import br.com.rony.ecommerce.application.dto.department_hierarchy.DepartmentFormDTO;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;

public interface DepartmentMapper {

	Department toEntity(DepartmentFormDTO form);

	DepartmentDTO toDepartmentDTO(Department department);

}
