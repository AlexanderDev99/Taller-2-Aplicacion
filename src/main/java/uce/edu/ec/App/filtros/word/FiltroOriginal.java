package uce.edu.ec.App.filtros.word;

import java.awt.image.BufferedImage;

import uce.edu.ec.App.filtros.FiltroSimple;

/** Word 01: devuelve la imagen sin cambios. */
public class FiltroOriginal extends FiltroSimple {

	@Override
	public String getNombre() {
		return "Word 01 - Original";
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

				resultado.setRGB(x, y, OperacionesColor.rgb(r, g, b));
			}
		}
		return resultado;
	}
}
