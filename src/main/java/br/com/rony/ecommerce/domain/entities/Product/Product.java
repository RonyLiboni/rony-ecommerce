package br.com.rony.ecommerce.domain.entities.Product;

import java.math.BigDecimal;
import java.util.List;

import br.com.rony.ecommerce.adapters.domain.entities.product.Image;
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

	void addNewImage(Image image);

	List<Image> getImages();

	void updateImageOrderByKey(String key, Integer imageOrder);

	void removeImageByKey(String key);

	Image getImageByKey(String key);

	void addNewImage(String key, Integer imageOrder, String url);

}
