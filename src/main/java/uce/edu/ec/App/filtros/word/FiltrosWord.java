package uce.edu.ec.App.filtros.word;

import java.util.ArrayList;
import java.util.List;

import uce.edu.ec.App.filtros.FiltroImagen;

/** Agrupa los 21 filtros de la tarea 3 (estilo Word) en el orden del documento. */
public final class FiltrosWord {

	private FiltrosWord() {
	}

	public static List<FiltroImagen> crear() {
		List<FiltroImagen> f = new ArrayList<>();

		// FILA 1
		f.add(new FiltroOriginal());
		f.add(new FiltroEscalaGrises());
		f.add(new FiltroSepia());
		f.add(new FiltroDeslavado());
		f.add(new FiltroBlancoNegro("Word 05 - Blanco y negro (umbral 80)", 80));
		f.add(new FiltroBlancoNegro("Word 06 - Blanco y negro (umbral 128)", 128));
		f.add(new FiltroBlancoNegro("Word 07 - Blanco y negro (umbral 180)", 180));

		// FILA 2 - tonalidades
		f.add(new FiltroTonalidad("Word 08 - Gris oscuro", 110, 110, 110));
		f.add(new FiltroTonalidad("Word 09 - Azul", 50, 110, 220));
		f.add(new FiltroTonalidad("Word 10 - Naranja", 230, 115, 35));
		f.add(new FiltroTonalidad("Word 11 - Gris", 160, 160, 160));
		f.add(new FiltroTonalidad("Word 12 - Amarillo", 245, 205, 45));
		f.add(new FiltroTonalidad("Word 13 - Azul 2", 65, 145, 195));
		f.add(new FiltroTonalidad("Word 14 - Verde", 100, 180, 85));

		// FILA 3 - tonalidades claras
		f.add(new FiltroTonalidadClara("Word 15 - Gris claro", 110, 110, 110, 0.45));
		f.add(new FiltroTonalidadClara("Word 16 - Azul claro", 50, 110, 220, 0.45));
		f.add(new FiltroTonalidadClara("Word 17 - Naranja claro", 230, 115, 35, 0.45));
		f.add(new FiltroTonalidadClara("Word 18 - Gris claro 2", 160, 160, 160, 0.55));
		f.add(new FiltroTonalidadClara("Word 19 - Amarillo claro", 245, 205, 45, 0.45));
		f.add(new FiltroTonalidadClara("Word 20 - Azul claro 2", 65, 145, 195, 0.45));
		f.add(new FiltroTonalidadClara("Word 21 - Verde claro", 100, 180, 85, 0.45));

		return f;
	}
}
