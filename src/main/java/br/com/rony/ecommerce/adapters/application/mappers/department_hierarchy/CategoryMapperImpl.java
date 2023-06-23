package br.com.rony.ecommerce.adapters.application.mappers.department_hierarchy;

import org.springframework.stereotype.Component;

import br.com.rony.ecommerce.adapters.application.mappers.AbstractMapper;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.CategoryImpl;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.SubDepartmentImpl;
import br.com.rony.ecommerce.application.dto.department_hierarchy.CategoryFormDTO;
import br.com.rony.ecommerce.application.mappers.department_hierarchy.CategoryMapper;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;

@Component
public class CategoryMapperImpl extends AbstractMapper implements CategoryMapper{
		
	@Override
	public Category toEntity(CategoryFormDTO form) {		
		var category = buildCategory();
		mapper().map(form, category);
		category.setSubDepartment(mapper().map(form.getSubDepartment(), SubDepartmentImpl.class));
		return category;
	}

	private CategoryImpl buildCategory() {
		var category = new CategoryImpl();
		category.setSubDepartment(new SubDepartmentImpl());
		return category;
	}	

}
