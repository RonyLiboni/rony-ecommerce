package br.com.rony.ecommerce.adapters.data.repository.department_hierarchy;

import br.com.rony.ecommerce.domain.exceptions.BusinessEntityNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public abstract class BasicRepositoryImpl<Entity, ID> {

	private final EntityManager entityManager;

	public BasicRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	public Entity saveOrMerge(Entity entity) {
		return entityManager.merge(entity);
	}
	
	public Entity findBy(ID id) {
		var entity = entityManager.find(getEntityClass(), id);
		entityCannotBeNull(id, entity);
		return entity;
	}

	protected void entityCannotBeNull(ID id, Entity entity) {
		if(entity == null) {
			throw new BusinessEntityNotFoundException(String.format(getEntityClass().getSimpleName().replace("Impl", "")+" with id=%s not found.", id));
		}
	}
		
	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected abstract Class<? extends Entity> getEntityClass();
}
