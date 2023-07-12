package br.com.rony.ecommerce.infrastructure.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.s3.AmazonS3;

import br.com.rony.ecommerce.adapters.infrastructure.storage.LocalImageStorageService;
import br.com.rony.ecommerce.adapters.infrastructure.storage.S3ImageStorageService;

@Configuration
public class StorageConfiguration {
	
	private final StorageProperties storageProperties;
		
	public StorageConfiguration(StorageProperties storageProperties) {
		this.storageProperties = storageProperties;
	}

	@Bean
	ImageStorageService imageStorageService(StorageErrorRepository storageErrorRepository, AmazonS3 amazonS3) {
		if(storageProperties.getAccessKeyId().isBlank() ||
		   storageProperties.getSecretAccessKey().isBlank() ||
		   storageProperties.getBucket().isBlank() ||
		   storageProperties.getRegion() == null) {
			return new LocalImageStorageService(storageErrorRepository);	
		}
		return new S3ImageStorageService(amazonS3, storageProperties, storageErrorRepository);	
	}
	
}
