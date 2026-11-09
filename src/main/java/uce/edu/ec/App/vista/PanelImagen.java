package uce.edu.ec.App.vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

// Panel que dibuja una imagen escalada 
public class PanelImagen extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage imagen;

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagen == null) {
			return;
		}
		double escala = Math.min((double) getWidth() / imagen.getWidth(), (double) getHeight() / imagen.getHeight());
		int w = (int) (imagen.getWidth() * escala);
		int h = (int) (imagen.getHeight() * escala);
		int x = (getWidth() - w) / 2;
		int y = (getHeight() - h) / 2;

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(imagen, x, y, w, h, null);
	}
}
