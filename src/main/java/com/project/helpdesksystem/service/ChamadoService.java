package com.project.helpdesksystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.project.helpdesksystem.domain.Chamado;
import com.project.helpdesksystem.domain.Cliente;
import com.project.helpdesksystem.domain.Tecnico;
import com.project.helpdesksystem.dtos.ChamadoDTO;
import com.project.helpdesksystem.enums.Prioridade;
import com.project.helpdesksystem.enums.Status;
import com.project.helpdesksystem.handleException.ObjectNotFoundExceptionValues;
import com.project.helpdesksystem.repository.ChamadoRepository;

@Service
public class ChamadoService {

	private ChamadoRepository chamadoRepository;
	private TecnicoService tecnicoService;
	private ClienteService clienteService;

	public ChamadoService(ChamadoRepository chamadoRepository, TecnicoService tecnicoService,
			ClienteService clienteService) {
		this.chamadoRepository = chamadoRepository;
		this.tecnicoService = tecnicoService;
		this.clienteService = clienteService;
	}

	public Chamado findById(Integer id) {
		Optional<Chamado> chamadoFind = chamadoRepository.findById(id);
		return chamadoFind.orElseThrow(
				() -> new ObjectNotFoundExceptionValues("Chamado não de id : " + id + " não foi encontrado"));
	}

	public List<Chamado> findAllData() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO chamadoDto) {
		return chamadoRepository.save(newChamado(chamadoDto));
	}

	public Chamado update(Integer id, ChamadoDTO chamadoDto) {
		chamadoDto.setId(id);
		Chamado chamado = findById(id);
		chamado = newChamado(chamadoDto);
		return chamadoRepository.save(chamado);
	}

	private Chamado newChamado(ChamadoDTO chamDto) {
		Tecnico tecnico = tecnicoService.findById(chamDto.getTecnico());
		Cliente cliente = clienteService.findById(chamDto.getCliente());

		Chamado chamado = new Chamado();
		if (chamDto.getId() != null) {
			chamado.setId(chamDto.getId());
		}

		if (chamDto.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamDto.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamDto.getStatus()));
		chamado.setTitulo(chamDto.getTitulo());
		chamado.setObservacoes(chamDto.getObservacoes());

		return chamado;

	}
}
