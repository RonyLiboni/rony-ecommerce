package br.com.rony.ecommerce.adapters.infrastructure.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.rony.ecommerce.infrastructure.storage.ImageStorageService;
import br.com.rony.ecommerce.infrastructure.storage.StorageError;
import br.com.rony.ecommerce.infrastructure.storage.StorageErrorRepository;
import br.com.rony.ecommerce.infrastructure.storage.StorageException;

@Service
public class LocalImageStorageService implements ImageStorageService {

	private StorageErrorRepository storageErrorRepository;
	
	public LocalImageStorageService(StorageErrorRepository storageErrorRepository) {
		this.storageErrorRepository = storageErrorRepository;
	}

	@Override
	public String uploadImage(MultipartFile imageToAdd, String key) {
		try {
			Path filePath = getFilePath(key);
			FileCopyUtils.copy(imageToAdd.getInputStream(), Files.newOutputStream(filePath));
			return filePath.toString();
		} catch (Exception e) {
			throw new StorageException("It was not possible to send the file to server storage.", e);
		}
	}

	@Override
	public void deleteImageBykey(String key) {
		try {
			Files.deleteIfExists(getFilePath(key));
		} catch (Exception e) {
			storageErrorRepository.saveError(new StorageError(key, e.getMessage().substring(0, 250)));
		}
	}
	
	private Path getFilePath(String fileName) {
		return getPhotosDirectory().resolve(Path.of(fileName));
	}
	
	private Path getPhotosDirectory() {
		try {
			return Path.of(new File(".").getCanonicalPath(),
					"/src/main/resources/product_images/");
		} catch (IOException e) {
			throw new StorageException("Chosen local directory is unavailable.");
		}
	}

}
