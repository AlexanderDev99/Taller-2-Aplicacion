package uce.edu.ec.App.filtros.word;

/** Metodos auxiliares de color compartidos por los filtros Word. */
public final class OperacionesColor {

	private OperacionesColor() {
	}

	/** Arma el pixel limitando cada canal al rango 0-255. */
	public static int rgb(int r, int g, int b) {
		r = Math.max(0, Math.min(r, 255));
		g = Math.max(0, Math.min(g, 255));
		b = Math.max(0, Math.min(b, 255));

		return (r << 16) | (g << 8) | b;
	}

	/** Gris ponderado segun la sensibilidad del ojo humano. */
	public static int luminosidad(int r, int g, int b) {
		return (int) (0.299 * r + 0.587 * g + 0.114 * b);
	}
}
