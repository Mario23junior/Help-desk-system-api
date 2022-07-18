package com.project.helpdesksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.helpdesksystem.domain.Pessoa;

public interface ClienteRepository extends JpaRepository<Pessoa, Integer>{

}
	