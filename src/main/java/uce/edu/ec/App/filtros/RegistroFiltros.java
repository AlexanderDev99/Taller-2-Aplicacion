package uce.edu.ec.App.filtros;

import java.util.ArrayList;
import java.util.List;

import uce.edu.ec.App.filtros.word.FiltrosWord;

/**
 * Lista central de filtros disponibles.
 * PARA AGREGAR UN FILTRO NUEVO: crear su clase y anadir una linea aqui.
 */
public final class RegistroFiltros {

	private RegistroFiltros() {
	}

	public static List<FiltroImagen> obtenerFiltros() {
		List<FiltroImagen> filtros = new ArrayList<>();
		filtros.add(new FiltroGris());
		filtros.add(new FiltroGrisColorido());
		filtros.add(new FiltroSecuenciaOpacidad());
		filtros.add(new FiltroNegativo());
		
		//tarea 3 (21 filtros estilo Word)
		filtros.addAll(FiltrosWord.crear());
		
		//contraste 
		filtros.add(new FiltroContraste());
		
		return filtros;
	}
}
