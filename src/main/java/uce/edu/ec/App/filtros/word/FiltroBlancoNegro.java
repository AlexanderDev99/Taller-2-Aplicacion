package uce.edu.ec.App.filtros.word;

import java.awt.image.BufferedImage;

import uce.edu.ec.App.filtros.FiltroSimple;

public class FiltroBlancoNegro extends FiltroSimple {

	private final String nombre;
	private final int umbral;

	public FiltroBlancoNegro(String nombre, int umbral) {
		this.nombre = nombre;
		this.umbral = umbral;
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

				// por encima del umbral blanco, por debajo negro
				int valor = gris >= umbral ? 255 : 0;

				resultado.setRGB(x, y, OperacionesColor.rgb(valor, valor, valor));
			}
		}
		return resultado;
	}
}
