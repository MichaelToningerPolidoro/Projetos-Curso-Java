package br.com.michael.cm.modelo;

@FunctionalInterface
public interface ObservadorCampo {

	public void eventoOcorreu(Campo campo, EventoCampo eventoCampo);
}
