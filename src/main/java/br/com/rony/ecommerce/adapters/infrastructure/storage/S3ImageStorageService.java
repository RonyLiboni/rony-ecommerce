package br.com.rony.ecommerce.adapters.infrastructure.storage;

import java.net.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import br.com.rony.ecommerce.infrastructure.storage.ImageStorageService;
import br.com.rony.ecommerce.infrastructure.storage.StorageError;
import br.com.rony.ecommerce.infrastructure.storage.StorageErrorRepository;
import br.com.rony.ecommerce.infrastructure.storage.StorageException;
import br.com.rony.ecommerce.infrastructure.storage.StorageProperties;

@Service
public class S3ImageStorageService implements ImageStorageService {

	private final AmazonS3 amazonS3;
	private final StorageProperties storageProperties;
	private StorageErrorRepository storageErrorRepository;
	
	public S3ImageStorageService(AmazonS3 amazonS3, StorageProperties storageProperties,
			StorageErrorRepository storageErrorRepository) {
		this.amazonS3 = amazonS3;
		this.storageProperties = storageProperties;
		this.storageErrorRepository = storageErrorRepository;
	}

	@Override
	public String uploadImage(MultipartFile imageToAdd, String key) {
		try {
			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(imageToAdd.getContentType());
			objectMetadata.setContentLength(imageToAdd.getSize());
			amazonS3.putObject(new PutObjectRequest(storageProperties.getBucket(),
													key,
													imageToAdd.getInputStream(),
													objectMetadata)
											.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (Exception e) {
			throw new StorageException("It was not possible to send the file to Amazon S3.", e);
		}
		return getUploadUrlByKey(key);
	}

	private String getUploadUrlByKey(String key) {
		URL url = amazonS3.getUrl(storageProperties.getBucket(), key);		
		return url.toString();
	}

	@Override
	public void deleteImageBykey(String key) {
		try {
			amazonS3.deleteObject(new DeleteObjectRequest(storageProperties.getBucket(),key));
		} catch (Exception e) {
			storageErrorRepository.saveError(new StorageError(key, e.getMessage().substring(0, 250)));
		}
	}

}
