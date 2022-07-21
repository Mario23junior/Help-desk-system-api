package com.project.helpdesksystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.helpdesksystem.domain.Chamado;
import com.project.helpdesksystem.handleException.ObjectNotFoundExceptionValues;
import com.project.helpdesksystem.repository.ChamadoRepository;

@Service
public class ChamadoService {

	private ChamadoRepository chamadoRepository;

	public ChamadoService(ChamadoRepository chamadoRepository) {
		this.chamadoRepository = chamadoRepository;
	}

	public Chamado findById(Integer id) {
		Optional<Chamado> chamadoFind = chamadoRepository.findById(id);
		return chamadoFind.orElseThrow(
				() -> new ObjectNotFoundExceptionValues("Chamado não de id : " + id + " não foi encontrado"));
	}

	public List<Chamado> findAllData() {
		return chamadoRepository.findAll();
  	}
}
