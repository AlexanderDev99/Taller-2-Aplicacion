package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * Reductor de niveles de color: un nivel (0-255) por canal R, G y B.
 * 255 deja el canal intacto; valores menores lo reducen proporcionalmente;
 * 0 lo elimina por completo.
 */
public class FiltroReductorNivelesColor extends FiltroSimple implements FiltroAjustable {

	private volatile int nivelR = 255;
	private volatile int nivelG = 255;
	private volatile int nivelB = 255;

	@Override
	public String getNombre() {
		return "Reductor de niveles de color";
	}

	@Override
	public List<ParametroFiltro> getParametros() {
		return Arrays.asList(
				new ParametroFiltro("Nivel de rojo (R)", 0, 255, 255),
				new ParametroFiltro("Nivel de verde (G)", 0, 255, 255),
				new ParametroFiltro("Nivel de azul (B)", 0, 255, 255));
	}

	@Override
	public void setValores(int[] valores) {
		this.nivelR = valores[0];
		this.nivelG = valores[1];
		this.nivelB = valores[2];
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		BufferedImage imagenReducida = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		int nr = nivelR;
		int ng = nivelG;
		int nb = nivelB;

		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				int pixel = imagen.getRGB(x, y);

				// manipular los pixeles
				int r = (pixel >> 16) & mascara;
				int g = (pixel >> 8) & mascara;
				int b = pixel & mascara;

				// reducimos cada canal segun su nivel
				int r1 = r * nr / 255;
				int g1 = g * ng / 255;
				int b1 = b * nb / 255;

				int pixel2 = (r1 << 16) | (g1 << 8) | b1;
				imagenReducida.setRGB(x, y, pixel2);
			}
		}
		return imagenReducida;
	}
}
