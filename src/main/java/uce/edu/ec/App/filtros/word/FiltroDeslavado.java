package uce.edu.ec.App.filtros.word;

import java.awt.image.BufferedImage;

import uce.edu.ec.App.filtros.FiltroSimple;

public class FiltroDeslavado extends FiltroSimple {

	@Override
	public String getNombre() {
		return "Word 04 - Deslavado";
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

				//Word 04: deslavado (acerca cada canal al blanco un 70%).
				int r1 = (int) (r + (255 - r) * 0.70);
				int g1 = (int) (g + (255 - g) * 0.70);
				int b1 = (int) (b + (255 - b) * 0.70);

				resultado.setRGB(x, y, OperacionesColor.rgb(r1, g1, b1));
			}
		}
		return resultado;
	}
}
