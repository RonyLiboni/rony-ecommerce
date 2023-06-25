package br.com.rony.ecommerce.domain.services.product;

import br.com.rony.ecommerce.domain.entities.Product.Product;

public interface ProductService {

	Long create(Product product);

	Product findByIdWithAllRelatedDataLoaded(Long id);

	void updateBy(Long id, Product product);

	Product findBy(Long id);

}
