package br.com.rony.ecommerce.application.controllers.department_hierarchy;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rony.ecommerce.application.controllers.BaseRestController;
import br.com.rony.ecommerce.application.dto.department_hierarchy.DepartmentDTO;
import br.com.rony.ecommerce.application.dto.department_hierarchy.DepartmentFormDTO;
import br.com.rony.ecommerce.application.mappers.department_hierarchy.DepartmentMapper;
import br.com.rony.ecommerce.domain.services.department_hierarchy.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class DepartmentController extends BaseRestController {
	
	private final static String DEPARTMENT_URI = "/departments";
	private final DepartmentMapper departmentMapper;
	private final DepartmentService departmentService;
	
	public DepartmentController(HttpServletRequest request, DepartmentMapper departmentMapper,
			DepartmentService departmentService) {
		super(request);
		this.departmentMapper = departmentMapper;
		this.departmentService = departmentService;
	}
		
	@PostMapping(DEPARTMENT_URI)
	public ResponseEntity<Void> create(@RequestBody @Valid final DepartmentFormDTO form){
		return created(departmentService.create(departmentMapper.toEntity(form)).toString());
	}
		
	@GetMapping(DEPARTMENT_URI + "/{name}")
	public ResponseEntity<DepartmentDTO> findByName(@PathVariable String name){
		return ResponseEntity.ok(departmentMapper.toDepartmentDTO(departmentService.findByNameWithAllRelatedDataLoaded(name)));
	}
	
	@GetMapping(DEPARTMENT_URI)
	public ResponseEntity<List<DepartmentDTO>> getAll(){
		return ResponseEntity.ok(departmentService.findAll()
										.stream()
										.map(department -> departmentMapper.toDepartmentDTO(department))
										.toList());
	}
	
	@PutMapping(DEPARTMENT_URI + "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody DepartmentFormDTO form){
		departmentService.updateBy(id, departmentMapper.toEntity(form));
		return noContent();
	}
	
}
