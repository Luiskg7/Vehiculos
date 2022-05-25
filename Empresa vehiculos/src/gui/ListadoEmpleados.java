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

import accesoBD.CategoriaBD;
import accesoBD.EmpleadoBD;
import clases.Categoria;
import clases.Empleado;
import exceptions.Ape1_no_valido;
import exceptions.Ape2_no_valido;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Dni_no_valido;
import exceptions.Localidad_no_valida;
import exceptions.Longitud_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;

import javax.swing.JTable;
import java.awt.Toolkit;

public class ListadoEmpleados extends JFrame {

	private JPanel contentPane;
	private JTable tabla;

	/**
	 * Create the frame.
	 * @throws Codigo_no_valido 
	 * @throws Opcion_no_valida 
	 * @throws Provincia_no_valida 
	 * @throws Localidad_no_valida 
	 * @throws Descripcion_no_valida 
	 * @throws Ape2_no_valido 
	 * @throws Ape1_no_valido 
	 * @throws Dni_no_valido 
	 * @throws Longitud_no_valida 
	 * @throws SQLException 
	 */
	public ListadoEmpleados() throws SQLException, Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoEmpleados.class.getResource("/general/png/logo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 743, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Ape1");
		modelo.addColumn("Ape2");
		modelo.addColumn("Dni");
		modelo.addColumn("fecha_nac");
		modelo.addColumn("fecha_alta");
		modelo.addColumn("Oficina_trab");
		
		
		
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		add(new JScrollPane(tabla));	
		contentPane.add(tabla, BorderLayout.CENTER);
		
		
		ArrayList<Empleado> empleados=new ArrayList<Empleado>();
		empleados=EmpleadoBD.listaEmpleados();
		
		for(Empleado empleado:empleados) {
			modelo.addRow(new Object[] {empleado.getNombre(),empleado.getApe1(),empleado.getApe2(),empleado.getDni(),empleado.getFecha_nac2(),empleado.getFecha_alta2(),empleado.getOficina_trab()});
		}
			
		MetodosGui.centraVentana(this);
		
	}

}
