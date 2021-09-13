package br.com.michael.exerciciossb.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.michael.exerciciossb.model.entities.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

	public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);
}
