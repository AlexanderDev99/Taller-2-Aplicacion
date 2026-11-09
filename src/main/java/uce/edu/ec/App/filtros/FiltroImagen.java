package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;
import java.util.List;


public interface FiltroImagen {

	String getNombre();
	
	List<BufferedImage> aplicar(BufferedImage imagen);
}
