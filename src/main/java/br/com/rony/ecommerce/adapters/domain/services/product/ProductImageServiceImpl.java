package br.com.rony.ecommerce.adapters.domain.services.product;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.rony.ecommerce.adapters.domain.entities.product.Image;
import br.com.rony.ecommerce.data.repository.product.ProductRepository;
import br.com.rony.ecommerce.domain.entities.Product.Product;
import br.com.rony.ecommerce.domain.services.product.ProductImageService;
import br.com.rony.ecommerce.infrastructure.storage.ImageStorageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	
	private ProductRepository productRepository;
	private ImageStorageService imageStorageService;
	
	public ProductImageServiceImpl(ProductRepository productRepository, ImageStorageService imageStorageService) {
		this.productRepository = productRepository;
		this.imageStorageService = imageStorageService;
	}

	@Override
	public Image addImageIn(Long productId, MultipartFile imageToAdd, Integer imageOrder) {
		Product product = productRepository.findBy(productId);
		String key = product.getSku() + "_" + UUID.randomUUID().toString();
		product.addNewImage(key, imageOrder, imageStorageService.uploadImage(imageToAdd, key));
		productRepository.saveOrMerge(product);
		return product.getImageByKey(key);
	}

	@Override
	public void updateImageOrder(Long productId, String key, Integer imageOrder) {
		Product product = productRepository.findByIdWithAllRelatedDataLoaded(productId);
		product.updateImageOrderByKey(key, imageOrder);
		productRepository.saveOrMerge(product);
	}

	@Override
	public void deleteBy(Long productId, String key) {
		Product product = productRepository.findByIdWithAllRelatedDataLoaded(productId);
		product.removeImageByKey(key);
		productRepository.saveOrMerge(product);
        imageStorageService.deleteImageBykey(key);
	}

}
