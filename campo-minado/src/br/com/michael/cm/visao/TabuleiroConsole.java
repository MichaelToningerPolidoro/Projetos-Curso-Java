package br.com.michael.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.michael.cm.excecao.ExplosaoException;
import br.com.michael.cm.excecao.SairJogoException;
import br.com.michael.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner scanner = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida ? (S/n) ");
				String resposta = scanner.nextLine().trim();
				
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
			
		} catch (SairJogoException e) {
			System.out.println("Até mais!!");
		} finally {
			scanner.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				String digitado = capturarValorDigitado("Digite (linha, coluna): ");
				
				Iterator<Integer> linhaColuna = Arrays.stream(digitado.split(","))
					.map(e -> Integer.parseInt(e.trim()))
					.iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				int linha = linhaColuna.next();
				int coluna = linhaColuna.next();
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(linha, coluna);
				} else if ("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(linha, coluna);
				}
			}
			
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String valorDigitado = scanner.nextLine().trim();
		
		if ("sair".equalsIgnoreCase(valorDigitado)) {
			throw new SairJogoException();
		}
		
		return valorDigitado;
	}
}
