package com.siesquen.infraccionservice.validator;

import com.siesquen.infraccionservice.entity.Infraccion;
import com.siesquen.infraccionservice.exception.ValidateServiceException;

public class InfraccionValidator {
	public static void save(Infraccion infraccion) {
		if (infraccion.getDni()==null ) {
			throw new ValidateServiceException("El dni es requerido");
		}
		
		if (infraccion.getDni().length()>8) {
			throw new ValidateServiceException("El dni no puede ser mas de 8 caracteres");
		}
		if (infraccion.getFalta().length()>3) {
			throw new ValidateServiceException("El dni mo puede ser mas de 8 caracteres");
		}
		
		if (infraccion.getInfraccion()==null ) {
			throw new ValidateServiceException("Debe poner la infraccion");
		}
	}

}
