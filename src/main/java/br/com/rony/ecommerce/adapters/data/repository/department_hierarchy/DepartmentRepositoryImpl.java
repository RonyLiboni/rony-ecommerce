package br.com.rony.ecommerce.adapters.data.repository.department_hierarchy;

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
	
}
