package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/** Secuencia de imagenes con opacidad decreciente (logica de secuenciaImagenes de la clase). */
public class FiltroSecuenciaOpacidad implements FiltroImagen {

	private static final int mascara = 0xFF;
	private final int numeroImagenes;

	public FiltroSecuenciaOpacidad() {
		this(10); // valor usado en clase
	}

	public FiltroSecuenciaOpacidad(int numeroImagenes) {
		if (numeroImagenes < 2) {
			throw new IllegalArgumentException("Se necesitan al menos 2 imagenes");
		}
		this.numeroImagenes = numeroImagenes;
	}

	@Override
	public String getNombre() {
		return "Secuencia de opacidad (" + numeroImagenes + " imagenes)";
	}

	@Override
	public List<BufferedImage> aplicar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		List<BufferedImage> resultado = new ArrayList<>();

		for (int i = 1; i <= numeroImagenes; i++) {
			BufferedImage secuencia = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

			double opacidad = 0.20;
			double progreso = (double) (i - 1) / (numeroImagenes - 1);
			// Operamos el canal alpha
			int alpha = (int) (255 * (1.0 - progreso * (1.0 - opacidad)));

			for (int x = 0; x < ancho; x++) {
				for (int y = 0; y < alto; y++) {
					int pixel = imagen.getRGB(x, y);

					// operamos pixeles
					int r = (pixel >> 16) & mascara;
					int g = (pixel >> 8) & mascara;
					int b = pixel & mascara;

					int pixel2 = (alpha << 24) | (r << 16) | (g << 8) | b;
					secuencia.setRGB(x, y, pixel2);
				}
			}
			resultado.add(secuencia);
		}
		return resultado;
	}
}
