package com.E_Tech_Store_Backend.E_Tech_Store_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class ETechStoreBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ETechStoreBackendApplication.class, args);
	}

}
