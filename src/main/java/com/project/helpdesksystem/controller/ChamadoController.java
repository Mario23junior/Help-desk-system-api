package com.project.helpdesksystem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.helpdesksystem.domain.Chamado;
import com.project.helpdesksystem.dtos.ChamadoDTO;
import com.project.helpdesksystem.service.ChamadoService;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

	private ChamadoService chamadoService;

	public ChamadoController(ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ChamadoDTO> findId(@PathVariable Integer id) {
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> list = chamadoService.findAllData();
		List<ChamadoDTO> listDto = list.stream().map(listValues -> new ChamadoDTO(listValues))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDto) {
		Chamado chamado = chamadoService.create(chamadoDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @RequestBody ChamadoDTO chamadoDto) {
		return null;
	}

}
