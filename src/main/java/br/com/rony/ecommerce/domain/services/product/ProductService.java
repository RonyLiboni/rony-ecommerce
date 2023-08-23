package br.com.rony.ecommerce.domain.services.product;

import java.math.BigDecimal;
import java.util.Collection;

import br.com.rony.ecommerce.domain.entities.Product.Product;

public interface ProductService {

	Long create(Product product);

	Product findByIdWithAllRelatedDataLoaded(Long id);

	void updateBy(Long id, Product product);

	Product findBy(Long id);

	Long findProductsBySkuTotalCount(String sku);

	Collection<Product> findProductsBySku(String sku, String sort, Integer pageNumber, Integer pageSize);

	Collection<Product> customerSearch(String productName, String sortDirection, String sortField, Integer pageNumber,
			Integer pageSize, BigDecimal startPrice, BigDecimal endPrice, Collection<String> categoriesDTO,
			Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO);

	Long customerSearchTotalCount(String productName, BigDecimal startPrice, BigDecimal endPrice,
			Collection<String> categoriesDTO, Collection<String> subDepartmentsDTO, Collection<String> departmentsDTO);

}
