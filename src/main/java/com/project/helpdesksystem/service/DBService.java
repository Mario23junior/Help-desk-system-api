package com.project.helpdesksystem.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.helpdesksystem.domain.Chamado;
import com.project.helpdesksystem.domain.Cliente;
import com.project.helpdesksystem.domain.Tecnico;
import com.project.helpdesksystem.enums.Perfil;
import com.project.helpdesksystem.enums.Prioridade;
import com.project.helpdesksystem.enums.Status;
import com.project.helpdesksystem.repository.ChamadoRepository;
import com.project.helpdesksystem.repository.ClienteRepository;
import com.project.helpdesksystem.repository.TecnicoRepository;

@Service
public class DBService {
  	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired	
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public void intanciaDB() {
		Tecnico tec1 = new Tecnico(null,"Arnold jose","60504571079","arnandoMagic@gmail.com","123923");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null,"Linux torvals","04813101003","linuxTorns@gmail.com","cf2323pown");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA,Status.ANDAMENTO,"primeiro chamado","Mudança de os", tec1,cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
