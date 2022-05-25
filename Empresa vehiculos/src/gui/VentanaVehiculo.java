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
import exceptions.Autonomia_no_valida;
import exceptions.Carga_no_valida;
import exceptions.Carnet_no_valido;
import exceptions.Cilindrada_no_valida;
import exceptions.Codigo_no_valido;
import exceptions.Color_no_valido;
import exceptions.Consumo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Emision_no_valida;
import exceptions.Km_no_valido;
import exceptions.Localidad_no_valida;
import exceptions.Marca_no_valida;
import exceptions.Matricula_no_valida;
import exceptions.Modelo_no_valido;
import exceptions.Opcion_no_valida;
import exceptions.Plazas_no_validas;
import exceptions.Potencia_no_valida;
import exceptions.Provincia_no_valida;
import exceptions.Recarga_no_valida;
import exceptions.Recargo_no_valido;
import exceptions.Tipo_no_valido;
import listeners.Cancelar_coche_electrico;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import listeners.*;
import java.awt.Toolkit;

public class VentanaVehiculo extends JFrame {

	static public JPanel contentPane;
	static public JFrame yo;
	static public JTextField textMatricula;
	private JTextField textModelo;
	private JTextField textAutonomia;
	private JTextField textConsumo2;
	private JTextField textAutonomia2;
	private JTextField textAutonomia1;
	private JTextField textConsumo;
	private JTextField textRecarga;
	private JTextField textPotencia2;
	private JTextField textRecarga2;
	private JTextField textRecarga1;
	private JTextField textPotencia;
	private JTextField textPlaza;
	private JTextField textPlaza2;
	private JTextField textCarga;
	private int existe=0;
	private JComboBox cbMarca;
	static public JDateChooser calendarAdq;
	private JComboBox cbColor;
	private JComboBox cbCategoria;
	private JComboBox cbUbicacion;
	static public JTabbedPane tabbedPane;
	static public  JPanel panel_1;
	static public  JPanel panel_2;
	static public JPanel panel_3;
	static public JPanel panel_4;
	private JComboBox cbTipo;
	private JComboBox cbCilindrada;
	private JComboBox cbLicencia;
	private JComboBox cbEmision;
	private JComboBox cbEmision_1;
	private JButton btnCancelar;
	static public JPanel panel;
	private JButton btnEliminar;
	private JButton btnEliminar2;
	private JButton btnEliminar3;
	private JButton btnEliminar4;
	private JButton btnEliminar2_1;
	private JButton btnEliminar2_2;
	private Eliminar_vehiculo elimina=new Eliminar_vehiculo();
	private JComboBox cbTipo2;
	private JLabel lblKm;
	private JTextField textKm;
	private JButton btnGuardar3;
	private JComboBox cbLicencia2;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaVehiculo.class.getResource("/general/png/logo.png")));
		yo=this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1303, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
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
						textKm.setText(String.valueOf(vehiculo.getKms()));
						
						if(vehiculo.getClass()==Coche_electrico.class) {
							//TODO
						
							tabbedPane.setSelectedIndex(0);
							MetodosGui.activaFormulario(panel_1);
							textAutonomia1.setText(String.valueOf(((Coche_electrico)vehiculo).getAutonomia()));
							textRecarga1.setText(String.valueOf(((Coche_electrico)vehiculo).getRecarga()));
							textPlaza.setText(String.valueOf(((Coche_electrico)vehiculo).getPlazas()));
							cbTipo.setSelectedItem(((Coche_electrico)vehiculo).getTipo());
							
						}else if(vehiculo.getClass()==Motos.class) {
							tabbedPane.setSelectedIndex(1);
							MetodosGui.activaFormulario(panel_2);
							textAutonomia2.setText(String.valueOf(((Motos)vehiculo).getAutonomia()));
							textRecarga2.setText(String.valueOf(((Motos)vehiculo).getRecarga()));
							cbCilindrada.setSelectedItem(String.valueOf(((Motos)vehiculo).getCilindrada()));
							cbLicencia.setSelectedItem(((Motos)vehiculo).getLicencia());
						}else if(vehiculo.getClass()==Coche_combustion.class) {
							tabbedPane.setSelectedIndex(2);
							MetodosGui.activaFormulario(panel_3);
							textConsumo.setText(String.valueOf(((Coche_combustion)vehiculo).getConsumo()));
							textPotencia.setText(String.valueOf(((Coche_combustion)vehiculo).getPotencia()));
							cbEmision.setSelectedItem(((Coche_combustion)vehiculo).getEmisiones());
							textPlaza2.setText(String.valueOf(((Coche_combustion)vehiculo).getPlazas()));
							cbTipo2.setSelectedItem(((Coche_combustion)vehiculo).getTipo());
							
						}else if(vehiculo.getClass()==Furgoneta.class) {
							tabbedPane.setSelectedIndex(3);
							MetodosGui.activaFormulario(panel_4);
							textConsumo2.setText(String.valueOf(((Furgoneta)vehiculo).getConsumo()));
							textPotencia2.setText(String.valueOf(((Furgoneta)vehiculo).getPotencia()));
							cbEmision_1.setSelectedItem(((Furgoneta)vehiculo).getEmisiones());
							textCarga.setText(String.valueOf(((Furgoneta)vehiculo).getCarga()));
							cbLicencia2.setSelectedItem(((Furgoneta)vehiculo).getCarnet());
							
						}
					}else {
						tabbedPane.setEnabled(true);
						MetodosGui.activaFormulario(tabbedPane);
						MetodosGui.limpiaTexto(tabbedPane);
						existe=0;
					}
					
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
		
		textAutonomia1 = new JTextField();
		textAutonomia1.setBounds(341, 63, 96, 19);
		panel_1.add(textAutonomia1);
		textAutonomia1.setColumns(10);
		
		JLabel lblRecarga1 = new JLabel("Recarga");
		lblRecarga1.setBounds(504, 66, 52, 13);
		panel_1.add(lblRecarga1);
		
		textRecarga1 = new JTextField();
		textRecarga1.setBounds(566, 63, 43, 19);
		panel_1.add(textRecarga1);
		textRecarga1.setColumns(10);
		
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
		
		textAutonomia2 = new JTextField();
		textAutonomia2.setBounds(341, 63, 96, 19);
		textAutonomia2.setColumns(10);
		panel_2.add(textAutonomia2);
		
		JLabel lblRecarga2 = new JLabel("Recarga");
		lblRecarga2.setBounds(504, 66, 52, 13);
		panel_2.add(lblRecarga2);
		
		textRecarga2 = new JTextField();
		textRecarga2.setBounds(566, 63, 43, 19);
		textRecarga2.setColumns(10);
		panel_2.add(textRecarga2);
		
		JLabel lblCilindrada = new JLabel("Cilindradas");
		lblCilindrada.setBounds(249, 139, 66, 13);
		panel_2.add(lblCilindrada);
		
		cbCilindrada = new JComboBox();
		cbCilindrada.setModel(new DefaultComboBoxModel(new String[] {"125", "250", "300", "400"}));
		cbCilindrada.setBounds(322, 136, 66, 19);
		panel_2.add(cbCilindrada);
		
		JLabel lblLicencia = new JLabel("Licencia");
		lblLicencia.setBounds(504, 139, 66, 13);
		panel_2.add(lblLicencia);
		
		cbLicencia = new JComboBox();
		cbLicencia.setModel(new DefaultComboBoxModel(new String[] {"AM", "A1", "A2"}));
		cbLicencia.setBounds(566, 135, 59, 21);
		panel_2.add(cbLicencia);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(987, 220, 84, 21);
		Cancelar_moto listener2=new Cancelar_moto();
		btnCancelar.addActionListener(listener2);
		panel_2.add(btnCancelar);
		
		btnEliminar2 = new JButton("Eliminar");
		btnEliminar2.addActionListener(elimina);
		btnEliminar2.setBounds(1081, 220, 83, 21);
		panel_2.add(btnEliminar2);
		
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
		
		textPlaza2 = new JTextField();
		textPlaza2.setBounds(304, 136, 25, 19);
		panel_3.add(textPlaza2);
		textPlaza2.setColumns(10);
		
		cbTipo2 = new JComboBox();
		cbTipo2.setModel(new DefaultComboBoxModel(new String[] {"Deportivo", "Familiar", "4x4"}));
		cbTipo2.setBounds(566, 135, 85, 21);
		panel_3.add(cbTipo2);
		
		JLabel lblEmision = new JLabel("Emisiones");
		lblEmision.setBounds(639, 66, 64, 13);
		panel_3.add(lblEmision);
		
		cbEmision = new JComboBox();
		cbEmision.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		cbEmision.setBounds(713, 62, 43, 21);
		panel_3.add(cbEmision);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(975, 220, 96, 21);
		Cancelar_coche_combustion listener3=new Cancelar_coche_combustion();
		btnCancelar.addActionListener(listener3);
		panel_3.add(btnCancelar);
		
		btnEliminar3 = new JButton("Eliminar");
		btnEliminar3.setBounds(1081, 220, 83, 21);
		btnEliminar3.addActionListener(elimina);
		panel_3.add(btnEliminar3);
		
		btnGuardar3 = new JButton("Guardar");
		btnGuardar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date a=new java.sql.Date(calendarAdq.getDate().getTime());
				
				
					try {
						Coche_combustion coche=new Coche_combustion(textMatricula.getText(),(String)cbMarca.getSelectedItem(),textModelo.getText(),(String)cbColor.getSelectedItem(),a,Double.parseDouble(textKm.getText()),(Categoria)cbCategoria.getSelectedItem(),(Oficina)cbUbicacion.getSelectedItem(),Double.parseDouble(textConsumo.getText()),Integer.parseInt(textPotencia.getText()),(String)cbEmision.getSelectedItem(),Integer.parseInt(textPlaza2.getText()),(String)cbTipo2.getSelectedItem());
						
						if (existe==0) {
							VehiculoBD.añadeCoche_combustion(coche);
						}else {
							VehiculoBD.modificaCoche_combustion(coche, (Categoria)cbCategoria.getSelectedItem(), (Oficina)cbUbicacion.getSelectedItem());
						}
						
						MetodosGui.limpiaTexto(panel);
						MetodosGui.desactivaFormulario(panel);
						MetodosGui.limpiaTexto(tabbedPane);
						MetodosGui.desactivaFormulario(tabbedPane);
						textMatricula.setEnabled(true);
						tabbedPane.setEnabled(false);
						calendarAdq.setEnabled(false);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Km_no_valido e1) {
						JOptionPane.showMessageDialog(null, "Kilometros introducidos no válidos","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Matricula_no_valida e1) {
						JOptionPane.showMessageDialog(null, "Matricula introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Marca_no_valida e1) {
						JOptionPane.showMessageDialog(null, "Marca introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Modelo_no_valido e1) {
						JOptionPane.showMessageDialog(null, "Modelo introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Color_no_valido e1) {
						JOptionPane.showMessageDialog(null, "Color introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Emision_no_valida e1) {
						JOptionPane.showMessageDialog(null, "Emision introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Potencia_no_valida e1) {
						JOptionPane.showMessageDialog(null, "Potencia introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Consumo_no_valido e1) {
						JOptionPane.showMessageDialog(null, "Consumo introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Plazas_no_validas e1) {
						JOptionPane.showMessageDialog(null, "Plazas introducidas no válidas","ERROR",JOptionPane.ERROR_MESSAGE);
					} catch (Tipo_no_valido e1) {
						JOptionPane.showMessageDialog(null, "Tipo introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				
			}
		});
		btnGuardar3.setBounds(1174, 220, 90, 21);
		panel_3.add(btnGuardar3);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Furgoneta", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblConsumo2 = new JLabel("Comsuno");
		lblConsumo2.setBounds(249, 66, 66, 13);
		panel_4.add(lblConsumo2);
		
		textConsumo2 = new JTextField();
		textConsumo2.setBounds(341, 63, 96, 19);
		panel_4.add(textConsumo2);
		textConsumo2.setColumns(10);
		
		JLabel lblPotencia2 = new JLabel("Potencia");
		lblPotencia2.setBounds(504, 66, 52, 13);
		panel_4.add(lblPotencia2);
		
		textPotencia2 = new JTextField();
		textPotencia2.setBounds(566, 63, 43, 19);
		panel_4.add(textPotencia2);
		textPotencia2.setColumns(10);
		
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
		
		cbLicencia2 = new JComboBox();
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
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(982, 220, 89, 21);
		Cancelar_furgoneta listener4=new Cancelar_furgoneta();
		btnCancelar.addActionListener(listener4);
		panel_4.add(btnCancelar);
		
		btnEliminar4 = new JButton("Eliminar");
		btnEliminar4.setBounds(1081, 220, 83, 21);
		btnEliminar4.addActionListener(elimina);
		panel_4.add(btnEliminar4);
		
		JButton btnGuardar4 = new JButton("Guardar");
		btnGuardar4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date fecha=new java.sql.Date(calendarAdq.getDate().getTime());
				try {
					
					
					Furgoneta furgoneta=new Furgoneta(textMatricula.getText(),(String)cbMarca.getSelectedItem(),textModelo.getText(),(String)cbColor.getSelectedItem(),fecha,Double.parseDouble(textKm.getText()),(Categoria)cbCategoria.getSelectedItem(),(Oficina)cbUbicacion.getSelectedItem(),Double.parseDouble(textConsumo2.getText()),Integer.parseInt(textPotencia2.getText()),(String)cbEmision_1.getSelectedItem(),Integer.parseInt(textCarga.getText()),(String)cbLicencia2.getSelectedItem());
					
					if(existe==0) {
						VehiculoBD.añadeFurgoneta(furgoneta);
					}else {
						VehiculoBD.modificaFurgoneta(furgoneta, (Categoria)cbCategoria.getSelectedItem(), (Oficina)cbUbicacion.getSelectedItem());
					}
					
					
					MetodosGui.limpiaTexto(panel);
					MetodosGui.desactivaFormulario(panel);
					MetodosGui.limpiaTexto(tabbedPane);
					MetodosGui.desactivaFormulario(tabbedPane);
					textMatricula.setEnabled(true);
					tabbedPane.setEnabled(false);
					calendarAdq.setEnabled(false);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Km_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Kilometros introducidos no válidos","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Matricula_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Matricula introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Marca_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Marca introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Modelo_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Modelo introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Color_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Color introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Emision_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Emision introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Potencia_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Potencia introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Consumo_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Consumo introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Carga_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Carga introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Carnet_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Licencia introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGuardar4.setBounds(1174, 220, 90, 21);
		panel_4.add(btnGuardar4);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(986, 220, 85, 21);
		Cancelar_coche_electrico listener1=new Cancelar_coche_electrico();
		btnCancelar.addActionListener(listener1);
		panel_1.add(btnCancelar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(1081, 220, 83, 21);
		btnEliminar.addActionListener(elimina);
		panel_1.add(btnEliminar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					java.sql.Date fecha=new java.sql.Date(calendarAdq.getDate().getTime());
					Coche_electrico coche=new Coche_electrico(textMatricula.getText(),(String)cbMarca.getSelectedItem(),textModelo.getText(),(String)cbColor.getSelectedItem(),fecha,Double.parseDouble(textKm.getText()),(Categoria)cbCategoria.getSelectedItem(),(Oficina)cbUbicacion.getSelectedItem(),Double.parseDouble(textAutonomia1.getText()),Integer.parseInt(textRecarga1.getText()),Integer.parseInt(textPlaza.getText()),(String)cbTipo.getSelectedItem());
					if(existe==0) {
						VehiculoBD.añadeCoche_electrico(coche);
					}else {
						VehiculoBD.modificaCoche_electrico(coche, (Categoria)cbCategoria.getSelectedItem(), (Oficina)cbUbicacion.getSelectedItem());
					}
					
					
					//limpia el formulario una vez añadido el vehiculo
					MetodosGui.limpiaTexto(panel);
					MetodosGui.desactivaFormulario(panel);
					MetodosGui.limpiaTexto(tabbedPane);
					MetodosGui.desactivaFormulario(tabbedPane);
					tabbedPane.setEnabled(false);
					calendarAdq.setEnabled(false);
					textMatricula.setEnabled(true);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Km_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Introduzca un numero de kilometros valido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Matricula_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Matricula introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Marca_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Marca introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Modelo_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Modelo introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Color_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Color introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Autonomia_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Autonomia introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Recarga_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Recarga introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Plazas_no_validas e1) {
					JOptionPane.showMessageDialog(null, "Plazas introducidas no válidas","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Tipo_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Tipo introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGuardar.setBounds(1174, 220, 90, 21);
		panel_1.add(btnGuardar);
		
		JLabel lblFechaAdq = new JLabel("Fecha de adquisicion");
		panel.add(lblFechaAdq);
		
		calendarAdq = new JDateChooser();
		calendarAdq.setDateFormatString("yyyy-MM-dd");
		panel.add(calendarAdq);
		
		MetodosGui.desactivaFormulario(contentPane);
		MetodosGui.desactivaFormulario(panel_1);
		MetodosGui.limpiaTexto(panel);
		MetodosGui.centraVentana(yo);
		textMatricula.setEnabled(true);
		
		lblKm = new JLabel("Km");
		panel.add(lblKm);
		
		textKm = new JTextField();
		textKm.setColumns(10);
		panel.add(textKm);
		
		JButton btnGuardar2 = new JButton("Guardar");
		btnGuardar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.sql.Date fecha=new java.sql.Date(calendarAdq.getDate().getTime());
					Motos moto;
					
					moto = new Motos(textMatricula.getText(),(String)cbMarca.getSelectedItem(),textModelo.getText(),(String)cbColor.getSelectedItem(),fecha,Double.parseDouble(textKm.getText()),(Categoria)cbCategoria.getSelectedItem(),(Oficina)cbUbicacion.getSelectedItem(),Double.parseDouble(textAutonomia2.getText()),Integer.parseInt(textRecarga2.getText()),Integer.parseInt((String) cbCilindrada.getSelectedItem()),(String)cbLicencia.getSelectedItem());
					
					if(existe==0) {
						VehiculoBD.añadeMoto(moto);
					}else {
						VehiculoBD.modificaMoto(moto, (Categoria)cbCategoria.getSelectedItem(), (Oficina)cbUbicacion.getSelectedItem());
					}
					
					
					//limpia el formulario una vez añadido el vehiculo
					MetodosGui.limpiaTexto(panel);
					MetodosGui.desactivaFormulario(panel);
					textMatricula.setEnabled(true);
					MetodosGui.limpiaTexto(tabbedPane);
					MetodosGui.desactivaFormulario(tabbedPane);
					tabbedPane.setEnabled(false);
					calendarAdq.setEnabled(false);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Km_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Introduzca un numero de kilometros valido","ERROR",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (Matricula_no_valida e1) {
					e1.printStackTrace();
				} catch (Marca_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Marca introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Modelo_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Modelo introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Color_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Color introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Autonomia_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Autonomia introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Recarga_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Recarga introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (Carnet_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Carnet introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (Cilindrada_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Cilindrada introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			});
		btnGuardar2.setBounds(1174, 220, 90, 21);
		panel_2.add(btnGuardar2);
	}
}
