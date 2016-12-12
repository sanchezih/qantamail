package gui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import basics.Handler;

public abstract class VentanaPlantilla extends JDialog {

	protected Handler handler = new Handler();

	protected JPanel panelNorte = new JPanel();
	protected JPanel panelCentro = new JPanel();
	protected JPanel panelSur = new JPanel();

	protected JPanel panelBotonera = new JPanel();

	public VentanaPlantilla() {
		init();
	}

	public void init() {
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BorderLayout(0, 0));
		panelSur.add(panelBotonera, BorderLayout.EAST);
		setVisible(true);
		setResizable(false);
	}

	protected abstract void definirEventos();

	protected abstract void definirTamanio();

	protected abstract int validarCampos();

}
