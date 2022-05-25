package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import accesoBD.Conexion;
import accesoBD.OficinaBD;
import clases.Oficina;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Localidad_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;

import javax.swing.JTable;
import java.awt.Toolkit;

public class ListadoOficinas extends JFrame {

	private JPanel contentPane;
	private JTable tabla;

	/**
	 * Create the frame.
	 * @throws Codigo_no_valido 
	 * @throws Opcion_no_valida 
	 * @throws Provincia_no_valida 
	 * @throws Localidad_no_valida 
	 * @throws Descripcion_no_valida 
	 * @throws SQLException 
	 */
	public ListadoOficinas() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoOficinas.class.getResource("/general/png/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 691, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		String[] columnas= {"Código","Descripción","Localidad","Provincia","Aeropuerto"};
		DefaultTableModel modelo = new DefaultTableModel(null,columnas);
		
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		add(new JScrollPane(tabla));	
		contentPane.add(tabla, BorderLayout.CENTER);
		
		
		ArrayList<Oficina> oficinas=new ArrayList<Oficina>();
		oficinas=OficinaBD.listaOficinas();
		
		for(Oficina oficina:oficinas) {
			modelo.addRow(new Object[] {oficina.getCodigo(),oficina.getDescripcion(),oficina.getLocalidad(),oficina.getProvincia(),oficina.getAeropuerto()});
		}
			
		MetodosGui.centraVentana(this);
		
		
	}

}
