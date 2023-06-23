package br.com.rony.ecommerce.domain.exceptions;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 5669415235869701907L;

	public BusinessException(String message) {
		super(message);
	}

}
