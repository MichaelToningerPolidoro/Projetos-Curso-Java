package br.com.michael.exerciciossb.model.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.michael.exerciciossb.model.entities.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}
