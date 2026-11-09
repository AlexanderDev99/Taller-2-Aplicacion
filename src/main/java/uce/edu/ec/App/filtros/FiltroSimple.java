package uce.edu.ec.App.filtros;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

public abstract class FiltroSimple implements FiltroImagen {

	protected static final int mascara = 0xFF;

	protected abstract BufferedImage procesar(BufferedImage imagen);

	@Override
	public List<BufferedImage> aplicar(BufferedImage imagen) {
		return Collections.singletonList(procesar(imagen));
	}
}
