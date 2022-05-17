package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import accesoBD.CategoriaBD;
import accesoBD.Conexion;
import accesoBD.OficinaBD;
import accesoBD.VehiculoBD;
import clases.*;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Localidad_no_valida;
import exceptions.Matricula_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;
import exceptions.Recargo_no_valido;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class VentanaVehiculos extends JFrame {

	private JPanel contentPane;
	private JTextField textMatricula;
	private int existe=0;
	private JButton btnIntroducir;
	private JFrame yo=this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion.conexion();
					VentanaVehiculos frame = new VentanaVehiculos();
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
	 * @throws Recargo_no_valido 
	 */
	public VentanaVehiculos() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido, Recargo_no_valido {
		setTitle("Vehiculos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(26, 31, 64, 13);
		contentPane.add(lblMatricula);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(26, 54, 45, 13);
		contentPane.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(26, 77, 45, 13);
		contentPane.add(lblModelo);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(26, 100, 45, 13);
		contentPane.add(lblColor);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(26, 123, 64, 13);
		contentPane.add(lblCategoria);
		
		JLabel lblNewLabel_1 = new JLabel("Ubicacion");
		lblNewLabel_1.setBounds(26, 146, 64, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblFecha_adq = new JLabel("Fecha de adquisicion");
		lblFecha_adq.setBounds(248, 31, 125, 13);
		contentPane.add(lblFecha_adq);
		
		textMatricula = new JTextField();
		textMatricula.setBounds(92, 28, 118, 19);
		contentPane.add(textMatricula);
		textMatricula.setColumns(10);
		
		JComboBox cbColor = new JComboBox();
		cbColor.setEnabled(false);
		cbColor.setModel(new DefaultComboBoxModel(new String[] {"negro", "azul", "marron", "gris", "verde", "naranja", "rosa", "purpura", "rojo", "blanco", "amarillo"}));
		cbColor.setBounds(92, 96, 118, 21);
		contentPane.add(cbColor);
		
		JDateChooser calendarAdq = new JDateChooser();
		calendarAdq.setDateFormatString("yyyy-MM-dd");
		calendarAdq.setBounds(373, 31, 97, 16);
		calendarAdq.setEnabled(false);
		contentPane.add(calendarAdq);
		
		JTextField textModelo = new JTextField();
		textModelo.setEnabled(false);
		textModelo.setBounds(92, 74, 118, 19);
		contentPane.add(textModelo);
		textModelo.setColumns(10);
		
		JComboBox cbMarca = new JComboBox();
		cbMarca.setEnabled(false);
		cbMarca.setModel(new DefaultComboBoxModel(new String[] {"Abarth", "Alfa Romeo", "Aston Martin", "Audi", "Bentley", "BMW ", "Cadillac", "Caterham", "Chevrolet", "Citroen", "Dacia", "Ferrari", "Fiat", "Ford", "Honda", "Infiniti", "Isuzu", "Iveco", "Jaguar", "Jeep", "Kia", "KTM", "Lada", "Lamborghini", "Lancia", "Land Rover", "Lexus", "Lotus ", "Maresati", "Mazda", "Mercedes-Benz", "Mini", "Mitsubishi", "Morgan", "Nissan", "Opel", "Peugeot", "Piaggio", "Porsche", "Renault", "Rolls-Royce", "Seat", "Skoda", "Smart", "SsangYon", "Subaru", "Suzuki", "Tata", "Tesla", "Toyota", "Volkswagen", "Volvo"}));
		cbMarca.setBounds(92, 50, 118, 21);
		contentPane.add(cbMarca);
		
		JComboBox cbCategoria = new JComboBox();
		cbCategoria.setEnabled(false);
		cbCategoria.setBounds(92, 119, 118, 21);
		DefaultComboBoxModel d=new DefaultComboBoxModel();
		d.addAll(CategoriaBD.listaCategorias());
		cbCategoria.setModel(d);
		contentPane.add(cbCategoria);
		
		JComboBox cbUbicacion = new JComboBox();
		cbUbicacion.setEnabled(false);
		cbUbicacion.setBounds(92, 142, 118, 21);
		DefaultComboBoxModel u=new DefaultComboBoxModel();
		u.addAll(OficinaBD.listaOficinas());
		cbUbicacion.setModel(u);
		contentPane.add(cbUbicacion);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setIcon(new ImageIcon(VentanaVehiculos.class.getResource("/general/png/16/arrowhead_right.png")));
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(563, 337, 118, 22);
		contentPane.add(btnSiguiente);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(468, 338, 85, 21);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosGui.desactivaFormulario(contentPane);
				MetodosGui.limpiaTexto(contentPane);
				textMatricula.setEnabled(true);
				btnIntroducir.setEnabled(true);
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(373, 338, 85, 21);
		contentPane.add(btnCancelar);
		
		btnIntroducir = new JButton("");
		btnIntroducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula=textMatricula.getText();
				Vehiculo vehiculo=null;
				
				try {
					Vehiculo.validaMatricula(matricula);
						
					vehiculo=VehiculoBD.listaVehiculo(matricula);
					MetodosGui.activaFormulario(contentPane);
					textMatricula.setEnabled(false);
					calendarAdq.setEnabled(true);
					
					if(vehiculo!=null) {
						existe=1;
						
						cbMarca.setSelectedItem(vehiculo.getMarca());
						textModelo.setText(vehiculo.getModelo());
						cbColor.setSelectedItem(vehiculo.getColor());
						cbCategoria.setSelectedItem(vehiculo.getCategoria());
						cbUbicacion.setSelectedItem(vehiculo.getUbicacion());
						calendarAdq.setDate(vehiculo.getFecha_adq2());
					}
				} catch (Matricula_no_valida e1) {
					JOptionPane.showMessageDialog(null, "La matricula introducida no es valida","ERROR",JOptionPane.ERROR_MESSAGE);
				}
					
					
				
			}
		});
		btnIntroducir.setIcon(new ImageIcon(VentanaVehiculos.class.getResource("/general/png/16/zoom.png")));
		btnIntroducir.setBounds(214, 26, 24, 21);
		contentPane.add(btnIntroducir);
		MetodosGui.limpiaTexto(contentPane);
		
		MetodosGui.centraVentana(yo);
	}
}
