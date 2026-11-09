package uce.edu.ec.App.filtros;

import java.util.ArrayList;
import java.util.List;

import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionBase;
import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionDinamico7x7;
import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionDinamico9x9;
import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionEdgeDetection;
import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionEmboss;
import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionSharpen;
import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionBlur;
import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionBoxBlur;
import uce.edu.ec.App.filtros.convolucion.FiltroConvolucionBase;
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
		
		//Tarea 4 - Convolucion 
		filtros.add(new FiltroConvolucionBase());  
		filtros.add(new FiltroConvolucionDinamico7x7()); 
		filtros.add(new FiltroConvolucionDinamico9x9()); 
		filtros.add(new FiltroConvolucionBlur()); //filtro 1
		filtros.add(new FiltroConvolucionSharpen()); //Filtro 2
		filtros.add(new FiltroConvolucionEdgeDetection()); //Filtro 3
		filtros.add(new FiltroConvolucionEmboss()); // Filtro 4
		filtros.add(new FiltroConvolucionBoxBlur()); //filtro 5
		
		
		return filtros;
	}
}
