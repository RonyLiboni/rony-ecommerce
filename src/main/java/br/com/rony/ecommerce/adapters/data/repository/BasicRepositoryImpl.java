package br.com.rony.ecommerce.adapters.data.repository;

import br.com.rony.ecommerce.data.repository.BasicRepository;
import br.com.rony.ecommerce.domain.exceptions.BusinessDataBaseConstraintException;
import br.com.rony.ecommerce.domain.exceptions.BusinessEntityNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

public abstract class BasicRepositoryImpl<Entity, ID> implements BasicRepository<Entity, ID>{

	private final EntityManager entityManager;

	public BasicRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	public Entity saveOrMerge(Entity entity) {
		try {
			Entity mergedEntity = entityManager.merge(entity);
			entityManager.flush();
			return mergedEntity;
		} catch (PersistenceException e) {
			throw new BusinessDataBaseConstraintException(getEntityClass().getSimpleName()+ " " +e.getMessage());
		}
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
