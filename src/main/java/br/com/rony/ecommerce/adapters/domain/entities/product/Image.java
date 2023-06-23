package br.com.rony.ecommerce.adapters.domain.entities.product;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Image {
	
	private String key;
	private String url;
	private Integer imageOrder;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getImageOrder() {
		return imageOrder;
	}
	public void setImageOrder(Integer imageOrder) {
		this.imageOrder = imageOrder;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(key);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(key, other.key);
	}

}
