package br.com.rony.ecommerce.application.dto.product;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerSearchFilterDTO {
	private Collection<String> departments = new ArrayList<>();
	private Collection<String> subDepartments = new ArrayList<>();
	private Collection<String> categories = new ArrayList<>();
	
	public Collection<String> getDepartments() {
		return departments;
	}
	public void setDepartments(Collection<String> departments) {
		this.departments = departments;
	}
	public Collection<String> getSubDepartments() {
		return subDepartments;
	}
	public void setSubDepartments(Collection<String> subDepartments) {
		this.subDepartments = subDepartments;
	}
	public Collection<String> getCategories() {
		return categories;
	}
	public void setCategories(Collection<String> categories) {
		this.categories = categories;
	}

}
