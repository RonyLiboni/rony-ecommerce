package br.com.rony.ecommerce.application.mappers.product;

import java.util.Collection;

import br.com.rony.ecommerce.application.dto.commons.PageResource;
import br.com.rony.ecommerce.application.dto.product.ProductDTO;
import br.com.rony.ecommerce.application.dto.product.ProductFormDTO;
import br.com.rony.ecommerce.application.dto.product.ProductUpdateFormDTO;
import br.com.rony.ecommerce.domain.entities.Product.Product;

public interface ProductMapper {

	Product toEntity(ProductFormDTO form);

	ProductDTO toProductDTO(Product product);

	Product toEntity(ProductUpdateFormDTO form);

	PageResource<ProductDTO> toPageResource(Collection<Product> products, Long totalCount);

}
