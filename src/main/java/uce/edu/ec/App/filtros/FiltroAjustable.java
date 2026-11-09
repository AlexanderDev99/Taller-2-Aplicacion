package uce.edu.ec.App.filtros;

import java.util.List;


public interface FiltroAjustable extends FiltroImagen {

	
	List<ParametroFiltro> getParametros();

	
	void setValores(int[] valores);
}
