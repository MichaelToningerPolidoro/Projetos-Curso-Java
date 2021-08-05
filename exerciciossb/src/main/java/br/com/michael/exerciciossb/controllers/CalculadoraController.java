package br.com.michael.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

	// /calculadora/somar/10/20
	
	@GetMapping("/somar/{numero1}/{numero2}")
	public static int somar(@PathVariable int numero1, @PathVariable int numero2) {
		return numero1 + numero2;
	}
	
	// /calculadora/subtrair/?a=10&b=20
	
	@GetMapping("/subtrair")
	public static int subtrair(
				@RequestParam(value = "n1") int numero1,
				@RequestParam(value = "n2") int numero2
			) {
		return numero1 - numero2;
	}
}
