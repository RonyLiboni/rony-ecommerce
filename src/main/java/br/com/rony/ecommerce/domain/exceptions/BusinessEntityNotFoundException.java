package br.com.rony.ecommerce.domain.exceptions;

public class BusinessEntityNotFoundException extends BusinessException{

	private static final long serialVersionUID = -4996830595916484368L;

	public BusinessEntityNotFoundException(String message) {
		super(message);
	}
	
}
