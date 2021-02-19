package br.com.michael.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.michael.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.getLinha();
		boolean colunaDiferente = coluna != vizinho.getColuna();
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.getLinha());
		int deltaColuna = Math.abs(coluna - vizinho.getColuna());
		int deltaGeral = deltaColuna + deltaLinha;
		
		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}
	
	void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;
			
			if (minado) {
				throw new ExplosaoException();
			}
			
			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		} else {
			return false;			
		}
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.isMinado());
	}
	
	void minar() {
		minado = true;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		
		return desvendado || protegido;
	}
	
	long minasNaVizinhaca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	void reinicar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	public String toString() {
		if (marcado) {
			return "x";
		} else if (aberto && minado) {
			return "*";
		} else if (aberto && minasNaVizinhaca() > 0) {
			return Long.toString(minasNaVizinhaca());
		} else if (aberto) {
			return "";
		} else {
			return "?";
		}
	}

	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}

	public boolean isMinado() {
		return minado;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
}
