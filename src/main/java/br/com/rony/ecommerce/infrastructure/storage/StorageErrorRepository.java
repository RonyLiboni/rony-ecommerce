package br.com.rony.ecommerce.infrastructure.storage;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class StorageErrorRepository {

	private final EntityManager entityManager;

	public StorageErrorRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void saveError(StorageError storageError) {
		entityManager.merge(storageError);
	}
	
}
