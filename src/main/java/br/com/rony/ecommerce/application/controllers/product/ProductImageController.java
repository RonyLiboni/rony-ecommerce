package br.com.rony.ecommerce.application.controllers.product;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rony.ecommerce.adapters.domain.entities.product.Image;
import br.com.rony.ecommerce.application.controllers.BaseRestController;
import br.com.rony.ecommerce.application.dto.product.ImageDTO;
import br.com.rony.ecommerce.application.dto.product.ImageFormDTO;
import br.com.rony.ecommerce.application.mappers.product.ProductImageMapper;
import br.com.rony.ecommerce.domain.services.product.ProductImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class ProductImageController extends BaseRestController {

	private final static String PRODUCT_IMAGE_URI = "/products/{productId}/images";
	private final ProductImageMapper productImageMapper;
	private final ProductImageService productImageService;
	
	public ProductImageController(HttpServletRequest request, ProductImageMapper productImageMapper,
			ProductImageService productImageService) {
		super(request);
		this.productImageMapper = productImageMapper;
		this.productImageService = productImageService;
	}
		
	@PostMapping(value = PRODUCT_IMAGE_URI, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ImageDTO> addImageIn(@PathVariable Long productId,@Valid ImageFormDTO form){
		Image image = productImageService.addImageIn(productId, form.getImage() ,form.getImageOrder());
		return ok(productImageMapper.toDTO(image));
	}
	
	@PutMapping(PRODUCT_IMAGE_URI + "/{key}")
	public ResponseEntity<Void> updateImageOrder(@PathVariable Long productId, @PathVariable String key,@RequestBody Integer imageOrder){
		productImageService.updateImageOrder(productId, key, imageOrder);
		return noContent();
	}
		
	@DeleteMapping(PRODUCT_IMAGE_URI + "/{key}")
	public ResponseEntity<Void> deleteImage(@PathVariable Long productId, @PathVariable String key){
		productImageService.deleteBy(productId, key);
		return noContent();
	}

}
