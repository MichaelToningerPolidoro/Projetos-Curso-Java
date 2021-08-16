package br.com.michael.exerciciossb.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String nome;
	
	@Min(0)
	private float preco;
	
	@Min(0)
	@Max(1)
	private float desconto;
	
	public Produto() {}
	
	public Produto(String nome, float preco, float desconto) {
		setNome(nome);
		setPreco(preco);
		setDesconto(desconto);
	}
	
	public Produto(int id, String nome, float preco, float desconto) {
		this(nome, preco, desconto);
		setId(id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;	
	}
	
	public float getDesconto() {
		return desconto;
	}
	
	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}
}
