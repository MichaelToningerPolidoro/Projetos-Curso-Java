package br.com.michael.exerciciossb.model.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.michael.exerciciossb.model.entities.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

	public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);
	
	// mais algumas convenções
	// findByNomeContaining
	// findByNomeContains
	// findByNomeIsContaining
	// findByNomeNotContaining
	
	// findByNomeStartsWith
	// findByNomeEndsWith
	
	@Query("SELECT p FROM Produto p where p.nome LIKE %:nome%")
	public Iterable<Produto> searchByNameLike(@Param("nome") String nome);
}
