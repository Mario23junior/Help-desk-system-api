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

import com.project.helpdesksystem.domain.Tecnico;
import com.project.helpdesksystem.dtos.TecnicoDTO;
import com.project.helpdesksystem.service.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

	private TecnicoService service;

	public TecnicoController(TecnicoService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico findResult = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(findResult));
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAllData() {
		List<Tecnico> list = service.findAllDataValues();
		List<TecnicoDTO> listDto = list.stream().map(dtoCon -> new TecnicoDTO(dtoCon)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@RequestBody @Valid TecnicoDTO tecnicoDto) {
		Tecnico tecn = service.create(tecnicoDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecn.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
    
	@PutMapping("/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
}










