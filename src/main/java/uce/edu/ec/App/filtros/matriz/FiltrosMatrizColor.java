package uce.edu.ec.App.filtros.matriz;

import java.util.ArrayList;
import java.util.List;

import uce.edu.ec.App.filtros.FiltroImagen;

//creamos un filtro por cada matriz de color
public final class FiltrosMatrizColor {

	private FiltrosMatrizColor() {
	}

	public static List<FiltroImagen> crear() {
		List<FiltroImagen> f = new ArrayList<>();

		// 1. Matrices RGB
		f.add(new FiltroMatrizColor("Matriz RGB - Escala de grises", Matrices.matrizGrisesRGB));
		f.add(new FiltroMatrizColor("Matriz RGB - Sepia", Matrices.matrizSepiaRGB));

		// 2. Matrices RGB con offset de brillo
		f.add(new FiltroMatrizColor("Matriz RGB - Grises con brillo", Matrices.matrizGrisesRGBBrillo));
		f.add(new FiltroMatrizColor("Matriz RGB - Sepia con brillo", Matrices.matrizSepiaRGBBrillo));

		// 3. Matrices RGBA
		f.add(new FiltroMatrizColor("Matriz RGBA - Escala de grises", Matrices.matrizGrisesRGBA));
		f.add(new FiltroMatrizColor("Matriz RGBA - Sepia", Matrices.matrizSepiaRGBA));

		// 4. Matrices RGBA con offset de brillo
		f.add(new FiltroMatrizColor("Matriz RGBA - Grises con brillo", Matrices.matrizGrisesRGBABrillo));
		f.add(new FiltroMatrizColor("Matriz RGBA - Sepia con brillo", Matrices.matrizSepiaRGBABrillo));

		// 5. Alpha al 50%
		f.add(new FiltroMatrizColor("Matriz RGBA - Alpha 50%", Matrices.matrizAlpha50));

		// 6. Alpha al 40% y brillo al 30%
		f.add(new FiltroMatrizColor("Matriz RGBA - Alpha 40% y brillo 30%", Matrices.matrizAlpha40Brillo30));

		return f;
	}
}
