package com.project.helpdesksystem.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.helpdesksystem.domain.Tecnico;
import com.project.helpdesksystem.repository.TecnicoRepository;

@Service
public class TecnicoService {
    
	private TecnicoRepository tecnicoRepository;
	
	public TecnicoService(TecnicoRepository tecnicoRepository) {
		this.tecnicoRepository = tecnicoRepository;
	}
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> find = tecnicoRepository.findById(id);
		return find.orElse(null);
	}
	
}
