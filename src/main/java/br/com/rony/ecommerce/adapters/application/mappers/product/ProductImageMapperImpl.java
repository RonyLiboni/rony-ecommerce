package br.com.rony.ecommerce.adapters.application.mappers.product;

import org.springframework.stereotype.Component;

import br.com.rony.ecommerce.adapters.application.mappers.AbstractMapper;
import br.com.rony.ecommerce.adapters.domain.entities.product.Image;
import br.com.rony.ecommerce.application.dto.product.ImageDTO;
import br.com.rony.ecommerce.application.mappers.product.ProductImageMapper;

@Component
public class ProductImageMapperImpl extends AbstractMapper implements ProductImageMapper {

	@Override
	public ImageDTO toDTO(Image image) {
		return mapper().map(image, ImageDTO.class);
	}

}
