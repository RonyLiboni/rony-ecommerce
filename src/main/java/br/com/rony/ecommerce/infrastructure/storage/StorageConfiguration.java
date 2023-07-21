package br.com.rony.ecommerce.infrastructure.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
		if (storageProperties.getAccessKeyId().isBlank() || storageProperties.getSecretAccessKey().isBlank()
				|| storageProperties.getBucket().isBlank() || storageProperties.getRegion() == null) {
			try {
				Path caminhoDiretorios = getPhotosDirectory();
				Files.createDirectories(caminhoDiretorios);
			} catch (Exception e) {
			}
			return new LocalImageStorageService(storageErrorRepository);
		}
		return new S3ImageStorageService(amazonS3, storageProperties, storageErrorRepository);
	}

	private Path getPhotosDirectory() {
		try {
			return Path.of(new File(".").getCanonicalPath(), "/src/main/resources/product_images/");
		} catch (IOException e) {
			throw new StorageException("Chosen local directory is unavailable.");
		}
	}

}
