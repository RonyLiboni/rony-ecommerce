package br.com.rony.ecommerce.application.controllers.department_hierarchy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.rony.ecommerce.application.controllers.BaseRestController;
import br.com.rony.ecommerce.application.dto.department_hierarchy.SubDepartmentFormDTO;
import br.com.rony.ecommerce.application.mappers.department_hierarchy.SubDepartmentMapper;
import br.com.rony.ecommerce.domain.services.department_hierarchy.SubDepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class SubDepartmentController extends BaseRestController {
	
	private final static String SUB_DEPARTMENT_URI = "/sub-departments";
	private final SubDepartmentMapper subDepartmentMapper;
	private final SubDepartmentService subDepartmentService;
	
	public SubDepartmentController(HttpServletRequest request, SubDepartmentMapper subDepartmentMapper,
			SubDepartmentService subDepartmentService) {
		super(request);
		this.subDepartmentMapper = subDepartmentMapper;
		this.subDepartmentService = subDepartmentService;
	}
		
	@PostMapping(SUB_DEPARTMENT_URI)
	public ResponseEntity<Void> create(@RequestBody @Valid final SubDepartmentFormDTO form){
		return created(subDepartmentService.create(subDepartmentMapper.toEntity(form)).toString());
	}
			
	@PutMapping(SUB_DEPARTMENT_URI + "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody SubDepartmentFormDTO form){
		subDepartmentService.updateBy(id, subDepartmentMapper.toEntity(form));
		return noContent();
	}
	
}
