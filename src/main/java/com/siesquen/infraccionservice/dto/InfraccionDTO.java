package com.siesquen.infraccionservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfraccionDTO {
	private int id;
	private String dni;
	private String falta;
	private Date created_at	;
	private String infraccion;
	private String descripcion;
}
