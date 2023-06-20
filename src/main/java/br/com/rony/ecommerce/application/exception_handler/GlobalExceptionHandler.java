package br.com.rony.ecommerce.application.exception_handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.rony.ecommerce.application.exception_handler.ProblemDetails.ValidationError;
import br.com.rony.ecommerce.domain.exceptions.BusinessDataBaseConstraintException;
import br.com.rony.ecommerce.domain.exceptions.BusinessEntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	public static final String GENERIC_MESSAGE_FOR_USER = "An unexpected error happened in the system. Try again and if the problem persist "
			+ "please, contact a system administrator.";
		
	@ExceptionHandler({BusinessEntityNotFoundException.class})
	public ResponseEntity<Object> handleNotFound(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND; 
		String userMessage = "One of the resources needed for your request wasn't found.";		
		var problemDetails = buildProblemDetails(e.getMessage(),userMessage, status.value());
		return handleExceptionInternal(e, problemDetails, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler({BusinessDataBaseConstraintException.class})
	public ResponseEntity<Object> handleBusinessDataBaseConstraintException(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST; 
		String userMessage = "The provided information of one child resource was not found.";		
		var problemDetails = buildProblemDetails(e.getMessage(),userMessage, status.value());
		return handleExceptionInternal(e, problemDetails, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleUncaught(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; 
		var problemDetails = buildProblemDetails(e.getMessage(),GENERIC_MESSAGE_FOR_USER, status.value());
		return handleExceptionInternal(e, problemDetails, new HttpHeaders(), status, request);
	}
	
	@Override
	@Nullable
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		if (body == null) {
			body = buildProblemDetails(ex.getMessage(), GENERIC_MESSAGE_FOR_USER, status.value());
			return super.handleExceptionInternal(ex, body, headers, status, request);
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		   status =  HttpStatus.BAD_REQUEST;
		    String detail = "One or more fields are invalid. Please fill the form with valid data and try again.";
		    
		    BindingResult bindingResult = ex.getBindingResult();
		    
		    List<ProblemDetails.ValidationError> validationErrors = bindingResult.getAllErrors().stream()
		    		.map(objectError -> {
		    			@SuppressWarnings("null")
						String message = getMessageSource().getMessage(objectError, LocaleContextHolder.getLocale());
		    			String name = objectError.getObjectName();
		    			
		    			if (objectError instanceof FieldError) {
		    				name = ((FieldError) objectError).getField();
		    			}
		    			var validationError = new ProblemDetails.ValidationError();
		    			validationError.setName(name);
		    			validationError.setUserMessage(message);
		    			return validationError;
		    		})
		    		.collect(Collectors.toList());
		    ProblemDetails problemDetails = buildProblemDetails(detail, detail, status.value(), validationErrors);
		    return handleExceptionInternal(ex, problemDetails, headers, status, request);
	}

	private ProblemDetails buildProblemDetails(String detail, String userMessage, int status) {
		var problemDetails = new ProblemDetails();
		problemDetails.setStatus(status);
		problemDetails.setDetail(detail);
		problemDetails.setUserMessage(userMessage);
		return problemDetails;					
	}

	private ProblemDetails buildProblemDetails(String detail, String userMessage, int status,
			List<ValidationError> validationErrors) {
		var problemDetails =  buildProblemDetails(detail, userMessage, status);
		problemDetails.setValidationErrors(validationErrors);
		return problemDetails;
	}
	
}
