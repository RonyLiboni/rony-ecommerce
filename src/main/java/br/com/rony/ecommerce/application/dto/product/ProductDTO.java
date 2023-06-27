package br.com.rony.ecommerce.application.dto.product;

import java.math.BigDecimal;
import java.util.List;
import br.com.rony.ecommerce.application.dto.commons.NameDTO;
import br.com.rony.ecommerce.application.dto.department_hierarchy.CategoryDTO;

public class ProductDTO extends NameDTO {
	
	private Long id;
	private String sku;
	private BigDecimal price;
	private String description;
	private CategoryDTO category;
	private List<ImageDTO> images;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	public List<ImageDTO> getImages() {
		return images;
	}
	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	
}
