package uce.edu.ec.App.filtros;

import java.util.ArrayList;
import java.util.List;

import uce.edu.ec.App.filtros.word.FiltrosWord;

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
		
		//tarea 3 contraste 
		filtros.add(new FiltroContraste());
		
		// Tarea 3 - reductor de niveles de color (N parametrizable)
		filtros.add(new FiltroReductorNivelesColor());
		
		// Tarea 3 - reductor de bits
		filtros.add(new FiltroReduccionBits()); 
		
		return filtros;
	}
}
