package br.com.michael.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.michael.exerciciossb.models.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping("/qualquer")
	public static Cliente obterCliente() {
		return new Cliente(1, "José", "12345678910");
	}
	
	@GetMapping("/{id}")
	public static Cliente obterClientePorId1(@PathVariable int id) {
		return new Cliente(id, "Maria", "12345678910");
	}
	
}
