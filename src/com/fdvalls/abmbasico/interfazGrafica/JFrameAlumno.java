package com.fdvalls.abmbasico.interfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameAlumno extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre = new JTextField();
	private JTextField textFieldDocumento = new JTextField();
	private JLabel etiquetaNombre = new JLabel("Nombre");
	private JLabel etiquetaDocumento = new JLabel("Documento");
	private JButton botonGuardar = new JButton("Guardar");
	
	private Ventana ventana;

	/**
	 * Create the frame.
	 * 
	 * @param ventana
	 */
	public JFrameAlumno(Ventana ventana) {
		this.ventana = ventana;
		inicializarBotones();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		textFieldNombre.setBounds(94, 26, 86, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		etiquetaNombre.setBounds(27, 29, 46, 14);
		contentPane.add(etiquetaNombre);

		etiquetaDocumento.setBounds(27, 60, 77, 14);
		contentPane.add(etiquetaDocumento);

		textFieldDocumento.setBounds(94, 57, 86, 20);
		contentPane.add(textFieldDocumento);
		textFieldDocumento.setColumns(10);

		botonGuardar.setBounds(72, 185, 89, 23);
		contentPane.add(botonGuardar);
	}

	private void inicializarBotones() {
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String nombre = textFieldNombre.getText();
				if (nombre == null || nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío, es obligatorio");
					error = true;
				}
				
				if (!error) {
				//ventana.crearAlumno(dniMaestro, nombre, documento, genero, edad, mail, fecha);
					setVisible(false);
				}
			}
		});
	}
}
