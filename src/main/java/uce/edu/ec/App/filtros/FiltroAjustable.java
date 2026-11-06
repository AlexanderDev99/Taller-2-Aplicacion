package uce.edu.ec.App.filtros;

/**
 * Filtro que depende de un valor numerico elegido por el usuario con un deslizador.
 * La vista detecta esta interfaz y muestra el deslizador con el rango indicado.
 * Sirve para cualquier filtro futuro con parametro (brillo, umbral, etc.).
 */
public interface FiltroAjustable extends FiltroImagen {

	/** Texto que aparece junto al deslizador. */
	String getEtiquetaParametro();

	int getMinimo();

	int getMaximo();

	/** Valor en el que arranca el deslizador. */
	int getValorInicial();

	/** La vista lo llama con el valor del deslizador justo antes de aplicar el filtro. */
	void setValor(int valor);
}
