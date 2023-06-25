package br.com.rony.ecommerce.application.dto.product;

import java.math.BigDecimal;
import br.com.rony.ecommerce.application.dto.commons.NameDTO;
import br.com.rony.ecommerce.application.dto.department_hierarchy.CategoryIdDTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductFormDTO extends NameDTO {
	
	@NotBlank
	@Size(min = 4, max = 36)
	private String sku;
	@Digits(integer = 6, fraction = 2)
	private BigDecimal price;
	@NotBlank
	@Size(min = 5, max = 255)
	private String description;
	@NotNull
	private CategoryIdDTO category;
		
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryIdDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryIdDTO category) {
		this.category = category;
	}
	
}
