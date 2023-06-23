package br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.rony.ecommerce.adapters.domain.entities.commons.EntityWithNameAndLongId;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.SubDepartment;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categories")
public class CategoryImpl extends EntityWithNameAndLongId implements Category {
	
	private SubDepartment subDepartment;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = SubDepartmentImpl.class)
	@JoinColumn(name = "sub_department_id")
	@JsonBackReference
	public SubDepartment getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(SubDepartment subDepartment) {
		this.subDepartment = subDepartment;
	}

}
