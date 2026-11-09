package uce.edu.ec.App.filtros.word;

import java.awt.image.BufferedImage;

import uce.edu.ec.App.filtros.FiltroSimple;

public class FiltroTonalidadClara extends FiltroSimple {

	private final String nombre;
	private final int tr;
	private final int tg;
	private final int tb;
	private final double cantidad;

	public FiltroTonalidadClara(String nombre, int tr, int tg, int tb, double cantidad) {
		this.nombre = nombre;
		this.tr = tr;
		this.tg = tg;
		this.tb = tb;
		this.cantidad = cantidad;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	// Word 15-21: tonalidad (tr, tg, tb) aclarada una cantidad dada hacia el blanco.
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

				// primero la tonalidad (limitada a 0-255)
				int pixelTono = OperacionesColor.rgb(gris * tr / 255, gris * tg / 255, gris * tb / 255);
				int rt = (pixelTono >> 16) & mascara;
				int gt = (pixelTono >> 8) & mascara;
				int bt = pixelTono & mascara;

				// luego se aclara hacia el blanco
				int r1 = (int) (rt + (255 - rt) * cantidad);
				int g1 = (int) (gt + (255 - gt) * cantidad);
				int b1 = (int) (bt + (255 - bt) * cantidad);

				resultado.setRGB(x, y, OperacionesColor.rgb(r1, g1, b1));
			}
		}
		return resultado;
	}
}
