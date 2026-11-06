package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;

/** Filtro de escala de grises (logica de generarImagenGris de la clase). */
public class FiltroGris extends FiltroSimple {

	@Override
	public String getNombre() {
		return "Imagen en gris";
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		BufferedImage imagenGris = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				int pixel = imagen.getRGB(x, y);

				// manipular los pixeles
				int r = (pixel >> 16) & mascara;
				int g = (pixel >> 8) & mascara;
				int b = pixel & mascara;

				// obtenemos los pixeles para la imagen gris
				int gris = (r + g + b) / 3;

				int pixel2 = (gris << 16) | (gris << 8) | gris;
				imagenGris.setRGB(x, y, pixel2);
			}
		}
		return imagenGris;
	}
}
