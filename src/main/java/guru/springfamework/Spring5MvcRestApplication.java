package guru.springfamework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("guru.springfamework.api.v1.mapper, guru.springfamework.bootstrap, guru.springfamework.controllers.v1, guru.springfamework.domain, guru.springfamework.repositories, guru.springfamework.services ")
public class Spring5MvcRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5MvcRestApplication.class, args);
	}
}
