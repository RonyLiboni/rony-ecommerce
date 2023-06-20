package br.com.rony.ecommerce.data.repository.department_hierarchy;

public interface BasicRepository<Entity, ID> {

	Entity saveOrMerge(Entity entity);
	Entity findBy(ID id);
	
}
