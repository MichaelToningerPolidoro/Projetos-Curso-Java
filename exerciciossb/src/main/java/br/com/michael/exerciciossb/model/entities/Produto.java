package br.com.michael.exerciciossb.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	private float preco;
	private float desconto;
	
	public Produto(String nome, float preco, float desconto) {
		setNome(nome);
		setPreco(preco);
		setDesconto(desconto);
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
		if (preco > 0f) {
			this.preco = preco;			
		}
	}
	
	public float getDesconto() {
		return desconto;
	}
	
	public void setDesconto(float desconto) {
		if (desconto >= 0f && desconto <= 1f) {
			this.desconto = desconto;			
		}
	}
}
