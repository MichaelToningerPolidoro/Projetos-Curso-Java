package br.com.michael.exerciciossb.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.michael.exerciciossb.model.entities.Produto;
import br.com.michael.exerciciossb.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private static final String PATH_ID = "/{id}";
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Produto novoProduto(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
	
	@GetMapping
	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping(path="/nome/{parteNome}")
	public Iterable<Produto> obterProdutosPorNome(@PathVariable String parteNome) {
		return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
	}
	
	@GetMapping(path = "/pagina/{numeroPagina}/{qtdeItems}")
	public Iterable<Produto> obterProdutosPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdeItems) {
		
		if (qtdeItems <= 0 || qtdeItems > 10) {
			qtdeItems = 10;
		}
		
		Pageable pageable = PageRequest.of(numeroPagina, qtdeItems);
		return produtoRepository.findAll(pageable);
	}
	
	@GetMapping(path = PATH_ID)
	public Optional<Produto> obterProdutoPorId(@PathVariable int id) {
		return produtoRepository.findById(id);
	}
	
	@DeleteMapping(path = PATH_ID)
	public void excluirProduto(@PathVariable int id) {
		produtoRepository.deleteById(id);
	}
}
