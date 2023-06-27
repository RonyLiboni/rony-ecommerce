package br.com.rony.ecommerce.application.dto.product;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import br.com.rony.ecommerce.adapters.application.validation.product.FileContentType;
import br.com.rony.ecommerce.adapters.application.validation.product.FileSize;
import jakarta.validation.constraints.NotNull;

public class ImageFormDTO {
	
	@NotNull
	@FileSize(max = "3MB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile image; 
	@NotNull
	private Integer imageOrder;
	
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public Integer getImageOrder() {
		return imageOrder;
	}
	public void setImageOrder(Integer imageOrder) {
		this.imageOrder = imageOrder;
	}

}
