package br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.rony.ecommerce.adapters.domain.entities.commons.EntityWithNameAndLongId;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sub_Departments")
public class SubDepartmentImpl extends EntityWithNameAndLongId implements SubDepartment {
	
	private Department department;
	private Set<Category> categories;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = CategoryImpl.class, mappedBy = "subDepartment")
	@JsonManagedReference
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DepartmentImpl.class)
	@JsonBackReference
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
}
