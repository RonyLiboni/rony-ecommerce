package br.com.rony.ecommerce.infrastructure.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

	String uploadImage(MultipartFile imageToAdd, String key);

	void deleteImageBykey(String key);

}
