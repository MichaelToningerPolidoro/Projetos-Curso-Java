package br.com.michael.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeiroController {

	@GetMapping(path = {"/olamundo", "/saudacao"})
	public String hello() {
		return "Hello World!! Vou aprender Spring Boot!!";
	}
}
