package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Contrato que debe cumplir todo filtro de la aplicacion.
 * Recibe la imagen original y devuelve una o varias imagenes resultantes
 * (por ejemplo, la secuencia de opacidad devuelve varias).
 */
public interface FiltroImagen {

	/** Nombre que se muestra en la interfaz. */
	String getNombre();

	/** Aplica el filtro sin modificar la imagen original. */
	List<BufferedImage> aplicar(BufferedImage imagen);
}
