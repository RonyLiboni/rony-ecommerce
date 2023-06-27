package br.com.rony.ecommerce.infrastructure.storage;

import java.time.OffsetDateTime;

import br.com.rony.ecommerce.adapters.domain.entities.commons.EntityWithLongId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "storage_errors")
public class StorageError extends EntityWithLongId{

	private String fileKey;
	private String errorMessage;
	private boolean isConsumed;
	private OffsetDateTime createdAt;
	
	public StorageError() {
	}
	
	public StorageError(String fileKey, String errorMessage) {
		this.fileKey = fileKey;
		this.errorMessage = errorMessage;
		this.isConsumed = false;
		this.createdAt = OffsetDateTime.now();
	}
	
	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Column(name = "is_consumed")
	public boolean isConsumed() {
		return isConsumed;
	}
	public void setConsumed(boolean isConsumed) {
		this.isConsumed = isConsumed;
	}
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
