package br.com.rony.ecommerce.data.repository.department_hierarchy;

import java.util.List;
import br.com.rony.ecommerce.data.repository.BasicRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;

public interface CategoryRepository extends BasicRepository<Category, Long>{

	List<Category> getAll();

}
