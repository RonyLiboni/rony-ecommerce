package br.com.rony.ecommerce.adapters.data.repository.product;

import java.math.BigDecimal;
import java.util.Collection;

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

	@Override
	public Long findProductsBySkuTotalCount(String sku) {
		String query = "SELECT COUNT(1) FROM ProductImpl p WHERE p.sku LIKE :sku";
		return getEntityManager().createQuery(query, Long.class)
								 .setParameter("sku", addLikeOperator(sku))
								 .getSingleResult();
	}

	@Override
	public Collection<Product> findProductsBySku(String sku, String sort, Integer pageNumber, Integer pageSize) {
		String query = "SELECT p FROM ProductImpl p LEFT JOIN FETCH p.images pi WHERE p.sku LIKE :sku ORDER BY p.sku " + sort;
		return getEntityManager().createQuery(query, Product.class)
								 .setParameter("sku", addLikeOperator(sku))
								 .setFirstResult(startsAtRow(pageNumber, pageSize))
								 .setMaxResults(pageSize)
								 .getResultList();
	}

	@Override
	public Collection<Product> customerSearch(String productName, String sortDirection, String sortField,
			Integer pageNumber, Integer pageSize, BigDecimal startPrice, BigDecimal endPrice,
			Collection<String> categoriesDTO, Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO) {
		String select = "SELECT p FROM ProductImpl p JOIN FETCH p.category c LEFT JOIN FETCH p.images pi JOIN c.subDepartment s JOIN s.department d ";
		String where = "WHERE p.name LIKE :productName AND p.price BETWEEN :startPrice AND :endPrice ";
		String orderBy = String.format("ORDER BY p.%s %s, pi.imageOrder ASC", sortField, sortDirection);
		
		if(categoriesDTO != null && !categoriesDTO.isEmpty()) {
			where += String.format("AND c.name IN (%s) ", buildInParameters(categoriesDTO));
		}
		if(subDepartmentsDTO!= null && !subDepartmentsDTO.isEmpty()) {
			where += String.format("AND s.name IN (%s) ", buildInParameters(subDepartmentsDTO));
		}
		if(departmentsDTO!= null && !departmentsDTO.isEmpty()) {
			where += String.format("AND d.name IN (%s) ", buildInParameters(departmentsDTO));
		}
		String query = select + where + orderBy;
		return getEntityManager().createQuery(query, Product.class)
				.setFirstResult(startsAtRow(pageNumber, pageSize))
				.setParameter("productName", addLikeOperator(productName.trim()))
				.setParameter("startPrice", startPrice)
				.setParameter("endPrice", endPrice)
				.setMaxResults(pageSize).getResultList();
	}

	@Override
	public Long customerSearchTotalCount(String productName, BigDecimal startPrice, BigDecimal endPrice,
			Collection<String> categoriesDTO, Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO) {
		String select = "SELECT COUNT(1) FROM ProductImpl p JOIN p.category c JOIN c.subDepartment s JOIN s.department d ";
		String where = "WHERE p.name LIKE :productName AND p.price BETWEEN :startPrice AND :endPrice ";
		
		if(categoriesDTO != null && !categoriesDTO.isEmpty()) {
			where += String.format("AND c.name IN (%s) ", buildInParameters(categoriesDTO));
		}
		if(subDepartmentsDTO!= null && !subDepartmentsDTO.isEmpty()) {
			where += String.format("AND s.name IN (%s) ", buildInParameters(subDepartmentsDTO));
		}
		if(departmentsDTO!= null && !departmentsDTO.isEmpty()) {
			where += String.format("AND d.name IN (%s) ", buildInParameters(departmentsDTO));
		}

		return getEntityManager().createQuery(select + where, Long.class)
								 .setParameter("productName", addLikeOperator(productName.trim()))
								 .setParameter("startPrice", startPrice)
								 .setParameter("endPrice", endPrice)
								 .getSingleResult();
	}

}
