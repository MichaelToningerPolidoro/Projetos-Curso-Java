package br.com.michael.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.michael.cm.excecao.ExplosaoException;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minasExigidas;
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minasExigidas = minas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream()
				.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
				.findFirst()
				.ifPresent(c -> c.abrir());
		} catch (ExplosaoException e) {
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream()
			.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(c -> c.alternarMarcacao());
	}

	private void gerarCampos() {
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				campos.add(new Campo(i, j));
			}
		}
	}
	
	private void associarVizinhos() {
		for (Campo c1: campos) {
			for(Campo c2: campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}
	
	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = campo -> campo.isMinado();
		
		do {
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(minado).count();
			
		} while (minasArmadas < minasExigidas);
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.parallelStream().forEach(c -> c.reinicar());
		sortearMinas();
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		int i = 0;
		
		// Apresenta N colunas
		stringBuilder.append("  |");
		for (int c = 0; c < colunas; c++) {
			stringBuilder.append(" " + c);
		}
		
		// Linha divisória campo/apresentacao colunas
		stringBuilder.append("\n---");
		for (int c = 0; c < colunas; c++) {
			stringBuilder.append("--");
		}
		stringBuilder.append("\n");
		
		for (int l = 0; l < linhas; l++) {
			//Apresentacao N linhas
			stringBuilder.append(l + " |");
			for (int c = 0; c < colunas; c++) {
				stringBuilder.append(" ");
				stringBuilder.append(campos.get(i));
				i++;
			}
			
			stringBuilder.append("\n");
		}
		
		return stringBuilder.toString();
	}
}
