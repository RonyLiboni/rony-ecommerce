package br.com.rony.ecommerce.adapters.data.repository.product;

import org.springframework.stereotype.Repository;
import br.com.rony.ecommerce.adapters.data.repository.BasicRepositoryImpl;
import br.com.rony.ecommerce.adapters.domain.entities.product.ProductImpl;
import br.com.rony.ecommerce.data.repository.product.ProductRepository;
import br.com.rony.ecommerce.domain.entities.Product.Product;
import br.com.rony.ecommerce.domain.exceptions.BusinessEntityNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository
public class ProductRepositoryImpl extends BasicRepositoryImpl<Product, Long> implements ProductRepository {
	
	private final String FIND_AND_LOAD_ALL_RELATED_DATA = "Select p from "+getEntityClass().getSimpleName()+" p "
															+ "LEFT JOIN FETCH p.images pi "
															+ "LEFT JOIN FETCH p.category c ";

	public ProductRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Product findByIdWithAllRelatedDataLoaded(Long id) {
		try {
			String query = FIND_AND_LOAD_ALL_RELATED_DATA+ " WHERE p.id = :id ORDER BY pi.imageOrder ASC";
			return getEntityManager()
							.createQuery(query, getEntityClass())
							.setParameter("id", id)
							.getSingleResult();
		} catch (NoResultException e) {
			throw new BusinessEntityNotFoundException(String.format(getEntityClass().getSimpleName().replace("Impl", "")+" with id=%s not found.", id));
		}
	}

	@Override
	protected Class<? extends Product> getEntityClass() {
		return ProductImpl.class;
	}

}
