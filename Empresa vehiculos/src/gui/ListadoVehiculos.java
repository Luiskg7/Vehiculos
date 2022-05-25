package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import accesoBD.CategoriaBD;
import accesoBD.ClienteBD;
import accesoBD.Conexion;
import accesoBD.VehiculoBD;
import clases.Categoria;
import clases.Cliente;
import clases.Oficina;
import clases.Vehiculo;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Localidad_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class ListadoVehiculos extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JTextField textBusca;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ListadoVehiculos() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoVehiculos.class.getResource("/general/png/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("matricula");
		modelo.addColumn("marca");
		modelo.addColumn("modelo");
		modelo.addColumn("color");
		modelo.addColumn("fecha_adq");
		modelo.addColumn("kms");
		modelo.addColumn("categoria");
		modelo.addColumn("ubicacion");
		
		
		
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		add(new JScrollPane(tabla));	
		contentPane.add(tabla, BorderLayout.CENTER);
		
		textBusca = new JTextField();
		contentPane.add(textBusca, BorderLayout.SOUTH);
		textBusca.setColumns(10);
		
		
		ArrayList<Vehiculo> vehiculos=new ArrayList<Vehiculo>();
		vehiculos=VehiculoBD.listaVehiculos();
		
		for(Vehiculo vehiculo:vehiculos) {
			modelo.addRow(new Object[] {vehiculo.getMatricula(),vehiculo.getMarca(),vehiculo.getModelo(),vehiculo.getColor(),vehiculo.getFecha_adq2(),vehiculo.getKms(),vehiculo.getCategoria(),vehiculo.getUbicacion()});
		}
			
		MetodosGui.centraVentana(this);
		MetodosGui.SearchInJtable(tabla, textBusca);
		
		
		
		
		
		
	}

}
