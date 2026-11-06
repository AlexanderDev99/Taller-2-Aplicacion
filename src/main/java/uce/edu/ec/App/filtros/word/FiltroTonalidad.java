package uce.edu.ec.App.filtros.word;

import java.awt.image.BufferedImage;

import uce.edu.ec.App.filtros.FiltroSimple;

/** Word 08-14: recolorea la imagen con una tonalidad (tr, tg, tb) a partir del gris. */
public class FiltroTonalidad extends FiltroSimple {

	private final String nombre;
	private final int tr;
	private final int tg;
	private final int tb;

	public FiltroTonalidad(String nombre, int tr, int tg, int tb) {
		this.nombre = nombre;
		this.tr = tr;
		this.tg = tg;
		this.tb = tb;
	}

	@Override
	public String getNombre() {
		return nombre;
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

				int gris = OperacionesColor.luminosidad(r, g, b);

				// generamos el color con la tonalidad
				int r1 = gris * tr / 255;
				int g1 = gris * tg / 255;
				int b1 = gris * tb / 255;

				resultado.setRGB(x, y, OperacionesColor.rgb(r1, g1, b1));
			}
		}
		return resultado;
	}
}
