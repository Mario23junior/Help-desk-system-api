package com.project.helpdesksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.helpdesksystem.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
