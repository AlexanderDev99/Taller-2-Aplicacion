package uce.edu.ec.App.filtros.convolucion;

import java.awt.Color;
import java.awt.image.BufferedImage;

import uce.edu.ec.App.filtros.FiltroSimple;

public class FiltroConvolucionDinamico7x7 extends FiltroSimple{

	@Override
	public String getNombre() {
		
		return "Filtro Convolucion 7x7 Sharpen";
	}

	@Override
	protected BufferedImage procesar(BufferedImage imagen) {
		int ancho = imagen.getWidth();
		int alto = imagen.getHeight();
		
		BufferedImage imagen2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		
		// Matriz de convolución 7x7 sharpen)
		float[] kernel = {
			 0,  0,  0, -1,  0,  0,  0,
			 0,  0, -1, -2, -1,  0,  0,
			 0, -1, -2, -4, -2, -1,  0,
			-1, -2, -4, 55, -4, -2, -1,
			 0, -1, -2, -4, -2, -1,  0,
			 0,  0, -1, -2, -1,  0,  0,
			 0,  0,  0, -1,  0,  0,  0
		};
		
		// Calcular la suma total de todos los elementos del kernel
		float sumaKernel = 0;
		for (float valor : kernel) {
					sumaKernel += valor;
		}
				
		// Si la suma es diferente de 0 (para evitar división por cero en detectores de bordes puros), 
		// normalizamos el kernel dividiendo cada elemento entre la suma total.		
		if (sumaKernel != 0) {
			for (int k = 0; k < kernel.length; k++) {
						kernel[k] /= sumaKernel;
			}
		}
		
		// Cálculo automático del tamaño y del borde
		int tamanoKernel = (int) Math.sqrt(kernel.length);
		int borde = tamanoKernel / 2;
		
		for(int x = borde; x < ancho - borde; x++) {
			for(int y = borde; y < alto - borde; y++) {
				
				float sumaR = 0, sumaG = 0, sumaB = 0;
				int indice = 0;
				
				for(int i = -borde; i <= borde; i++) {
					for(int j = -borde; j <= borde; j++) {
						
						int pixel = imagen.getRGB(x + i, y + j);
						
						Color color = new Color(pixel);
						float r = color.getRed();
						float g = color.getGreen();
						float b = color.getBlue();
						
						sumaR += r * kernel[indice];
						sumaG += g * kernel[indice];
						sumaB += b * kernel[indice];
						
						indice++;
					}
				}
			
				int r1 = (int) Math.min(255, Math.max(0, sumaR));
				int g1 = (int) Math.min(255, Math.max(0, sumaG)); 
				int b1 = (int) Math.min(255, Math.max(0, sumaB)); 
				
				int pixel2 = (r1 << 16) | (g1 << 8) | b1;
				imagen2.setRGB(x, y, pixel2);
			}
		}
		
		return imagen2;
	}	
}
