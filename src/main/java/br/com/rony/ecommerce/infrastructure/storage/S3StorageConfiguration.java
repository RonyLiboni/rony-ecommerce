package br.com.rony.ecommerce.infrastructure.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3StorageConfiguration {
	
	private final StorageProperties storageProperties;
		
	public S3StorageConfiguration(StorageProperties storageProperties) {
		this.storageProperties = storageProperties;
	}

	@Bean
	AmazonS3 amazonS3() {
		var credentials = new BasicAWSCredentials(
										storageProperties.getAccessKeyId(), 
										storageProperties.getSecretAccessKey());
		return AmazonS3ClientBuilder.standard()
										.withCredentials(new AWSStaticCredentialsProvider(credentials))
										.withRegion(storageProperties.getRegion())
										.build();
	}
	
}
