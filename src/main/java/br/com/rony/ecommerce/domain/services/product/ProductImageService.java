package br.com.rony.ecommerce.domain.services.product;

import org.springframework.web.multipart.MultipartFile;

import br.com.rony.ecommerce.adapters.domain.entities.product.Image;

public interface ProductImageService {

	Image addImageIn(Long productId, MultipartFile image, Integer imageOrder);

	void updateImageOrder(Long productId, String key, Integer imageOrder);

	void deleteBy(Long productId, String key);

}
