
package uce.edu.ec.App.filtros.matriz;

public final class Matrices {

    // 1. MATRICES RGB
	
    // Escala de grises RGB
    public static final float[][] matrizGrisesRGB = {
        {0.299f, 0.587f, 0.114f, 0},
        {0.299f, 0.587f, 0.114f, 0},
        {0.299f, 0.587f, 0.114f, 0}
    };

    // Sepia RGB
    public static final float[][] matrizSepiaRGB = {
        {0.393f, 0.769f, 0.189f, 0},
        {0.349f, 0.686f, 0.168f, 0},
        {0.272f, 0.534f, 0.131f, 0}
    };

    
    // 2. MATRICES RGB CON OFFSET DE BRILLO
    
    public static final float nivelBrillo50 = 255 * 0.5f;

    // Escala de grises con brillo
    public static final float[][] matrizGrisesRGBBrillo = {
        {0.299f, 0.587f, 0.114f, nivelBrillo50},
        {0.299f, 0.587f, 0.114f, nivelBrillo50},
        {0.299f, 0.587f, 0.114f, nivelBrillo50}
    };

    // Sepia con brillo
    public static final float[][] matrizSepiaRGBBrillo = {
        {0.393f, 0.769f, 0.189f, nivelBrillo50},
        {0.349f, 0.686f, 0.168f, nivelBrillo50},
        {0.272f, 0.534f, 0.131f, nivelBrillo50}
    };

    // 3. MATRICES RGBA
    
    // Escala de grises RGBA
    public static final float[][] matrizGrisesRGBA = {
        {0.299f, 0.587f, 0.114f, 0, 0},
        {0.299f, 0.587f, 0.114f, 0, 0},
        {0.299f, 0.587f, 0.114f, 0, 0},
        {0, 0, 0, 1, 0}
    };

    // Sepia RGBA
    public static final float[][] matrizSepiaRGBA = {
        {0.393f, 0.769f, 0.189f, 0, 0},
        {0.349f, 0.686f, 0.168f, 0, 0},
        {0.272f, 0.534f, 0.131f, 0, 0},
        {0, 0, 0, 1, 0}
    };
    

    // 4. MATRICES RGBA CON OFFSET DE BRILLO
    
    // Escala de grises con brillo
    public static final float[][] matrizGrisesRGBABrillo = {
        {0.299f, 0.587f, 0.114f, 0, nivelBrillo50},
        {0.299f, 0.587f, 0.114f, 0, nivelBrillo50},
        {0.299f, 0.587f, 0.114f, 0, nivelBrillo50},
        {0, 0, 0, 1, 0}
    };

    // Sepia con brillo
    public static final float[][] matrizSepiaRGBABrillo = {
        {0.393f, 0.769f, 0.189f, 0, nivelBrillo50},
        {0.349f, 0.686f, 0.168f, 0, nivelBrillo50},
        {0.272f, 0.534f, 0.131f, 0, nivelBrillo50},
        {0, 0, 0, 1, 0}
    };


    // 5. REDUCIR ALPHA AL 50%
    public static final float[][] matrizAlpha50 = {
        {1, 0, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0.50f, 0}
    };


    // 6. ALPHA AL 40% Y BRILLO AL 30%

    public static final float nivelBrillo30 = 255 * 0.30f;

    public static final float[][] matrizAlpha40Brillo30 = {
        {1, 0, 0, 0, nivelBrillo30},
        {0, 1, 0, 0, nivelBrillo30},
        {0, 0, 1, 0, nivelBrillo30},
        {0, 0, 0, 0.40f, 0}
    };

}
