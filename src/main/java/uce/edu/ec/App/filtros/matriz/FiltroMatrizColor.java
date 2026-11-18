package uce.edu.ec.App.filtros.matriz;

import java.awt.image.BufferedImage;

import uce.edu.ec.App.filtros.FiltroSimple;

//Filtros con Matrices de Color
public class FiltroMatrizColor extends FiltroSimple {

	private final String nombre;
	private final float[][] matriz;

	public FiltroMatrizColor(String nombre, float[][] matriz) {
		validar(matriz);
		this.nombre = nombre;
		this.matriz = matriz;
	}

	private static void validar(float[][] matriz) {
		if (matriz.length != 3 && matriz.length != 4) {
			throw new IllegalArgumentException("La matriz debe tener 3 filas (RGB) o 4 filas (RGBA)");
		}
		for (float[] fila : matriz) {
			if (fila.length != 4 && fila.length != 5) {
				throw new IllegalArgumentException("Cada fila debe tener 4 o 5 valores");
			}
		}
		if (matriz.length == 4 && matriz[3].length != 5) {
			throw new IllegalArgumentException("La fila de alpha debe tener 5 valores");
		}
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();

		// imagen con transparencia
		BufferedImage imagen2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				int pixel = imagen.getRGB(x, y);

				// manipular los pixeles
				int r = (pixel >> 16) & mascara;
				int g = (pixel >> 8) & mascara;
				int b = pixel & mascara;
				int a = (pixel >>> 24) & mascara;

				int r1 = canal(matriz[0], r, g, b, a);
				int g1 = canal(matriz[1], r, g, b, a);
				int b1 = canal(matriz[2], r, g, b, a);

				// alpha
				int a1;
				if (matriz.length == 4) {
					a1 = clamping(Math.round(r * matriz[3][0] + g * matriz[3][1] + b * matriz[3][2]
							+ a * matriz[3][3] + matriz[3][4]));
				} else {
					// Las matrices RGB conservan la transparencia original
					a1 = a;
				}

				int pixel2 = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
				imagen2.setRGB(x, y, pixel2);
			}
		}
		return imagen2;
	}

	// Calculamos un canal de salida con su fila de la matriz, tanto para filas de 4 como de 5 valores. 
	private static int canal(float[] fila, int r, int g, int b, int a) {
		return clamping(Math.round(r * fila[0] + g * fila[1] + b * fila[2] + (fila.length == 5 ? a * fila[3] + fila[4] : fila[3])));
	}

	private static int clamping(int val) {
		return Math.max(0, Math.min(255, val));
	}
}
