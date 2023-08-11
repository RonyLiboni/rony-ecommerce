package br.com.rony.ecommerce.application.mappers.department_hierarchy;

import java.util.Collection;

import br.com.rony.ecommerce.application.dto.department_hierarchy.CategoryDTO;
import br.com.rony.ecommerce.application.dto.department_hierarchy.CategoryFormDTO;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;

public interface CategoryMapper {

	Category toEntity(CategoryFormDTO form);

	Collection<CategoryDTO> toListDTO(Collection<Category> categories);

}
