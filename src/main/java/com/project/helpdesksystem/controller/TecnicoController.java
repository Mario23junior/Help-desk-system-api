package com.project.helpdesksystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.helpdesksystem.domain.Tecnico;
import com.project.helpdesksystem.dtos.TecnicoDTO;
import com.project.helpdesksystem.service.TecnicoService;

@RestController
@RequestMapping("/tecnicos/")
public class TecnicoController {
   
	private TecnicoService service;
	
	public TecnicoController(TecnicoService service) {
		this.service = service;
 	}
	
	@GetMapping("{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico findResult = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(findResult));
	}
}
	