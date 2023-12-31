package br.com.rony.ecommerce.adapters.data.repository;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.rony.ecommerce.data.repository.BasicRepository;
import br.com.rony.ecommerce.domain.exceptions.BusinessDataBaseConstraintException;
import br.com.rony.ecommerce.domain.exceptions.BusinessDataTruncationException;
import br.com.rony.ecommerce.domain.exceptions.BusinessEntityNotFoundException;
import br.com.rony.ecommerce.domain.exceptions.BusinessNameShouldBeUniqueException;
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
			if(e.getMessage().toLowerCase().contains("duplicate")) {
				throw new BusinessNameShouldBeUniqueException(getEntityClass().getSimpleName()+ " " +e.getMessage());
			} else if (e.getMessage().toLowerCase().contains("data truncation")) {
				throw new BusinessDataTruncationException(getEntityClass().getSimpleName()+ " " +e.getMessage());
			} else if (e.getMessage().toLowerCase().contains("constraint fails")) {
				throw new BusinessDataBaseConstraintException(getEntityClass().getSimpleName()+ " " +e.getMessage());
			}
			throw e;
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
	
	protected int startsAtRow(Integer pageNumber, Integer pageSize) {
		return (pageNumber - 1) * pageSize;
	}
	
	protected String addLikeOperator(String field) {
		return "%" + field + "%";
	}
	
	protected String buildInParameters(Collection<String> predicates) {
		return predicates.stream()
	            .map(s -> "'" + s.trim() + "'")
	            .collect(Collectors.joining(", "));
	}

	protected abstract Class<? extends Entity> getEntityClass();
}
