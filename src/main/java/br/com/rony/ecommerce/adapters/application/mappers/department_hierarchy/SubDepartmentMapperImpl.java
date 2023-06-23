package br.com.rony.ecommerce.adapters.application.mappers.department_hierarchy;

import org.springframework.stereotype.Component;

import br.com.rony.ecommerce.adapters.application.mappers.AbstractMapper;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.DepartmentImpl;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.SubDepartmentImpl;
import br.com.rony.ecommerce.application.dto.department_hierarchy.SubDepartmentFormDTO;
import br.com.rony.ecommerce.application.mappers.department_hierarchy.SubDepartmentMapper;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;

@Component
public class SubDepartmentMapperImpl extends AbstractMapper implements SubDepartmentMapper{
		
	@Override
	public SubDepartment toEntity(SubDepartmentFormDTO form) {		
		var subDepartment = buildSubDepartment();
		mapper().map(form, subDepartment);
		subDepartment.setDepartment(mapper().map(form.getDepartment(), DepartmentImpl.class));
		return subDepartment;
	}

	private SubDepartmentImpl buildSubDepartment() {
		var subDepartment = new SubDepartmentImpl();
		subDepartment.setDepartment(new DepartmentImpl());
		return subDepartment;
	}	

}
