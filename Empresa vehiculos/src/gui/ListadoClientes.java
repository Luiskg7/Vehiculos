package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import accesoBD.ClienteBD;
import accesoBD.EmpleadoBD;
import clases.Cliente;
import clases.Empleado;
import exceptions.Ape1_no_valido;
import exceptions.Ape2_no_valido;
import exceptions.Carnet_no_valido;
import exceptions.Dni_no_valido;
import exceptions.Longitud_no_valida;
import exceptions.Tarjeta_no_valida;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class ListadoClientes extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JTextField textBusca;


	/**
	 * Create the frame.
	 * @throws Tarjeta_no_valida 
	 * @throws Carnet_no_valido 
	 * @throws Ape2_no_valido 
	 * @throws Ape1_no_valido 
	 * @throws Dni_no_valido 
	 * @throws Longitud_no_valida 
	 * @throws SQLException 
	 */
	public ListadoClientes() throws SQLException, Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido, Carnet_no_valido, Tarjeta_no_valida {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoClientes.class.getResource("/general/png/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 729, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Ape1");
		modelo.addColumn("Ape2");
		modelo.addColumn("Dni");
		modelo.addColumn("fecha_nac");
		modelo.addColumn("licencia");
		modelo.addColumn("tarjeta");
		
		
		
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		add(new JScrollPane(tabla));	
		contentPane.add(tabla, BorderLayout.CENTER);
		
		textBusca = new JTextField();
		contentPane.add(textBusca, BorderLayout.SOUTH);
		textBusca.setColumns(10);
		
		
		ArrayList<Cliente> clientes=new ArrayList<Cliente>();
		clientes=ClienteBD.listaClientes();
		
		for(Cliente cliente:clientes) {
			modelo.addRow(new Object[] {cliente.getNombre(),cliente.getApe1(),cliente.getApe2(),cliente.getDni(),cliente.getFecha_nac2(),cliente.getLicencia(),cliente.getTarjeta()});
		}
			
		MetodosGui.centraVentana(this);
		MetodosGui.SearchInJtable(tabla, textBusca);
	}

}
