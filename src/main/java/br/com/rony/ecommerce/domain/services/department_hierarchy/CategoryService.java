package br.com.rony.ecommerce.domain.services.department_hierarchy;

import java.util.Collection;

import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;

public interface CategoryService {

	Long create(Category category);

	void updateBy(Long id, Category category);

	Collection<Category> getAll();

}
