package com.project.helpdesksystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
