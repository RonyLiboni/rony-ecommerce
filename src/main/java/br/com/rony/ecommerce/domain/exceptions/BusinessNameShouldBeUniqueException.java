package br.com.rony.ecommerce.domain.exceptions;

public class BusinessNameShouldBeUniqueException extends BusinessException{

	private static final long serialVersionUID = -6461709674614326244L;

	public BusinessNameShouldBeUniqueException(String message) {
		super(message);
	}
	
}
