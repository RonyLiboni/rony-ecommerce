package br.com.rony.ecommerce.domain.exceptions;

public class BusinessDataTruncationException extends BusinessException {
	
	private static final long serialVersionUID = 6698650575027788889L;
	
	public BusinessDataTruncationException(String message) {
		super(message);
	}

}
