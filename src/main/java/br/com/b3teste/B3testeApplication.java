package br.com.b3teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class B3testeApplication {

	public static void main(String[] args) {
		SpringApplication.run(B3testeApplication.class, args);
	}

}
