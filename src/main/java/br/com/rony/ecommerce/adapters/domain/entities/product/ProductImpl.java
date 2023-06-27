package br.com.rony.ecommerce.adapters.domain.entities.product;

import java.math.BigDecimal;
import java.util.List;

import br.com.rony.ecommerce.adapters.domain.entities.commons.EntityWithNameAndLongId;
import br.com.rony.ecommerce.adapters.domain.entities.department_hierarchy.CategoryImpl;
import br.com.rony.ecommerce.domain.entities.Product.Product;
import br.com.rony.ecommerce.domain.entities.department_hierarchy.Category;
import br.com.rony.ecommerce.domain.exceptions.BusinessEntityNotFoundException;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class ProductImpl extends EntityWithNameAndLongId implements Product {
	
	private String sku;
	private BigDecimal price;
	private String description;
	private Category category;
	private List<Image> images;
	
	public String getSku() {
		return sku;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CategoryImpl.class)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}
	
	@ElementCollection(fetch = FetchType.LAZY, targetClass = Image.class)
    @CollectionTable(name = "Product_Images", joinColumns = @JoinColumn(name = "product_id"))
	public List<Image> getImages() {
		return images;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public void updateImageOrderByKey(String key, Integer imageOrder) {		
		Image image = getImageByKey(key);
		image.setImageOrder(imageOrder);
		removeImageByKey(key);
		addNewImage(image);
	}

	@Override
	public void removeImageByKey(String key) {
		if(!getImages().removeIf(i -> i.getKey().equals(key))) {
			throw new BusinessEntityNotFoundException(buildMessageErrorWhenKeyIsNotFound(key));
		}
	}

	private String buildMessageErrorWhenKeyIsNotFound(String key) {
		return String.format("It was not found image with key '%s' in product with id '%s'", key, getId());
	}

	@Override
	public Image getImageByKey(String key) {
		return getImages().stream().filter(i-> i.getKey().equals(key)).findFirst()
				.orElseThrow(() -> new BusinessEntityNotFoundException(buildMessageErrorWhenKeyIsNotFound(key)));
	}

	@Override
	public void addNewImage(String key, Integer imageOrder, String url) {
		Image image = new Image();
		image.setKey(key);
		image.setUrl(url);
		image.setImageOrder(imageOrder);
		addNewImage(image);		
	}
	
	@Override
	public void addNewImage(Image image) {
		this.images.add(image);		
	}

}
