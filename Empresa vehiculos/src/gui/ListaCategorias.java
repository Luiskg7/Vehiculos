package gui;

import java.awt.BorderLayout;

import gui.ListadoOficinas;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import accesoBD.CategoriaBD;
import accesoBD.Conexion;
import accesoBD.OficinaBD;
import clases.Categoria;
import clases.Oficina;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Localidad_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;
import exceptions.Recargo_no_valido;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListaCategorias extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JTextField textBusca;

	

	/**
	 * Create the frame.
	 * @throws Codigo_no_valido 
	 * @throws Opcion_no_valida 
	 * @throws Provincia_no_valida 
	 * @throws Localidad_no_valida 
	 * @throws Descripcion_no_valida 
	 * @throws SQLException 
	 * @throws Recargo_no_valido 
	 */
	public ListaCategorias() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido, Recargo_no_valido {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaCategorias.class.getResource("/general/png/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 571, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Código");
		modelo.addColumn("Descripción");
		modelo.addColumn("Recargo");
		
		
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		//add(new JScrollPane(tabla));	
		contentPane.add(tabla, BorderLayout.CENTER);
		
		textBusca = new JTextField();
		contentPane.add(textBusca, BorderLayout.SOUTH);
		textBusca.setColumns(10);
		
		
		ArrayList<Categoria> categorias=new ArrayList<Categoria>();
		categorias=CategoriaBD.listaCategorias();
		
		for(Categoria categoria:categorias) {
			modelo.addRow(new Object[] {categoria.getCodigo(),categoria.getDescripcion(),categoria.getRecargo()});
		}
			
		MetodosGui.centraVentana(this);
		MetodosGui.SearchInJtable(tabla, textBusca);
		
	}

}
