package uce.edu.ec.App.filtros.word;

import java.awt.image.BufferedImage;

import uce.edu.ec.App.filtros.FiltroSimple;

/** Word 03: tono sepia. */
public class FiltroSepia extends FiltroSimple {

	@Override
	public String getNombre() {
		return "Word 03 - Sepia";
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				int pixel = imagen.getRGB(x, y);

				// manipular los pixeles
				int r = (pixel >> 16) & mascara;
				int g = (pixel >> 8) & mascara;
				int b = pixel & mascara;

				// generamos el color sepia
				int r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
				int g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
				int b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

				resultado.setRGB(x, y, OperacionesColor.rgb(r1, g1, b1));
			}
		}
		return resultado;
	}
}
