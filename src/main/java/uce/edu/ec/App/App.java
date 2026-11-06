package uce.edu.ec.App;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import uce.edu.ec.App.controlador.ControladorPrincipal;
import uce.edu.ec.App.filtros.RegistroFiltros;
import uce.edu.ec.App.servicio.ImagenServicio;
import uce.edu.ec.App.vista.VentanaPrincipal;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				// se usa el look and feel por defecto
			}
			VentanaPrincipal vista = new VentanaPrincipal();
			new ControladorPrincipal(vista, new ImagenServicio(), RegistroFiltros.obtenerFiltros());
			vista.setVisible(true);
		});
	}
}
