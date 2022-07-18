package com.project.helpdesksystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.helpdesksystem.domain.Chamado;
import com.project.helpdesksystem.domain.Cliente;
import com.project.helpdesksystem.domain.Tecnico;
import com.project.helpdesksystem.enums.Perfil;
import com.project.helpdesksystem.enums.Prioridade;
import com.project.helpdesksystem.enums.Status;
import com.project.helpdesksystem.repository.ChamadoRepository;
import com.project.helpdesksystem.repository.ClienteRepository;
import com.project.helpdesksystem.repository.PessoaRepository;
import com.project.helpdesksystem.repository.TecnicoRepository;

@SpringBootApplication
public class HelpdesksystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(HelpdesksystemApplication.class, args);
	}
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired	
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(1,"Arnold jose","60504571079","arnandoMagic@gmail.com","123923");
		tec1.addPerfils(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(2,"Linux torvals","04813101003","linuxTorns@gmail.com","cf2323pown");
		
		Chamado c1 = new Chamado(3, Prioridade.MEDIA,Status.ANDAMENTO,"primeiro chamado","Mudança de os", tec1,cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));

  	}
	
}
	