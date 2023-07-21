package com.siesquen.infraccionservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.siesquen.infraccionservice.entity.Infraccion;


public interface InfraccionRepository extends JpaRepository<Infraccion,Integer>{
	List<Infraccion> findByDniContaining(String dni, Pageable page); 
	Infraccion findByDni(String dni);
	
}
 