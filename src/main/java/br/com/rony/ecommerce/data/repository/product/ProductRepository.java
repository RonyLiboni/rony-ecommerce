package br.com.rony.ecommerce.data.repository.product;

import java.util.Collection;

import br.com.rony.ecommerce.data.repository.BasicRepository;
import br.com.rony.ecommerce.domain.entities.Product.Product;

public interface ProductRepository extends BasicRepository<Product, Long> {

	Product findByIdWithAllRelatedDataLoaded(Long id);

	Long findProductsBySkuTotalCount(String sku);

	Collection<Product> findProductsBySku(String sku, String sort, Integer pageNumber, Integer pageSize);

}
