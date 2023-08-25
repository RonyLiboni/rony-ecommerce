package br.com.rony.ecommerce.application.controllers.product;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rony.ecommerce.application.controllers.BaseRestController;
import br.com.rony.ecommerce.application.dto.commons.PageResource;
import br.com.rony.ecommerce.application.dto.product.CustomerSearchFilterDTO;
import br.com.rony.ecommerce.application.dto.product.ProductDTO;
import br.com.rony.ecommerce.application.mappers.product.CustomerSearchFilterMapper;
import br.com.rony.ecommerce.domain.services.product.CustomerSearchFilterService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CustomerSearchFilterController extends BaseRestController{
	private final CustomerSearchFilterService customerSearchFilterService;
	private final CustomerSearchFilterMapper customerSearchFilterMapper;
	
	public CustomerSearchFilterController(HttpServletRequest request,
			CustomerSearchFilterService productSearchFilterService, CustomerSearchFilterMapper customerSearchFilterMapper) {
		super(request);
		this.customerSearchFilterService = productSearchFilterService;
		this.customerSearchFilterMapper = customerSearchFilterMapper;
	}
	
	@GetMapping("/customer-search/filter")
	public ResponseEntity<CustomerSearchFilterDTO> customerSearchFilter(
			@RequestParam(defaultValue = "") String productName,
			@RequestParam(defaultValue = "0") BigDecimal startPrice,
			@RequestParam(defaultValue = "1000000") BigDecimal endPrice,
			@RequestParam(required = false) Collection<String> categoriesDTO,
			@RequestParam(required = false) Collection<String> subDepartmentsDTO,
			@RequestParam(required = false) Collection<String> departmentsDTO) {
		return ok(customerSearchFilterMapper.toFilterDTO(customerSearchFilterService.customerSearch(productName,
					startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO)));
	}
	
	@GetMapping("/customer-search")
	public ResponseEntity<PageResource<ProductDTO>> customerSearch(@RequestParam(defaultValue = "") String productName,
			@RequestParam(defaultValue = "asc") String sortDirection,
			@RequestParam(defaultValue = "id") String sortField, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") BigDecimal startPrice,
			@RequestParam(defaultValue = "1000000") BigDecimal endPrice, @RequestParam(required = false) Collection<String> categoriesDTO,
			@RequestParam(required = false) Collection<String> subDepartmentsDTO, @RequestParam(required = false) Collection<String> departmentsDTO) {
		return ok(customerSearchFilterMapper.toPageResource(customerSearchFilterService.customerSearch(productName, sortDirection, sortField, pageNumber, pageSize, startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO),
				customerSearchFilterService.customerSearchTotalCount(productName, startPrice, endPrice, categoriesDTO, subDepartmentsDTO, departmentsDTO)));
	}
}
