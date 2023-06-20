package br.com.rony.ecommerce.application.mappers.department_hierarchy;

import br.com.rony.ecommerce.application.dto.department_hierarchy.SubDepartmentFormDTO;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;

public interface SubDepartmentMapper {

	SubDepartment toEntity(SubDepartmentFormDTO form);

}
