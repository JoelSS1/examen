package com.siesquen.infraccionservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.siesquen.infraccionservice.entity.Infraccion;


public interface InfraccionService {
	public List<Infraccion> findAll(Pageable page);
	public List<Infraccion> finByDni(String dni,Pageable page);
	public Infraccion findById(int id);
	public Infraccion update(Infraccion infraccion);
	public Infraccion save(Infraccion infraccion);
	public void delete(int id);
}