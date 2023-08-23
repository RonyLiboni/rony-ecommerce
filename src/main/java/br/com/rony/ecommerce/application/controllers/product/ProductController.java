package br.com.rony.ecommerce.application.controllers.product;

import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.rony.ecommerce.application.controllers.BaseRestController;
import br.com.rony.ecommerce.application.dto.commons.PageResource;
import br.com.rony.ecommerce.application.dto.product.ProductDTO;
import br.com.rony.ecommerce.application.dto.product.ProductFormDTO;
import br.com.rony.ecommerce.application.dto.product.ProductUpdateFormDTO;
import br.com.rony.ecommerce.application.mappers.product.ProductMapper;
import br.com.rony.ecommerce.domain.services.product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class ProductController extends BaseRestController {

	private final static String PRODUCT_URI = "/products";
	private final ProductMapper productMapper;
	private final ProductService productService;
			
	public ProductController(HttpServletRequest request, ProductMapper productMapper, ProductService productService) {
		super(request);
		this.productMapper = productMapper;
		this.productService = productService;
	}

	@PostMapping(PRODUCT_URI)
	public ResponseEntity<Void> create(@RequestBody @Valid final ProductFormDTO form){
		return created(productService.create(productMapper.toEntity(form)).toString());
	}
	
	@GetMapping(PRODUCT_URI + "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
		return ok(productMapper.toProductDTO(productService.findByIdWithAllRelatedDataLoaded(id)));
	}
		
	@PutMapping(PRODUCT_URI + "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ProductUpdateFormDTO form){
		productService.updateBy(id, productMapper.toEntity(form));
		return noContent();
	}
	
	@GetMapping(PRODUCT_URI)
	public ResponseEntity<PageResource<ProductDTO>> findProducts(@RequestParam(defaultValue = "") String sku,
			@RequestParam(defaultValue = "asc") String sort, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return ok(productMapper.toPageResource(productService.findProductsBySku(sku.trim(), sort, pageNumber, pageSize),
				  productService.findProductsBySkuTotalCount(sku.trim())));
	}
	
	@GetMapping(PRODUCT_URI + "/search")
	public ResponseEntity<PageResource<ProductDTO>> customerSearch(@RequestParam(defaultValue = "") String productName,
			@RequestParam(defaultValue = "asc") String sortDirection,
			@RequestParam(defaultValue = "id") String sortField, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") BigDecimal startPrice,
			@RequestParam(defaultValue = "1000000") BigDecimal endPrice, @RequestParam(required = false) Collection<String> categoriesDTO,
			@RequestParam(required = false) Collection<String> subDepartmentsDTO, @RequestParam(required = false) Collection<String> departmentsDTO) {
		return ok(productMapper.toPageResource(productService.customerSearch(productName.trim(), sortDirection, sortField, pageNumber, pageSize, startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO),
				  productService.customerSearchTotalCount(productName.trim(), startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO)));
	}

}
