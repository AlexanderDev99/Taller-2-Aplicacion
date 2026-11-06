package uce.edu.ec.App.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;

import uce.edu.ec.App.filtros.FiltroAjustable;
import uce.edu.ec.App.filtros.FiltroImagen;

/** Interfaz grafica. No contiene logica de filtros ni de archivos: solo muestra y notifica. */
public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JButton btnCargar = new JButton("Cargar imagen");
	private final JButton btnAplicar = new JButton("Aplicar filtro");
	private final JButton btnGuardar = new JButton("Guardar resultado");
	private final JComboBox<FiltroImagen> cmbFiltros = new JComboBox<>();
	private final PanelImagen panelOriginal = new PanelImagen();
	private final PanelImagen panelResultado = new PanelImagen();
	private final JSlider sliderResultados = new JSlider(1, 1, 1);
	private final JLabel lblIndice = new JLabel();
	private final JPanel panelNavegacion = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private final JLabel lblEstado = new JLabel(" Cargue una imagen para comenzar.");

	// Deslizador de ajuste: solo visible cuando el filtro elegido es FiltroAjustable
	private final JPanel panelAjuste = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private final JLabel lblAjuste = new JLabel();
	private final JLabel lblValorAjuste = new JLabel();
	private final JSlider sliderAjuste = new JSlider(0, 100, 0);

	private List<BufferedImage> resultados = new ArrayList<>();

	public VentanaPrincipal() {
		super("Taller 2 - Filtros de imagen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Barra superior
		cmbFiltros.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean sel,
					boolean focus) {
				super.getListCellRendererComponent(list, value, index, sel, focus);
				if (value instanceof FiltroImagen) {
					setText(((FiltroImagen) value).getNombre());
				}
				return this;
			}
		});
		JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT));
		barra.add(btnCargar);
		barra.add(new JLabel("Filtro:"));
		barra.add(cmbFiltros);
		barra.add(btnAplicar);
		barra.add(btnGuardar);
		cmbFiltros.addActionListener(e -> actualizarPanelAjuste());

		sliderAjuste.setPaintTicks(true);
		sliderAjuste.setPaintLabels(true);
		sliderAjuste.setPreferredSize(new Dimension(450, 50));
		sliderAjuste.addChangeListener(e -> lblValorAjuste.setText(String.valueOf(sliderAjuste.getValue())));
		lblValorAjuste.setPreferredSize(new Dimension(40, 20));
		panelAjuste.add(lblAjuste);
		panelAjuste.add(sliderAjuste);
		panelAjuste.add(lblValorAjuste);
		panelAjuste.setVisible(false);

		JPanel norte = new JPanel(new BorderLayout());
		norte.add(barra, BorderLayout.NORTH);
		norte.add(panelAjuste, BorderLayout.SOUTH);
		add(norte, BorderLayout.NORTH);

		// Paneles de imagen
		panelOriginal.setBorder(BorderFactory.createTitledBorder("Original"));
		panelResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));

		panelNavegacion.add(new JLabel("Imagen:"));
		sliderResultados.setPreferredSize(new Dimension(250, 40));
		sliderResultados.setPaintTicks(true);
		sliderResultados.setSnapToTicks(true);
		sliderResultados.setMajorTickSpacing(1);
		panelNavegacion.add(sliderResultados);
		panelNavegacion.add(lblIndice);
		panelNavegacion.setVisible(false);
		sliderResultados.addChangeListener(e -> mostrarResultadoActual());

		JPanel contenedorResultado = new JPanel(new BorderLayout());
		contenedorResultado.add(panelResultado, BorderLayout.CENTER);
		contenedorResultado.add(panelNavegacion, BorderLayout.SOUTH);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelOriginal, contenedorResultado);
		split.setResizeWeight(0.5);
		add(split, BorderLayout.CENTER);
		add(lblEstado, BorderLayout.SOUTH);

		btnAplicar.setEnabled(false);
		btnGuardar.setEnabled(false);
	}

	// ---------- Enlaces con el controlador ----------

	public void onCargar(Runnable accion) {
		btnCargar.addActionListener(e -> accion.run());
	}

	public void onAplicar(Runnable accion) {
		btnAplicar.addActionListener(e -> accion.run());
	}

	public void onGuardar(Runnable accion) {
		btnGuardar.addActionListener(e -> accion.run());
	}

	// ---------- Estado de la vista ----------

	public void setFiltros(List<FiltroImagen> filtros) {
		cmbFiltros.setModel(new DefaultComboBoxModel<>(filtros.toArray(new FiltroImagen[0])));
		actualizarPanelAjuste();
	}

	/** Valor actual del deslizador de ajuste. */
	public int getValorAjuste() {
		return sliderAjuste.getValue();
	}

	/** Muestra u oculta el deslizador segun el filtro seleccionado y lo configura. */
	private void actualizarPanelAjuste() {
		FiltroImagen filtro = getFiltroSeleccionado();
		if (filtro instanceof FiltroAjustable) {
			FiltroAjustable ajustable = (FiltroAjustable) filtro;
			int paso = Math.max(1, (ajustable.getMaximo() - ajustable.getMinimo()) / 6);

			sliderAjuste.setMinimum(ajustable.getMinimo());
			sliderAjuste.setMaximum(ajustable.getMaximo());
			sliderAjuste.setMajorTickSpacing(paso);
			sliderAjuste.setLabelTable(sliderAjuste.createStandardLabels(paso, ajustable.getMinimo()));
			sliderAjuste.setValue(ajustable.getValorInicial());

			lblAjuste.setText(ajustable.getEtiquetaParametro() + ":");
			lblValorAjuste.setText(String.valueOf(sliderAjuste.getValue()));
			panelAjuste.setVisible(true);
		} else {
			panelAjuste.setVisible(false);
		}
		panelAjuste.revalidate();
	}

	public FiltroImagen getFiltroSeleccionado() {
		return (FiltroImagen) cmbFiltros.getSelectedItem();
	}

	public void setImagenOriginal(BufferedImage imagen) {
		panelOriginal.setImagen(imagen);
		panelResultado.setImagen(null);
		resultados = new ArrayList<>();
		panelNavegacion.setVisible(false);
		btnAplicar.setEnabled(imagen != null);
		btnGuardar.setEnabled(false);
	}

	public void setResultados(List<BufferedImage> imagenes) {
		this.resultados = imagenes;
		boolean varias = imagenes.size() > 1;
		panelNavegacion.setVisible(varias);
		sliderResultados.setMaximum(Math.max(1, imagenes.size()));
		sliderResultados.setValue(1);
		btnGuardar.setEnabled(!imagenes.isEmpty());
		mostrarResultadoActual();
		panelNavegacion.revalidate();
	}

	private void mostrarResultadoActual() {
		if (resultados.isEmpty()) {
			panelResultado.setImagen(null);
			return;
		}
		int indice = sliderResultados.getValue() - 1;
		panelResultado.setImagen(resultados.get(indice));
		lblIndice.setText((indice + 1) + " / " + resultados.size());
	}

	public void setOcupado(boolean ocupado) {
		btnCargar.setEnabled(!ocupado);
		btnAplicar.setEnabled(!ocupado);
		setCursor(ocupado ? java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR)
				: java.awt.Cursor.getDefaultCursor());
	}

	public void setEstado(String texto) {
		lblEstado.setText(" " + texto);
	}

	// ---------- Dialogos ----------

	public File pedirArchivoImagen() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Imagenes (jpg, png, bmp, gif)", "jpg", "jpeg", "png", "bmp", "gif"));
		return fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ? fc.getSelectedFile() : null;
	}

	public File pedirArchivoDestino(String nombreSugerido) {
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File(nombreSugerido));
		fc.setFileFilter(new FileNameExtensionFilter("PNG / JPG / BMP", "png", "jpg", "jpeg", "bmp"));
		return fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ? fc.getSelectedFile() : null;
	}

	public File pedirCarpetaDestino() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Seleccione la carpeta donde guardar la secuencia");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		return fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ? fc.getSelectedFile() : null;
	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarInfo(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
}
