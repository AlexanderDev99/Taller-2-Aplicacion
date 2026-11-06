package uce.edu.ec.App.servicio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

/** Entrada/salida de imagenes. No conoce filtros ni interfaz grafica. */
public class ImagenServicio {

	public BufferedImage cargar(File archivo) throws IOException {
		BufferedImage imagen = ImageIO.read(archivo);
		if (imagen == null) {
			throw new IOException("El archivo no es una imagen valida: " + archivo.getName());
		}
		return imagen;
	}

	/** Guarda una imagen; el formato sale de la extension (png por defecto). */
	public File guardar(BufferedImage imagen, File destino) throws IOException {
		String nombre = destino.getName().toLowerCase();
		String formato = "png";
		if (nombre.endsWith(".jpg") || nombre.endsWith(".jpeg")) {
			formato = "jpg";
		} else if (nombre.endsWith(".bmp")) {
			formato = "bmp";
		} else if (!nombre.endsWith(".png")) {
			destino = new File(destino.getParentFile(), destino.getName() + ".png");
		}

		BufferedImage aGuardar = imagen;
		// JPG y BMP no soportan transparencia: se compone sobre fondo blanco
		if (!formato.equals("png") && imagen.getColorModel().hasAlpha()) {
			aGuardar = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = aGuardar.createGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, aGuardar.getWidth(), aGuardar.getHeight());
			g.drawImage(imagen, 0, 0, null);
			g.dispose();
		}

		if (!ImageIO.write(aGuardar, formato, destino)) {
			throw new IOException("No se pudo guardar en formato " + formato);
		}
		return destino;
	}

	/** Guarda varias imagenes en una carpeta como prefijo1.png, prefijo2.png, ... */
	public void guardarSecuencia(List<BufferedImage> imagenes, File carpeta, String prefijo) throws IOException {
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		for (int i = 0; i < imagenes.size(); i++) {
			guardar(imagenes.get(i), new File(carpeta, prefijo + (i + 1) + ".png"));
		}
	}
}
