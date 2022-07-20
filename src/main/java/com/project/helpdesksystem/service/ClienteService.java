package com.project.helpdesksystem.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.project.helpdesksystem.domain.Cliente;
import com.project.helpdesksystem.domain.Pessoa;
import com.project.helpdesksystem.dtos.ClienteDTO;
import com.project.helpdesksystem.exceptions.DataIntegrityViolationException;
import com.project.helpdesksystem.handleException.ObjectNotFoundExceptionValues;
import com.project.helpdesksystem.repository.ClienteRepository;
import com.project.helpdesksystem.repository.PessoaRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	private PessoaRepository pessoaRepository;

	public ClienteService(ClienteRepository clienteRepository, PessoaRepository pessoaRepository) {
		this.clienteRepository = clienteRepository;
		this.pessoaRepository = pessoaRepository;
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptionValues("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAllDataValues() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO clienteDto) {
		clienteDto.setId(null);
		validaPorCpfEmailConsul(clienteDto);
		Cliente tec = new Cliente(clienteDto);
		return clienteRepository.save(tec);
	}

	public Cliente update(Integer id, @Valid ClienteDTO clienteDto) {
		clienteDto.setId(id);
		Cliente cliente = findById(id);

		validaPorCpfEmailConsul(clienteDto);
		cliente = new Cliente(clienteDto);
		return clienteRepository.save(cliente);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}
		clienteRepository.deleteById(id);
	}

	private void validaPorCpfEmailConsul(ClienteDTO ClienteDto) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(ClienteDto.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != ClienteDto.getId()) {
			throw new DataIntegrityViolationException("CPF já existe cadastros com esté CPF");
		}

		pessoa = pessoaRepository.findByEmail(ClienteDto.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != ClienteDto.getId()) {
			throw new DataIntegrityViolationException("EMAIL já existe cadastros com esté EMAIL");
		}
	}

}
