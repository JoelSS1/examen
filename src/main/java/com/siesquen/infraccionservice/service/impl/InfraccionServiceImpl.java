package com.siesquen.infraccionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siesquen.infraccionservice.entity.Infraccion;
import com.siesquen.infraccionservice.exception.GeneralServiceException;
import com.siesquen.infraccionservice.exception.NoDataServiceException;
import com.siesquen.infraccionservice.exception.ValidateServiceException;
import com.siesquen.infraccionservice.repository.InfraccionRepository;
import com.siesquen.infraccionservice.service.InfraccionService;
import com.siesquen.infraccionservice.validator.InfraccionValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class InfraccionServiceImpl implements InfraccionService{

		
	@Autowired
	
	private InfraccionRepository repository;
	@Override
	@Transactional(readOnly  =true)
	
	public List<Infraccion> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly  =true)
	public List<Infraccion> finByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni, page);
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly  =true)
	public Infraccion findById(int id) {
		try {
			return repository.findById(id).
					orElseThrow(()->new NoDataServiceException("No Existe un registro con el ID: "+id));
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Infraccion update(Infraccion infraccion) {
		try {
			InfraccionValidator.save(infraccion);
			Infraccion registroD=repository.findByDni(infraccion.getDni());
			if (registroD !=null && registroD.getId()!=infraccion.getId()) {
				throw new ValidateServiceException("ya existe un registro con ese DNI"+ infraccion.getDni());
			}
			
			Infraccion registro=repository.findById(infraccion.getId()).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ infraccion.getId()));
			registro.setDni(infraccion.getDni());
			registro.setFalta(infraccion.getFalta());
			registro.setInfraccion(infraccion.getInfraccion());
			registro.setDescripcion(infraccion.getInfraccion());
			
			repository.save(registro);
			return registro;
			
		} catch (NoDataServiceException|ValidateServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Infraccion save(Infraccion infraccion) {
		try {
			InfraccionValidator.save(infraccion);
			if(repository.findByDni(infraccion.getDni())!=null){
				
				throw new ValidateServiceException("ya existe un registro con el DNI"+infraccion.getDni());
				
			}
			Infraccion registro=repository.save(infraccion);
			return registro;
			
		} catch (NoDataServiceException|ValidateServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) {
		try {
			Infraccion registro =repository.findById(id).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ id));
			repository.delete(registro);
			
		} catch (NoDataServiceException|ValidateServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
		
	}

}
