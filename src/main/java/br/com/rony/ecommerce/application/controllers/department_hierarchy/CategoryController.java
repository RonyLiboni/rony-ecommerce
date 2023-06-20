package br.com.rony.ecommerce.application.controllers.department_hierarchy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rony.ecommerce.application.controllers.BaseRestController;
import br.com.rony.ecommerce.application.dto.department_hierarchy.CategoryFormDTO;
import br.com.rony.ecommerce.application.mappers.department_hierarchy.CategoryMapper;
import br.com.rony.ecommerce.domain.services.department_hierarchy.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class CategoryController extends BaseRestController {
	
	private final static String CATEGORY_URI = "/categories";
	private final CategoryMapper categoryMapper;
	private final CategoryService categoryService;
	
	public CategoryController(HttpServletRequest request, CategoryMapper categoryMapper,
			CategoryService categoryService) {
		super(request);
		this.categoryMapper = categoryMapper;
		this.categoryService = categoryService;
	}
		
	@PostMapping(CATEGORY_URI)
	public ResponseEntity<Void> create(@RequestBody @Valid final CategoryFormDTO form){
		return created(categoryService.create(categoryMapper.toEntity(form)).toString());
	}
			
	@PutMapping(CATEGORY_URI + "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CategoryFormDTO form){
		categoryService.updateBy(id, categoryMapper.toEntity(form));
		return noContent();
	}
	
}
