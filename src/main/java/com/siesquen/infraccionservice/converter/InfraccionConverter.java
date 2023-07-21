package com.siesquen.infraccionservice.converter;


import org.springframework.stereotype.Component;

import com.siesquen.infraccionservice.dto.InfraccionDTO;
import com.siesquen.infraccionservice.entity.Infraccion;
@Component
public class InfraccionConverter extends AbstractConverter<Infraccion, InfraccionDTO>{

	@Override
	public InfraccionDTO fromEntity(Infraccion entity) {
		
		if(entity==null) return null;
		return InfraccionDTO.builder() 
				.id(entity.getId())
				.dni(entity.getDni())
				.created_at(entity.getCreatedAt())
				.falta(entity.getFalta())
				.infraccion(entity.getInfraccion())
				.descripcion(entity.getDescripcion())
				.build();
	}

	@Override
	public Infraccion fromDTO(InfraccionDTO dto) {
		if(dto==null) return null;
		return Infraccion.builder() 
				.id(dto.getId())
				.dni(dto.getDni())
				.falta(dto.getFalta())
				.infraccion(dto.getInfraccion())
				.descripcion(dto.getDescripcion())
				.build();
	}

}