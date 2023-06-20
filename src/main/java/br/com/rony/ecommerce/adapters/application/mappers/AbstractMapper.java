package br.com.rony.ecommerce.adapters.application.mappers;

import org.modelmapper.ModelMapper;

public abstract class AbstractMapper {

	private final ModelMapper mapper = new ModelMapper();

	public ModelMapper mapper() {
		return mapper;
	}

}
