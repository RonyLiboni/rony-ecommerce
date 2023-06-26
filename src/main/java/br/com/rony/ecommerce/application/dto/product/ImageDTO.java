package br.com.rony.ecommerce.application.dto.product;

public class ImageDTO {

	private String url;
	private Integer imageOrder;
	private String key;
	
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
