package br.com.rony.ecommerce.adapters.application.mappers.product;

import org.springframework.stereotype.Component;

import br.com.rony.ecommerce.adapters.application.mappers.AbstractMapper;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.CategoryImpl;
import br.com.rony.ecommerce.adapters.domain.entities.product.ProductImpl;
import br.com.rony.ecommerce.application.dto.product.ProductDTO;
import br.com.rony.ecommerce.application.dto.product.ProductFormDTO;
import br.com.rony.ecommerce.application.dto.product.ProductUpdateFormDTO;
import br.com.rony.ecommerce.application.mappers.product.ProductMapper;
import br.com.rony.ecommerce.domain.entities.Product.Product;

@Component
public class ProductMapperImpl extends AbstractMapper implements ProductMapper {

	@Override
	public Product toEntity(ProductFormDTO form) {
		return toEntityInternal(form);
	}
	
	@Override
	public Product toEntity(ProductUpdateFormDTO form) {
		return toEntityInternal(form);
	}

	@Override
	public ProductDTO toProductDTO(Product product) {
		return mapper().map(product, ProductDTO.class);
	}
	
	private ProductImpl buildProduct() {
		var product = new ProductImpl();
		product.setCategory(new CategoryImpl());
		return product;
	}

	private Product toEntityInternal(Object form) {
		var product = buildProduct();
		mapper().map(form, product);
		if (form instanceof ProductUpdateFormDTO) {
			product.setCategory(mapper().map(((ProductUpdateFormDTO) form).getCategory(), CategoryImpl.class));
			return product;
		}
		product.setCategory(mapper().map(((ProductFormDTO) form).getCategory(), CategoryImpl.class));
		return product;
	}

}
