package br.com.rony.ecommerce.data.repository.product;

import br.com.rony.ecommerce.data.repository.BasicRepository;
import br.com.rony.ecommerce.domain.entities.Product.Product;

public interface ProductRepository extends BasicRepository<Product, Long> {

	Product findByIdWithAllRelatedDataLoaded(Long id);

}
