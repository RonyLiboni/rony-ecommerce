package br.com.rony.ecommerce.adapters.domain.services.department_hierarchy;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.rony.ecommerce.data.repository.department_hierarchy.DepartmentRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;
import br.com.rony.ecommerce.domain.services.department_hierarchy.DepartmentService;
import jakarta.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepository departmentRepository;
		
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Long create(Department entity) {
		Department department = departmentRepository.saveOrMerge(entity);
		return department.getId();
	}

	@Override
	public Department findByIdWithAllRelatedDataLoaded(Long id) {
		return departmentRepository.findByIdWithAllRelatedDataLoaded(id);
	}

	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	@Transactional
	public void updateBy(Long id, Department form) {
		Department departmentToUpdate = findBy(id);
		departmentToUpdate.setName(form.getName());
	}

	@Override
	public Department findBy(Long id) {
		return departmentRepository.findBy(id);
	}
	
}
