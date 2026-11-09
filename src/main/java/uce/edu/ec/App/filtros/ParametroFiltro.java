package uce.edu.ec.App.filtros;

// Muestra el parametros ajustable de los filtros que tienen deslizadorres
public final class ParametroFiltro {

	private final String etiqueta;
	private final int minimo;
	private final int maximo;
	private final int valorInicial;

	public ParametroFiltro(String etiqueta, int minimo, int maximo, int valorInicial) {
		this.etiqueta = etiqueta;
		this.minimo = minimo;
		this.maximo = maximo;
		this.valorInicial = valorInicial;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public int getMinimo() {
		return minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public int getValorInicial() {
		return valorInicial;
	}
}
