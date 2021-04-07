package com.jonas.config;


import com.jonas.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author Jonas, created 27/02/2021
 */
@Configuration
@Profile("dev")
public class DevConfig {

    //Summon class Repository for add H2
    @Autowired
	private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

    @Bean
	public boolean instanciaBaseDeDados() {
		if(strategy.equals("create")) {
			this.dbService.instanciaBaseDeDados();
		}
		return false;
	}
}
