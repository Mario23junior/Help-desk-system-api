package com.project.helpdesksystem.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.helpdesksystem.domain.Pessoa;
import com.project.helpdesksystem.domain.Tecnico;
import com.project.helpdesksystem.dtos.TecnicoDTO;
import com.project.helpdesksystem.exceptions.DataIntegrityViolationException;
import com.project.helpdesksystem.handleException.ObjectNotFoundExceptionValues;
import com.project.helpdesksystem.repository.PessoaRepository;
import com.project.helpdesksystem.repository.TecnicoRepository;

@Service
public class TecnicoService {

	private TecnicoRepository tecnicoRepository;
	private PessoaRepository pessoaRepository;
 	private BCryptPasswordEncoder enconder;


	public TecnicoService(TecnicoRepository tecnicoRepository, PessoaRepository pessoaRepository,BCryptPasswordEncoder enconder) {
		this.tecnicoRepository = tecnicoRepository;
		this.pessoaRepository = pessoaRepository;
		this.enconder = enconder;
	}

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptionValues("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAllDataValues() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDto) {
		tecnicoDto.setId(null);
		tecnicoDto.setSenha(enconder.encode(tecnicoDto.getSenha()));

		validaPorCpfEmailConsul(tecnicoDto);
		Tecnico tec = new Tecnico(tecnicoDto);
		return tecnicoRepository.save(tec);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);

		validaPorCpfEmailConsul(objDTO);
		oldObj = new Tecnico(objDTO);
		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}
		tecnicoRepository.deleteById(id);
	}


	private void validaPorCpfEmailConsul(TecnicoDTO tecnicoDto) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDto.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDto.getId()) {
			throw new DataIntegrityViolationException("CPF já existe cadastros com esté CPF");
		}

		pessoa = pessoaRepository.findByEmail(tecnicoDto.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDto.getId()) {
			throw new DataIntegrityViolationException("EMAIL já existe cadastros com esté EMAIL");
		}
	}

}
