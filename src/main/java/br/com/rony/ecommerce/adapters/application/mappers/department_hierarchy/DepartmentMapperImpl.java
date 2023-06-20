package br.com.rony.ecommerce.adapters.application.mappers.department_hierarchy;

import org.springframework.stereotype.Component;

import br.com.rony.ecommerce.adapters.application.mappers.AbstractMapper;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.DepartmentImpl;
import br.com.rony.ecommerce.application.dto.department_hierarchy.DepartmentDTO;
import br.com.rony.ecommerce.application.dto.department_hierarchy.DepartmentFormDTO;
import br.com.rony.ecommerce.application.mappers.department_hierarchy.DepartmentMapper;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;

@Component
public class DepartmentMapperImpl extends AbstractMapper implements DepartmentMapper{
		
	@Override
	public Department toEntity(DepartmentFormDTO form) {
		return mapper().map(form, DepartmentImpl.class);
	}

	@Override
	public DepartmentDTO toDepartmentDTO(Department department) {
		return mapper().map(department, DepartmentDTO.class);
	}

}
