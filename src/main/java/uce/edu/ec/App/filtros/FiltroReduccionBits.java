package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

/**
 * Reduccion de bits por canal
 * Cada canal R, G y B se cuantiza a 2^bits niveles: 8 bits = 256 niveles (sin cambios),
 * 5 bits = 32 niveles (el valor usado en clase), 1 bit = 2 niveles.
 */
public class FiltroReduccionBits extends FiltroSimple implements FiltroAjustable {

	private volatile int bits = 8;

	@Override
	public String getNombre() {
		return "Reduccion de bits";
	}

	@Override
	public List<ParametroFiltro> getParametros() {
		return Collections.singletonList(new ParametroFiltro("Bits por canal", 1, 8, 8));
	}

	@Override
	public void setValores(int[] valores) {
		this.bits = valores[0];
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

		// niveles = 2^bits (por ejemplo 5 bits = 32 niveles)
		int niveles = 1 << bits;

		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {

				int pixel = imagen.getRGB(x, y);

				// manipular los pixeles
				int r = (pixel >> 16) & mascara;
				int g = (pixel >> 8) & mascara;
				int b = pixel & mascara;

				r = cuantizar(r, niveles);
				g = cuantizar(g, niveles);
				b = cuantizar(b, niveles);

				int nuevoPixel = (r << 16) | (g << 8) | b;

				resultado.setRGB(x, y, nuevoPixel);
			}
		}
		return resultado;
	}

	static int cuantizar(int color, int niveles) {

		double escala = (niveles - 1) / 255.0;

		int nivel = (int) Math.round(color * escala);

		return (int) Math.round(nivel * 255.0 / (niveles - 1));
	}
}
