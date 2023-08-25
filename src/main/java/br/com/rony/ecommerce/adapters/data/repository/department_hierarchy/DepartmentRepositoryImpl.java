package br.com.rony.ecommerce.adapters.data.repository.department_hierarchy;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.rony.ecommerce.adapters.data.repository.BasicRepositoryImpl;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.DepartmentImpl;
import br.com.rony.ecommerce.data.repository.department_hierarchy.DepartmentRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;
import br.com.rony.ecommerce.domain.exceptions.BusinessEntityNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository
public class DepartmentRepositoryImpl extends BasicRepositoryImpl<Department,Long> implements DepartmentRepository {
	
	private final String FIND_AND_LOAD_ALL_RELATED_DATA = "Select d from "+getEntityClass().getSimpleName()+" d "
																	+ "LEFT JOIN FETCH d.subDepartments sd "
																	+ "LEFT JOIN FETCH sd.categories c ";
	
	public DepartmentRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Department> findAll(){
		return getEntityManager()
				.createQuery(FIND_AND_LOAD_ALL_RELATED_DATA, Department.class)
				.getResultList();
	}
	
	@Override
	public Department findByNameWithAllRelatedDataLoaded(String name) {	
		try {
			return getEntityManager()
							.createQuery(FIND_AND_LOAD_ALL_RELATED_DATA+ " WHERE d.name = :name", getEntityClass())
							.setParameter("name", name)
							.getSingleResult();
		} catch (NoResultException e) {
			throw new BusinessEntityNotFoundException(String.format(getEntityClass().getSimpleName().replace("Impl", "")+" with name=%s not found.", name));
		}
	}
	
	@Override
	protected Class<? extends Department> getEntityClass() {
		return DepartmentImpl.class;
	}
	
	@Override
	public List<Department> customerSearchFilter(String productName, BigDecimal startPrice, BigDecimal endPrice,
			Collection<String> categoriesDTO, Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO) {
		String select = FIND_AND_LOAD_ALL_RELATED_DATA + "JOIN ProductImpl p ON p.category.id = c.id ";
		String where = "WHERE p.name LIKE :productName AND p.price BETWEEN :startPrice AND :endPrice ";
		
		if(categoriesDTO != null && !categoriesDTO.isEmpty()) {
			where += String.format("AND c.name IN (%s) ", buildInParameters(categoriesDTO));
		}
		if(subDepartmentsDTO!= null && !subDepartmentsDTO.isEmpty()) {
			where += String.format("AND sd.name IN (%s) ", buildInParameters(subDepartmentsDTO));
		}
		if(departmentsDTO!= null && !departmentsDTO.isEmpty()) {
			where += String.format("AND d.name IN (%s) ", buildInParameters(departmentsDTO));
		}
		String query = select + where;
		
		return getEntityManager().createQuery(query, Department.class)
				.setParameter("productName", addLikeOperator(productName.trim()))
				.setParameter("startPrice", startPrice)
				.setParameter("endPrice", endPrice)
				.getResultList();
	}
	
}
