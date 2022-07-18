package com.project.helpdesksystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.project.helpdesksystem.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
  
	@Autowired
	private DBService dbService;

	@Bean
	public void instanciaDb() {
		this.dbService.intanciaDB();
	}
}
