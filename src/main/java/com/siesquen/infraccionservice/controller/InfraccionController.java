package com.siesquen.infraccionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siesquen.infraccionservice.converter.InfraccionConverter;
import com.siesquen.infraccionservice.dto.InfraccionDTO;
import com.siesquen.infraccionservice.entity.Infraccion;
import com.siesquen.infraccionservice.service.InfraccionService;
import com.siesquen.infraccionservice.util.WrapperResponse;





@RestController
@RequestMapping("/infracciones")



public class InfraccionController {
	
	
	@Autowired
	private InfraccionService service;
	@Autowired
	private InfraccionConverter converter; 
	@GetMapping()
	public ResponseEntity<List<InfraccionDTO>> findAll(
			@RequestParam(value = "dni", required = false, defaultValue = " ") String dni,
			@RequestParam(value = "offset", required = false, defaultValue = " 0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = " 5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Infraccion> infracciones;
		if (dni == null) {
			infracciones = service.findAll(page);

		} else {
			infracciones = service.finByDni(dni, page);
		}
		if (infracciones.isEmpty()) {
			return ResponseEntity.noContent().build();

		}
		List<InfraccionDTO> infraccionDTO=converter.fromEntity(infracciones);
		return new WrapperResponse(true,"succes",infraccionDTO).createResponse(HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse< InfraccionDTO>> findById(@PathVariable("id") int id) {
		Infraccion infraccion = service.findById(id);
		if (infraccion == null) {
			return ResponseEntity.notFound().build();
		}
		InfraccionDTO infraciionDTO=converter.fromEntity(infraccion);
		return new WrapperResponse<InfraccionDTO>(true, "succes",infraciionDTO ).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InfraccionDTO> create(@RequestBody InfraccionDTO articuloDTO) {
		Infraccion registro = service.save(converter.fromDTO(articuloDTO));
		InfraccionDTO registroDTO =converter.fromEntity(registro);
		return new WrapperResponse(true, "succes",registroDTO ).createResponse(HttpStatus.CREATED);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<InfraccionDTO> update(@PathVariable("id") int id, @RequestBody InfraccionDTO articuloDTO) {
		Infraccion registro = service.update(converter.fromDTO(articuloDTO));
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		InfraccionDTO registroDTO =converter.fromEntity(registro);
		return new WrapperResponse(true, "succes",registroDTO ).createResponse(HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<InfraccionDTO> delete(@PathVariable("id") int id) {
		service.delete(id);
		return new WrapperResponse(true, "succes",null ).createResponse(HttpStatus.OK);

	}

}