package com.project.helpdesksystem.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.helpdesksystem.dtos.ClienteDTO;
import com.project.helpdesksystem.enums.Perfil;

@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}
	
	public Cliente() {
 		addPerfil(Perfil.CLIENTE);
	}
	
	public Cliente(ClienteDTO clienteDto) {
		super();
		this.id = clienteDto.getId();
		this.nome = clienteDto.getNome();
		this.cpf = clienteDto.getCpf();
		this.email = clienteDto.getEmail();
		this.senha = clienteDto.getSenha();
		this.perfis = clienteDto.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = clienteDto.getDataCriacao();
 		addPerfil(Perfil.CLIENTE);

	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
