package br.com.rony.ecommerce.application.dto.commons;

import java.util.Collection;

public class PageResource<T> {
	
	private Collection<T> body;
	private Long totalCount;
		
	public PageResource(Collection<T> body, Long totalCount) {
		this.body = body;
		this.totalCount = totalCount;
	}
	
	public Collection<T> getBody() {
		return body;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
		
}
