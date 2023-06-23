package br.com.rony.ecommerce.adapters.data.repository.department_hierarchy;

import org.springframework.stereotype.Repository;

import br.com.rony.ecommerce.adapters.data.repository.BasicRepositoryImpl;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.CategoryImpl;
import br.com.rony.ecommerce.data.repository.department_hierarchy.CategoryRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;
import jakarta.persistence.EntityManager;

@Repository
public class CategoryRepositoryImpl extends BasicRepositoryImpl<Category,Long> implements CategoryRepository {
		
	public CategoryRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	protected Class<? extends Category> getEntityClass() {
		return CategoryImpl.class;
	}
	
}
