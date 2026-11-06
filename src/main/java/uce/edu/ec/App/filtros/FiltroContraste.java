package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;

/**
 * Filtro de contraste (logica de imagenContraste de la clase).
 * Suma 'nivel' a cada canal: positivo sube (Math.min(c + nivel, 255)),
 * negativo baja (Math.max(c + nivel, 0)).
 */
public class FiltroContraste extends FiltroSimple implements FiltroAjustable {

	private volatile int nivel = 0;

	@Override
	public String getNombre() {
		return "Contraste (subir o bajar)";
	}

	@Override
	public String getEtiquetaParametro() {
		return "Nivel de contraste";
	}

	@Override
	public int getMinimo() {
		return -255;
	}

	@Override
	public int getMaximo() {
		return 255;
	}

	@Override
	public int getValorInicial() {
		return 0;
	}

	@Override
	public void setValor(int valor) {
		this.nivel = valor;
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		BufferedImage imagenContraste = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		int nivelActual = nivel;

		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				int pixel = imagen.getRGB(x, y);

				// manipular los pixeles
				int r = (pixel >> 16) & mascara;
				int g = (pixel >> 8) & mascara;
				int b = pixel & mascara;

				// subir contraste: Math.min(c + nivel, 255)
				// bajar contraste: Math.max(c + nivel, 0)
				int r1 = Math.max(Math.min(r + nivelActual, 255), 0);
				int g1 = Math.max(Math.min(g + nivelActual, 255), 0);
				int b1 = Math.max(Math.min(b + nivelActual, 255), 0);

				int pixel2 = (r1 << 16) | (g1 << 8) | b1;
				imagenContraste.setRGB(x, y, pixel2);
			}
		}
		return imagenContraste;
	}
}
