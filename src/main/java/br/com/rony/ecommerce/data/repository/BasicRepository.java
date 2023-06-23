package br.com.rony.ecommerce.data.repository;

public interface BasicRepository<Entity, ID> {

	Entity saveOrMerge(Entity entity);
	Entity findBy(ID id);
	
}
