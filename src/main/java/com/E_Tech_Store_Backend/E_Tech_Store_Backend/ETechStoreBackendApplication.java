package com.E_Tech_Store_Backend.E_Tech_Store_Backend;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.service.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.unit.DataSize;

import javax.sql.DataSource;


@EnableJpaAuditing
@SpringBootApplication
public class ETechStoreBackendApplication implements CommandLineRunner {
	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(ETechStoreBackendApplication.class, args);
	}

	@Bean
	public MultipartConfigFactory multipartConfigFactory() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(10));
		factory.setMaxRequestSize(DataSize.ofMegabytes(10));
		return factory;
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}


