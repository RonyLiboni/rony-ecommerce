package br.com.rony.ecommerce.application.mappers.product;

import br.com.rony.ecommerce.adapters.domain.entities.product.Image;
import br.com.rony.ecommerce.application.dto.product.ImageDTO;

public interface ProductImageMapper {
	
	ImageDTO toDTO(Image image);
}
