package br.com.rony.ecommerce.domain.entities.Product;

import java.math.BigDecimal;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;

public interface Product {

	Long getId();

	String getName();

	void setName(String name);

	String getSku();

	void setSku(String sku);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	String getDescription();

	void setDescription(String description);

	Category getCategory();

	void setCategory(Category category);

}
