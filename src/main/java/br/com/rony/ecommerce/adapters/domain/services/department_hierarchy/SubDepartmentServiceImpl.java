package br.com.rony.ecommerce.adapters.domain.services.department_hierarchy;

import org.springframework.stereotype.Service;

import br.com.rony.ecommerce.data.repository.department_hierarchy.SubDepartmentRepository;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;
import br.com.rony.ecommerce.domain.services.department_hierarchy.SubDepartmentService;

@Service
public class SubDepartmentServiceImpl implements SubDepartmentService {
	
	private final SubDepartmentRepository subDepartmentRepository;
		
	public SubDepartmentServiceImpl(SubDepartmentRepository subDepartmentRepository) {
		this.subDepartmentRepository = subDepartmentRepository;
	}

	@Override
	public Long create(SubDepartment entity) {
		SubDepartment department = saveOrUpdate(entity);
		return department.getId();
	}

	private SubDepartment saveOrUpdate(SubDepartment entity) {
		return subDepartmentRepository.saveOrMerge(entity);
	}

	@Override
	public void updateBy(Long id, SubDepartment form) {
		SubDepartment subDepartmentToUpdate = subDepartmentRepository.findBy(id);
		subDepartmentToUpdate.setName(form.getName());
		subDepartmentToUpdate.setDepartment(form.getDepartment());
		saveOrUpdate(subDepartmentToUpdate);
	}
	
}
