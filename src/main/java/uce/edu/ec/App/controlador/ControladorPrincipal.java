package uce.edu.ec.App.controlador;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import uce.edu.ec.App.filtros.FiltroAjustable;
import uce.edu.ec.App.filtros.FiltroImagen;
import uce.edu.ec.App.servicio.ImagenServicio;
import uce.edu.ec.App.vista.VentanaPrincipal;

/** Coordina la vista, el servicio de imagenes y los filtros. */
public class ControladorPrincipal {

	private final VentanaPrincipal vista;
	private final ImagenServicio servicio;

	private BufferedImage original;
	private File archivoOriginal;
	private List<BufferedImage> resultados = new ArrayList<>();
	private FiltroImagen ultimoFiltro;

	public ControladorPrincipal(VentanaPrincipal vista, ImagenServicio servicio, List<FiltroImagen> filtros) {
		this.vista = vista;
		this.servicio = servicio;

		vista.setFiltros(filtros);
		vista.onCargar(this::cargarImagen);
		vista.onAplicar(this::aplicarFiltro);
		vista.onGuardar(this::guardarResultado);
	}

	private void cargarImagen() {
		File archivo = vista.pedirArchivoImagen();
		if (archivo == null) {
			return;
		}
		try {
			original = servicio.cargar(archivo);
			archivoOriginal = archivo;
			resultados = new ArrayList<>();
			vista.setImagenOriginal(original);
			vista.setEstado("Imagen cargada: " + archivo.getName() + " (" + original.getWidth() + " x "
					+ original.getHeight() + ")");
		} catch (Exception e) {
			vista.mostrarError("No se pudo cargar la imagen: " + e.getMessage());
		}
	}

	private void aplicarFiltro() {
		final FiltroImagen filtro = vista.getFiltroSeleccionado();
		if (original == null || filtro == null) {
			return;
		}

		// Si el filtro tiene parametro, se toma el valor del deslizador
		final String detalle;
		if (filtro instanceof FiltroAjustable) {
			FiltroAjustable ajustable = (FiltroAjustable) filtro;
			int valor = vista.getValorAjuste();
			ajustable.setValor(valor);
			detalle = " (" + ajustable.getEtiquetaParametro() + ": " + valor + ")";
		} else {
			detalle = "";
		}

		vista.setOcupado(true);
		vista.setEstado("Aplicando filtro: " + filtro.getNombre() + detalle + "...");

		// Se ejecuta fuera del hilo de la interfaz para que la ventana no se congele
		new SwingWorker<List<BufferedImage>, Void>() {
			@Override
			protected List<BufferedImage> doInBackground() {
				return filtro.aplicar(original);
			}

			@Override
			protected void done() {
				vista.setOcupado(false);
				try {
					resultados = get();
					ultimoFiltro = filtro;
					vista.setResultados(resultados);
					vista.setEstado("Filtro aplicado: " + filtro.getNombre() + detalle);
				} catch (InterruptedException | ExecutionException e) {
					vista.setEstado("Error al aplicar el filtro");
					vista.mostrarError("Error al aplicar el filtro: " + e.getMessage());
				}
			}
		}.execute();
	}

	private void guardarResultado() {
		if (resultados.isEmpty()) {
			return;
		}
		try {
			if (resultados.size() == 1) {
				File destino = vista.pedirArchivoDestino(nombreBase() + "_resultado.png");
				if (destino == null) {
					return;
				}
				File guardado = servicio.guardar(resultados.get(0), destino);
				vista.setEstado("Guardado en: " + guardado.getAbsolutePath());
			} else {
				File carpeta = vista.pedirCarpetaDestino();
				if (carpeta == null) {
					return;
				}
				servicio.guardarSecuencia(resultados, carpeta, "secuencia");
				vista.setEstado("Secuencia guardada en: " + carpeta.getAbsolutePath());
			}
			vista.mostrarInfo("Imagen(es) guardada(s) correctamente. Filtro: " + ultimoFiltro.getNombre());
		} catch (Exception e) {
			vista.mostrarError("No se pudo guardar: " + e.getMessage());
		}
	}

	private String nombreBase() {
		String nombre = archivoOriginal != null ? archivoOriginal.getName() : "imagen";
		int punto = nombre.lastIndexOf('.');
		return punto > 0 ? nombre.substring(0, punto) : nombre;
	}
}
