package com.project.helpdesksystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.helpdesksystem.domain.Tecnico;
import com.project.helpdesksystem.dtos.TecnicoDTO;
import com.project.helpdesksystem.exceptions.ObjectNotFoundExceptionValues;
import com.project.helpdesksystem.repository.TecnicoRepository;

@Service
public class TecnicoService {

	private TecnicoRepository tecnicoRepository;

	public TecnicoService(TecnicoRepository tecnicoRepository) {
		this.tecnicoRepository = tecnicoRepository;
	}

	public Tecnico findById(Integer id) {
		Optional<Tecnico> find = tecnicoRepository.findById(id);
		return find.orElseThrow(
				() -> new ObjectNotFoundExceptionValues("O ID " + id + " Não foi encontrado ,nos registros."));
	}

	public List<Tecnico> findAllDataValues() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDto) {
		tecnicoDto.setId(null);
		Tecnico tec = new Tecnico(tecnicoDto);
		return tecnicoRepository.save(tec);
	}

}
