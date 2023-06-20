package br.com.rony.ecommerce.adapters.domain.services.department_hierarchy;

import org.springframework.stereotype.Service;

import br.com.rony.ecommerce.data.repository.department_hierarchy.CategoryRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;
import br.com.rony.ecommerce.domain.services.department_hierarchy.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
		
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Long create(Category entity) {
		Category category = saveOrUpdate(entity);
		return category.getId();
	}

	private Category saveOrUpdate(Category entity) {
		return categoryRepository.saveOrMerge(entity);
	}

	@Override
	public void updateBy(Long id, Category form) {
		Category categoryToUpdate = categoryRepository.findBy(id);
		categoryToUpdate.setName(form.getName());
		saveOrUpdate(categoryToUpdate);
	}
	
}
