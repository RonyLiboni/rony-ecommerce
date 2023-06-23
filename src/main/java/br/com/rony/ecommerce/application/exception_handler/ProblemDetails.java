package br.com.rony.ecommerce.application.exception_handler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetails {
	
	private Integer status;
	private OffsetDateTime timestamp = OffsetDateTime.now();
	private String detail;
	private String userMessage;
	private List<ValidationError> validationErrors;
		
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public static class ValidationError {
		private String name;
		private String userMessage;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getUserMessage() {
			return userMessage;
		}
		
		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}		
	}
	
}
