package br.com.rony.ecommerce.application.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/api")
public abstract class BaseRestController {
	
	private final HttpServletRequest request;

	public BaseRestController(HttpServletRequest request) {
		this.request = request;
	}
	
	protected ResponseEntity<Void> created(String id) {		
		return ResponseEntity.created(buildLocation(id)).build();
	}
	
	protected ResponseEntity<Void> ok(){
		return ResponseEntity.ok().build();
	}
	
	 public <T> ResponseEntity<T> ok(T body) {
	        return ResponseEntity.ok(body);
	  }
	
	protected ResponseEntity<Void> noContent(){
		return ResponseEntity.noContent().location(buildLocation()).build();
	}

	private URI buildLocation(String id) {
		String location = String.format(getRequestUrl() + "/%s", id);
		return UriComponentsBuilder.fromHttpUrl(location).build()
									.toUri();
	}
	
	private URI buildLocation() {
		return UriComponentsBuilder.fromHttpUrl(getRequestUrl()).build()
									.toUri();
	}

	private String getRequestUrl() {
		return request.getRequestURL().toString();
	}
}
