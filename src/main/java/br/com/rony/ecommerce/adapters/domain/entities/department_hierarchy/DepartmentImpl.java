package br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import br.com.rony.ecommerce.adapters.domain.entities.commons.EntityWithNameAndLongId;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Departments")
public class DepartmentImpl extends EntityWithNameAndLongId implements Department {
	
	private Set<SubDepartment> subDepartments;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = SubDepartmentImpl.class, mappedBy = "department")
	@JsonManagedReference
	public Set<SubDepartment> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(Set<SubDepartment> subDepartments) {
		this.subDepartments = subDepartments;
	}
	
	
}
