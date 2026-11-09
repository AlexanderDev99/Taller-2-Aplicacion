package uce.edu.ec.App.filtros;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class FiltroNegativo extends FiltroSimple{

	@Override
	public String getNombre() {
		return "Imagen en negativo";
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		
		BufferedImage imagenNegativa = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		
		for(int x = 0; x < ancho; x++) {
			for(int y = 0; y < alto; y++) {
				int pixel = imagen.getRGB(x, y);
				Color color = new Color(pixel);
				
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
			
			
				//negativo de una imagen
				
				int r1 = (255 - r); 
				int g1 = (255 - g); 
				int b1 = (255 - b); 
				
				
				int pixel2 = (r1 << 16) | (g1 << 8) | b1;
				imagenNegativa.setRGB(x, y, pixel2);
				
			}
			
		}
		return imagenNegativa;
	}
	

}
