package gui;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoBD.CategoriaBD;
import accesoBD.Conexion;
import accesoBD.EmpleadoBD;
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

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class VentanaVehiculo extends JFrame {

	private JPanel contentPane;
	private JTextField textMatricula;
	private JTextField textModelo;
	private JTextField textAutonomia;
	private JTextField textConsumo;
	private JTextField textRecarga;
	private JTextField textPotencia;
	private JTextField textPlaza;
	private JTextField textCarga;
	private int existe=0;
	private JComboBox cbMarca;
	private JDateChooser calendarAdq;
	private JComboBox cbColor;
	private JComboBox cbCategoria;
	private JComboBox cbUbicacion;
	private JTabbedPane tabbedPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JComboBox cbTipo;
	private JComboBox cbCilindrada;
	private JComboBox cbLicencia;
	private JComboBox cbEmision;
	private JComboBox cbEmision_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion.conexion();
					VentanaVehiculo frame = new VentanaVehiculo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Recargo_no_valido 
	 * @throws Descripcion_no_valida 
	 * @throws Codigo_no_valido 
	 * @throws SQLException 
	 * @throws Opcion_no_valida 
	 * @throws Provincia_no_valida 
	 * @throws Localidad_no_valida 
	 */
	public VentanaVehiculo() throws SQLException, Codigo_no_valido, Descripcion_no_valida, Recargo_no_valido, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida {
		JFrame yo=this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMatricula = new JLabel("Matricula");
		panel.add(lblMatricula);
		
		textMatricula = new JTextField();
		textMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula=textMatricula.getText();
				Vehiculo vehiculo=null;
				
				try {
					Vehiculo.validaMatricula(matricula);
						
					vehiculo=VehiculoBD.listaVehiculo(matricula);
					MetodosGui.activaFormulario(panel);
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
						
						if(vehiculo.getClass()==Coche_electrico.class) {
							//TODO
							tabbedPane.setSelectedIndex(0);
							MetodosGui.activaFormulario(panel_1);
							textAutonomia.setText(String.valueOf(((Coche_electrico)vehiculo).getAutonomia()));
							textRecarga.setText(String.valueOf(((Coche_electrico)vehiculo).getRecarga()));
							textPlaza.setText(String.valueOf(((Coche_electrico)vehiculo).getPlazas()));
							cbTipo.setSelectedItem(((Coche_electrico)vehiculo).getTipo());
							
						}else if(vehiculo.getClass()==Motos.class) {
							tabbedPane.setSelectedIndex(1);
							MetodosGui.activaFormulario(panel_2);
							textAutonomia.setText(String.valueOf(((Coche_electrico)vehiculo).getAutonomia()));
							textRecarga.setText(String.valueOf(((Coche_electrico)vehiculo).getRecarga()));
							cbCilindrada.setSelectedItem(String.valueOf(((Motos)vehiculo).getCilindrada()));
							cbLicencia.setSelectedItem(((Motos)vehiculo).getLicencia());
						}else if(vehiculo.getClass()==Coche_combustion.class) {
							tabbedPane.setSelectedIndex(2);
							MetodosGui.activaFormulario(panel_3);
							textConsumo.setText(String.valueOf(((Coche_combustion)vehiculo).getConsumo()));
							textPotencia.setText(String.valueOf(((Coche_combustion)vehiculo).getPotencia()));
							cbEmision.setSelectedItem(((Coche_combustion)vehiculo).getEmisiones());
							textPlaza.setText(String.valueOf(((Coche_combustion)vehiculo).getPlazas()));
							cbTipo.setSelectedItem(((Coche_combustion)vehiculo).getTipo());
							
						}else if(vehiculo.getClass()==Furgoneta.class) {
							tabbedPane.setSelectedIndex(3);
							MetodosGui.activaFormulario(panel_4);
							textConsumo.setText(String.valueOf(((Furgoneta)vehiculo).getConsumo()));
							textPotencia.setText(String.valueOf(((Furgoneta)vehiculo).getPotencia()));
							cbEmision.setSelectedItem(((Furgoneta)vehiculo).getEmisiones());
							textCarga.setText(String.valueOf(((Furgoneta)vehiculo).getCarga()));
							cbLicencia.setSelectedItem(((Furgoneta)vehiculo).getCarnet());
							
						}
					}
					
					MetodosGui.activaFormulario(panel_5);
				} catch (Matricula_no_valida e1) {
					JOptionPane.showMessageDialog(null, "La matricula introducida no es valida","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(textMatricula);
		textMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		panel.add(lblMarca);
		
		cbMarca = new JComboBox();
		cbMarca.setModel(new DefaultComboBoxModel(new String[] {"Abarth", "Alfa Romeo", "Aston Martin", "Audi", "Bentley", "BMW ", "Cadillac", "Caterham", "Chevrolet", "Citroen", "Dacia", "Ferrari", "Fiat", "Ford", "Honda", "Infiniti", "Isuzu", "Iveco", "Jaguar", "Jeep", "Kia", "KTM", "Lada", "Lamborghini", "Lancia", "Land Rover", "Lexus", "Lotus ", "Maresati", "Volkswagen"}));
		panel.add(cbMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		panel.add(lblModelo);
		
		textModelo = new JTextField();
		panel.add(textModelo);
		textModelo.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		panel.add(lblColor);
		
		cbColor = new JComboBox();
		cbColor.setModel(new DefaultComboBoxModel(new String[] {"negro", "azul", "marron", "gris", "verde", "naranja", "rosa", "purpura", "rojo", "blanco", "amarillo"}));
		panel.add(cbColor);
		
		JLabel lblCategoria = new JLabel("Categoria");
		panel.add(lblCategoria);
		
		cbCategoria = new JComboBox();
		DefaultComboBoxModel d=new DefaultComboBoxModel();
		d.addAll(CategoriaBD.listaCategorias());
		cbCategoria.setModel(d);
		panel.add(cbCategoria);
		
		JLabel lblUbicacion = new JLabel("Ubicacion");
		panel.add(lblUbicacion);
		
		cbUbicacion = new JComboBox();
		DefaultComboBoxModel u=new DefaultComboBoxModel();
		u.addAll(OficinaBD.listaOficinas());
		cbUbicacion.setModel(u);
		panel.add(cbUbicacion);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Coche electrico", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblAutonomia1 = new JLabel("Autonomia");
		lblAutonomia1.setBounds(249, 66, 66, 13);
		panel_1.add(lblAutonomia1);
		
		textAutonomia = new JTextField();
		textAutonomia.setBounds(341, 63, 96, 19);
		panel_1.add(textAutonomia);
		textAutonomia.setColumns(10);
		
		JLabel lblRecarga1 = new JLabel("Recarga");
		lblRecarga1.setBounds(504, 66, 52, 13);
		panel_1.add(lblRecarga1);
		
		textRecarga = new JTextField();
		textRecarga.setBounds(566, 63, 43, 19);
		panel_1.add(textRecarga);
		textRecarga.setColumns(10);
		
		JLabel lblPlaza = new JLabel("Plazas");
		lblPlaza.setBounds(249, 139, 45, 13);
		panel_1.add(lblPlaza);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(504, 139, 45, 13);
		panel_1.add(lblTipo);
		
		textPlaza = new JTextField();
		textPlaza.setBounds(304, 136, 25, 19);
		panel_1.add(textPlaza);
		textPlaza.setColumns(10);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Deportivo", "Familiar", "4x4"}));
		cbTipo.setBounds(566, 135, 85, 21);
		panel_1.add(cbTipo);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Moto", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblAutonomia2 = new JLabel("Autonomia");
		lblAutonomia2.setBounds(249, 66, 66, 13);
		panel_2.add(lblAutonomia2);
		
		textAutonomia = new JTextField();
		textAutonomia.setBounds(341, 63, 96, 19);
		textAutonomia.setColumns(10);
		panel_2.add(textAutonomia);
		
		JLabel lblRecarga2 = new JLabel("Recarga");
		lblRecarga2.setBounds(504, 66, 52, 13);
		panel_2.add(lblRecarga2);
		
		textRecarga = new JTextField();
		textRecarga.setBounds(566, 63, 43, 19);
		textRecarga.setColumns(10);
		panel_2.add(textRecarga);
		
		JLabel lblCilindrada = new JLabel("Cilindradas");
		lblCilindrada.setBounds(249, 139, 66, 13);
		panel_2.add(lblCilindrada);
		
		cbCilindrada = new JComboBox();
		cbCilindrada.setModel(new DefaultComboBoxModel(new String[] {"125cc", "250cc", "300cc", "400cc"}));
		cbCilindrada.setBounds(322, 136, 52, 19);
		panel_2.add(cbCilindrada);
		
		JLabel lblLicencia = new JLabel("Licencia");
		lblLicencia.setBounds(504, 139, 66, 13);
		panel_2.add(lblLicencia);
		
		cbLicencia = new JComboBox();
		cbLicencia.setModel(new DefaultComboBoxModel(new String[] {"AM", "A1", "A2"}));
		cbLicencia.setBounds(566, 135, 59, 21);
		panel_2.add(cbLicencia);
		
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Coche combustion", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblConsumo = new JLabel("Consumo");
		lblConsumo.setBounds(249, 66, 66, 13);
		panel_3.add(lblConsumo);
		
		textConsumo = new JTextField();
		textConsumo.setBounds(341, 63, 96, 19);
		panel_3.add(textConsumo);
		textConsumo.setColumns(10);
		
		JLabel lblPotencia = new JLabel("Potencia");
		lblPotencia.setBounds(504, 66, 52, 13);
		panel_3.add(lblPotencia);
		
		textPotencia = new JTextField();
		textPotencia.setBounds(566, 63, 43, 19);
		panel_3.add(textPotencia);
		textPotencia.setColumns(10);
		
		JLabel lblPlaza3 = new JLabel("Plazas");
		lblPlaza3.setBounds(249, 139, 45, 13);
		panel_3.add(lblPlaza3);
		
		JLabel lblTipo3 = new JLabel("Tipo");
		lblTipo3.setBounds(504, 139, 45, 13);
		panel_3.add(lblTipo3);
		
		textPlaza = new JTextField();
		textPlaza.setBounds(304, 136, 25, 19);
		panel_3.add(textPlaza);
		textPlaza.setColumns(10);
		
		JComboBox cbTipo3 = new JComboBox();
		cbTipo3.setModel(new DefaultComboBoxModel(new String[] {"Deportivo", "Familiar", "4x4"}));
		cbTipo3.setBounds(566, 135, 85, 21);
		panel_3.add(cbTipo3);
		
		JLabel lblEmision = new JLabel("Emisiones");
		lblEmision.setBounds(639, 66, 64, 13);
		panel_3.add(lblEmision);
		
		cbEmision = new JComboBox();
		cbEmision.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		cbEmision.setBounds(713, 62, 43, 21);
		panel_3.add(cbEmision);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Furgoneta", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblConsumo2 = new JLabel("Comsuno");
		lblConsumo2.setBounds(249, 66, 66, 13);
		panel_4.add(lblConsumo2);
		
		textAutonomia = new JTextField();
		textAutonomia.setBounds(341, 63, 96, 19);
		panel_4.add(textAutonomia);
		textAutonomia.setColumns(10);
		
		JLabel lblPotencia2 = new JLabel("Potencia");
		lblPotencia2.setBounds(504, 66, 52, 13);
		panel_4.add(lblPotencia2);
		
		textRecarga = new JTextField();
		textRecarga.setBounds(566, 63, 43, 19);
		panel_4.add(textRecarga);
		textRecarga.setColumns(10);
		
		JLabel lblCarga = new JLabel("Carga");
		lblCarga.setBounds(249, 139, 45, 13);
		panel_4.add(lblCarga);
		
		JLabel lblLicencia2 = new JLabel("Licencia");
		lblLicencia2.setBounds(504, 139, 52, 13);
		panel_4.add(lblLicencia2);
		
		textCarga = new JTextField();
		textCarga.setBounds(304, 136, 52, 19);
		panel_4.add(textCarga);
		textCarga.setColumns(10);
		
		JComboBox cbLicencia2 = new JComboBox();
		cbLicencia2.setModel(new DefaultComboBoxModel(new String[] {"B", "C", "D"}));
		cbLicencia2.setBounds(566, 135, 52, 21);
		panel_4.add(cbLicencia2);
		
		JLabel lblEmision2 = new JLabel("Emisiones");
		lblEmision2.setBounds(655, 66, 66, 13);
		panel_4.add(lblEmision2);
		
		cbEmision_1 = new JComboBox();
		cbEmision_1.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		cbEmision_1.setBounds(745, 62, 43, 21);
		panel_4.add(cbEmision_1);
		
		panel_5 = new JPanel();
		contentPane.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosGui.desactivaFormulario(contentPane);
				MetodosGui.limpiaTexto(panel);
				MetodosGui.desactivaFormulario(tabbedPane);
				MetodosGui.limpiaTexto(tabbedPane);
				textMatricula.setEnabled(true);
				
			}
		});
		panel_5.add(btnCancelar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula=textMatricula.getText();
				
				try {
					//JDialog para avisar al usuario de que va a eliminar un vehiculo de la bd
					Object[] options = {"Aceptar",  "Cancelar",};
					int opc = JOptionPane.showOptionDialog(yo,"¿Desea eliminar este vehiculo?","AVISO",JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
					
					if(opc==0) {
						
						VehiculoBD.eliminaVehiculo(matricula);
						MetodosGui.limpiaTexto(contentPane);
						MetodosGui.desactivaFormulario(contentPane);
						textMatricula.setEnabled(true);
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_5.add(btnEliminar);
		
		JButton btnGuardar = new JButton("Guardar");
		panel_5.add(btnGuardar);
		
		MetodosGui.desactivaFormulario(contentPane);
		MetodosGui.desactivaFormulario(panel_1);
		textMatricula.setEnabled(true);
		
		JLabel lblFechaAdq = new JLabel("Fecha de adquisicion");
		panel.add(lblFechaAdq);
		
		calendarAdq = new JDateChooser();
		calendarAdq.setDateFormatString("yyyy-MM-dd");
		panel.add(calendarAdq);
		
		MetodosGui.limpiaTexto(panel);
	}
}
