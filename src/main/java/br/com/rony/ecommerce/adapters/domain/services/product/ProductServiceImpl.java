package br.com.rony.ecommerce.adapters.domain.services.product;

import java.util.Collection;

import org.springframework.stereotype.Service;

import br.com.rony.ecommerce.data.repository.product.ProductRepository;
import br.com.rony.ecommerce.domain.entities.Product.Product;
import br.com.rony.ecommerce.domain.services.product.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Long create(Product entity) {
		Product product = saveOrUpdate(entity);
		return product.getId();
	}

	private Product saveOrUpdate(Product entity) {
		return productRepository.saveOrMerge(entity);
	}

	@Override
	public Product findByIdWithAllRelatedDataLoaded(Long id) {
		return productRepository.findByIdWithAllRelatedDataLoaded(id);
	}

	@Override
	public void updateBy(Long id, Product form) {
		Product productToUpdate = findBy(id);
		productToUpdate.setName(form.getName());
		productToUpdate.setPrice(form.getPrice());
		productToUpdate.setDescription(form.getDescription());
		productToUpdate.setCategory(form.getCategory());
		saveOrUpdate(productToUpdate);
	}

	@Override
	public Product findBy(Long id) {
		return productRepository.findBy(id);
	}

	@Override
	public Long findProductsBySkuTotalCount(String sku) {
		return productRepository.findProductsBySkuTotalCount(sku);
	}

	@Override
	public Collection<Product> findProductsBySku(String sku, String sort, Integer pageNumber, Integer pageSize) {
		return productRepository.findProductsBySku(sku, sort, pageNumber, pageSize);
	}

}
