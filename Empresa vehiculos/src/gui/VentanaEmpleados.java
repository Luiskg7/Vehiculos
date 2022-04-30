package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoBD.Conexion;
import accesoBD.OficinaBD;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Localidad_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VentanaEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion.conexion();
					VentanaEmpleados frame = new VentanaEmpleados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Codigo_no_valido 
	 * @throws Opcion_no_valida 
	 * @throws Provincia_no_valida 
	 * @throws Localidad_no_valida 
	 * @throws Descripcion_no_valida 
	 * @throws SQLException 
	 */
	public VentanaEmpleados() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbldni = new JLabel("DNI");
		lbldni.setBounds(27, 35, 45, 13);
		contentPane.add(lbldni);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 58, 45, 13);
		contentPane.add(lblNombre);
		
		JLabel lblApe1 = new JLabel("Primer apellido");
		lblApe1.setBounds(27, 81, 90, 13);
		contentPane.add(lblApe1);
		
		JLabel lblApe2 = new JLabel("Segundo apellido");
		lblApe2.setBounds(27, 104, 104, 13);
		contentPane.add(lblApe2);
		
		JLabel lblFecha_Nac = new JLabel("Fecha de nacimiento");
		lblFecha_Nac.setBounds(27, 127, 118, 13);
		contentPane.add(lblFecha_Nac);
		
		JLabel lblFecha_Alta = new JLabel("Fecha de alta");
		lblFecha_Alta.setBounds(27, 150, 90, 13);
		contentPane.add(lblFecha_Alta);
		
		JLabel lblOficina_trabajo = new JLabel("Oficina de trabajo");
		lblOficina_trabajo.setBounds(137, 173, 104, 13);
		contentPane.add(lblOficina_trabajo);
		
		textField = new JTextField();
		textField.setBounds(70, 32, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(94, 55, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(120, 78, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(145, 101, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox cbOficinas=OficinaBD.cbOficinas();
		lblOficina_trabajo.setBounds(27, 173, 104, 13);
		
		
		
		//JCalendar fecha_nac=new JCalendar();
	}
}
