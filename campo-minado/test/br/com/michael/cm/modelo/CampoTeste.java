package br.com.michael.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.michael.cm.excecao.ExplosaoException;

class CampoTeste {

	private Campo campo;
	
	@BeforeEach
	private void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1Direita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1Cima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1Baixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasVezes() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.minar();
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos() {
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		Campo campo11 = new Campo(1, 1);
		
		Campo campo12 = new Campo(1, 2);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	@Test
	void testeToStringCondicao1() {
		campo.alternarMarcacao();
		assertEquals("x", campo.toString());
	}
	
	@Test
	void testeToStringCondicao2() {
		campo.minar();
		
		try {			
			campo.abrir();
		} catch (Exception e) {
			assertEquals("*", campo.toString());
		}
	}
	
	@Test
	void testeToStringCondicao3() {
		Campo vizinho11 = new Campo(2, 2);
		vizinho11.minar();
		
		campo.adicionarVizinho(vizinho11);
		campo.abrir();
		
		assertEquals("1", campo.toString());
	}
	
	@Test
	void testeToStringCondicao4() {
		campo.abrir();
		assertEquals("", campo.toString());
	}
	
	@Test
	void testeToStringCondicao5() {
		assertEquals("?", campo.toString());
	}
	
	@Test
	void testeMetodoReinicar() {
		campo.reinicar();
		boolean resultado = campo.isFechado() && 
				!campo.isMarcado() && !campo.isMinado();
		
		assertTrue(resultado);
	}
	
	@Test
	void testeObjetivoAlcancado1() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivoAlcancado2() {
		campo.minar();
		campo.alternarMarcacao();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivoAlcancado3() {
		campo.minar();
		assertFalse(campo.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivoAlcancado4() {
		campo.alternarMarcacao();
		assertFalse(campo.objetivoAlcancado());
	}
}
