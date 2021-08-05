package br.com.michael.exerciciossb.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metodos")
public class MetodosHTTPController {

	@GetMapping
	public static String get() {
		return "Requisição GET";
	}
	
	@PostMapping
	public static String post() {
		return "Requisição POST";
	}
	
	@PutMapping
	public static String put() {
		return "Requisição PUT";
	}
	
	@PatchMapping
	public static String patch() {
		return "Requisição PATCH";
	}
	
	@DeleteMapping
	public static String delete() {
		return "Requisição DELETE";
	}
}
