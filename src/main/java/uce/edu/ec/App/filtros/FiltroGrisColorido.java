package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;

/** Filtro gris colorido (logica de generarImagenGrisColorida de la clase). */
public class FiltroGrisColorido extends FiltroSimple {

	@Override
	public String getNombre() {
		return "Gris colorido";
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		BufferedImage imagenColorida = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				int pixel = imagen.getRGB(x, y);

				// manipular los pixeles
				int r = (pixel >> 16) & mascara;
				int g = (pixel >> 8) & mascara;
				int b = pixel & mascara;

				// obtenemos los pixeles para la imagen gris
				int gris = (r + g + b) / 3;

				// generamos el color
				r = gris;
				g = gris / 3;
				b = gris / 2;

				int pixel2 = (r << 16) | (g << 8) | b;
				imagenColorida.setRGB(x, y, pixel2);
			}
		}
		return imagenColorida;
	}
}
