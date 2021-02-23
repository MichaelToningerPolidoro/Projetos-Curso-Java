package br.com.michael.cm;

import br.com.michael.cm.modelo.Tabuleiro;
import br.com.michael.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		
		final int linhas = 7;
		final int colunas = 7;
		final int numeroBombas = 9;
		
		Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, numeroBombas);
		new TabuleiroConsole(tabuleiro);
	}
}
