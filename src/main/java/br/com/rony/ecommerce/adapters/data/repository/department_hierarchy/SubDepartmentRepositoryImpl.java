package br.com.rony.ecommerce.adapters.data.repository.department_hierarchy;

import org.springframework.stereotype.Repository;

import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.SubDepartmentImpl;
import br.com.rony.ecommerce.data.repository.department_hierarchy.SubDepartmentRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;
import jakarta.persistence.EntityManager;

@Repository
public class SubDepartmentRepositoryImpl extends BasicRepositoryImpl<SubDepartment,Long> implements SubDepartmentRepository {
		
	public SubDepartmentRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	protected Class<? extends SubDepartment> getEntityClass() {
		return SubDepartmentImpl.class;
	}
	
}
