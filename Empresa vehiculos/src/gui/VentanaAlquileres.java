package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import clases.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class VentanaAlquileres extends JFrame {

	private JPanel contentPane;
	private JComboBox cbOficina;
	private JComboBox cbVehiculo;
	private JComboBox cbModelo;
	private JComboBox cbCategoria;
	private JComboBox cbCliente;
	private JComboBox cbOficinaDev;
	private JButton btnGuardar;
	private JDateChooser calendarAlq;
	private JDateChooser calendarDev;
	private JComboBox cbEmpleado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlquileres frame = new VentanaAlquileres();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAlquileres() {
		setTitle("Alquilar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(168, 25, 55, 13);
		contentPane.add(lblCodigo);
		
		JTextField textCodigo = new JTextField();
		textCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(textCodigo==null||textCodigo.equals(""))){
					java.sql.Date fecha_dev=new java.sql.Date(calendarDev.getDate().getTime());
					
					Date fecha_alq=new java.sql.Date(calendarAlq.getDate().getTime());
					Alquiler alquiler=new Alquiler(Integer.parseInt(textCodigo.getText()),(Vehiculo)cbVehiculo.getSelectedItem(),(Empleado)cbEmpleado.getSelectedItem(),(Cliente)cbCliente.getSelectedItem(),fecha_alq,fecha_dev,(Oficina)cbOficinaDev.getSelectedItem());
				}
			}
		});
		textCodigo.setBounds(245, 22, 96, 19);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblOficinas = new JLabel("Oficinas");
		lblOficinas.setBounds(91, 70, 53, 13);
		contentPane.add(lblOficinas);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(364, 131, 66, 13);
		contentPane.add(lblCategorias);
		
		cbOficina = new JComboBox();
		cbOficina.setBounds(154, 66, 96, 21);
		contentPane.add(cbOficina);
		
		cbCategoria = new JComboBox();
		cbCategoria.setBounds(476, 119, 74, 21);
		contentPane.add(cbCategoria);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(91, 172, 66, 13);
		contentPane.add(lblEmpleado);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(364, 158, 45, 13);
		contentPane.add(lblCliente);
		
		cbEmpleado = new JComboBox();
		cbEmpleado.setBounds(154, 168, 96, 21);
		contentPane.add(cbEmpleado);
		
		cbCliente = new JComboBox();
		cbCliente.setBounds(476, 150, 96, 21);
		contentPane.add(cbCliente);
		
		JLabel lblOficinaDev = new JLabel("Oficinas de devolucion");
		lblOficinaDev.setBounds(91, 233, 132, 13);
		contentPane.add(lblOficinaDev);
		
		JLabel lblVehiculo = new JLabel("Vehiculo");
		lblVehiculo.setBounds(91, 111, 66, 13);
		contentPane.add(lblVehiculo);
		
		cbVehiculo = new JComboBox();
		cbVehiculo.setBounds(154, 107, 83, 21);
		contentPane.add(cbVehiculo);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(91, 135, 45, 13);
		contentPane.add(lblModelo);
		
		cbModelo = new JComboBox();
		cbModelo.setBounds(154, 131, 118, 21);
		contentPane.add(cbModelo);
		
		cbOficinaDev = new JComboBox();
		cbOficinaDev.setBounds(245, 229, 96, 21);
		contentPane.add(cbOficinaDev);
		
		JLabel lblFechaAlquiler = new JLabel("Fecha a alquilar");
		lblFechaAlquiler.setBounds(364, 192, 96, 13);
		contentPane.add(lblFechaAlquiler);
		
		JLabel lblFechaDev = new JLabel("Fecha a devolver");
		lblFechaDev.setBounds(364, 233, 102, 13);
		contentPane.add(lblFechaDev);
		
		calendarAlq = new JDateChooser();
		calendarAlq.setDateFormatString("yyyy-MM-dd\r\n");
		calendarAlq.setBounds(476, 186, 96, 19);
		contentPane.add(calendarAlq);
		
		calendarDev = new JDateChooser();
		calendarDev.setDateFormatString("yyyy-MM-dd");
		calendarDev.setBounds(476, 227, 96, 19);
		contentPane.add(calendarDev);
		
		btnGuardar = new JButton("Realizar alquiler");
		btnGuardar.setBounds(518, 323, 134, 21);
		contentPane.add(btnGuardar);
	}
}
