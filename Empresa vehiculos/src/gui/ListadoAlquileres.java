package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import accesoBD.AlquilerBD;
import accesoBD.VehiculoBD;
import clases.Alquiler;
import clases.Vehiculo;
import exceptions.Ape1_no_valido;
import exceptions.Ape2_no_valido;
import exceptions.Carnet_no_valido;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Dni_no_valido;
import exceptions.Localidad_no_valida;
import exceptions.Longitud_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;
import exceptions.Tarjeta_no_valida;
import java.awt.Toolkit;

public class ListadoAlquileres extends JFrame {

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
	 * @throws Tarjeta_no_valida 
	 * @throws Carnet_no_valido 
	 * @throws Ape2_no_valido 
	 * @throws Ape1_no_valido 
	 * @throws Dni_no_valido 
	 * @throws Longitud_no_valida 
	 */
	public ListadoAlquileres() throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido, Carnet_no_valido, Tarjeta_no_valida, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoAlquileres.class.getResource("/general/png/fondo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 734, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("codigo");
		modelo.addColumn("vehiculo");
		modelo.addColumn("empleado");
		modelo.addColumn("cliente");
		modelo.addColumn("fecha_alq");
		modelo.addColumn("fecha_dev");
		modelo.addColumn("oficina_dev");
		modelo.addColumn("precio");
		
		
		
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		add(new JScrollPane(tabla));	
		contentPane.add(tabla, BorderLayout.CENTER);
		
		textBusca = new JTextField();
		contentPane.add(textBusca, BorderLayout.SOUTH);
		textBusca.setColumns(10);
		
		
		ArrayList<Alquiler> alquileres=new ArrayList<Alquiler>();
		alquileres=AlquilerBD.listaAlquileres();
		
		for(Alquiler alquiler:alquileres) {
			modelo.addRow(new Object[] {alquiler.getCodigo(),alquiler.getVehiculo(),alquiler.getEmpleado(),alquiler.getCliente(),alquiler.getFecha_aql(),alquiler.getFecha_dev(),alquiler.getOficina_dev(),alquiler.getPrecio()});
		}
			
		MetodosGui.centraVentana(this);
		MetodosGui.SearchInJtable(tabla, textBusca);
	}

}
