package br.com.rony.ecommerce.application.mappers.product;

import java.util.Collection;
import java.util.List;
import br.com.rony.ecommerce.application.dto.commons.PageResource;
import br.com.rony.ecommerce.application.dto.product.CustomerSearchFilterDTO;
import br.com.rony.ecommerce.application.dto.product.ProductDTO;
import br.com.rony.ecommerce.domain.entities.Product.Product;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Department;

public interface CustomerSearchFilterMapper {

	CustomerSearchFilterDTO toFilterDTO(List<Department> customerSearch);

	PageResource<ProductDTO> toPageResource(Collection<Product> products, Long totalCount);

}
