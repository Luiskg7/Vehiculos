package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import accesoBD.AlquilerBD;
import accesoBD.ClienteBD;
import accesoBD.Conexion;
import accesoBD.EmpleadoBD;
import accesoBD.OficinaBD;
import accesoBD.VehiculoBD;
import clases.*;
import exceptions.Ape1_no_valido;
import exceptions.Ape2_no_valido;
import exceptions.Autonomia_no_valida;
import exceptions.Carnet_no_valido;
import exceptions.Codigo_no_valido;
import exceptions.Color_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Dni_no_valido;
import exceptions.Km_no_valido;
import exceptions.Localidad_no_valida;
import exceptions.Longitud_no_valida;
import exceptions.Marca_no_valida;
import exceptions.Matricula_no_valida;
import exceptions.Modelo_no_valido;
import exceptions.Opcion_no_valida;
import exceptions.Plazas_no_validas;
import exceptions.Provincia_no_valida;
import exceptions.Recarga_no_valida;
import exceptions.Recargo_no_valido;
import exceptions.Tarjeta_no_valida;
import exceptions.Tipo_no_valido;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class VentanaAlquileres extends JFrame {

	private JPanel contentPane;
	private JComboBox cbOficina;
	private JComboBox cbVehiculo;
	private JComboBox cbCliente;
	private JComboBox cbOficinaDev;
	private JButton btnGuardar;
	private JDateChooser calendarAlq;
	private JDateChooser calendarDev;
	private JComboBox cbEmpleado;
	private int existe=0;
	private DefaultComboBoxModel ofiVehiculo=null;
	private DefaultComboBoxModel ofiEmpleado=null;
	private DefaultComboBoxModel of=null;
	private JLabel lblPrecio2;
	private JButton btnElimina;

	/**
	 * Create the frame.
	 * @throws Codigo_no_valido 
	 * @throws Opcion_no_valida 
	 * @throws Provincia_no_valida 
	 * @throws Localidad_no_valida 
	 * @throws Descripcion_no_valida 
	 * @throws SQLException 
	 * @throws Tipo_no_valido 
	 * @throws Plazas_no_validas 
	 * @throws Recarga_no_valida 
	 * @throws Autonomia_no_valida 
	 * @throws Color_no_valido 
	 * @throws Modelo_no_valido 
	 * @throws Marca_no_valida 
	 * @throws Matricula_no_valida 
	 * @throws Km_no_valido 
	 * @throws Recargo_no_valido 
	 * @throws Tarjeta_no_valida 
	 * @throws Carnet_no_valido 
	 * @throws Ape2_no_valido 
	 * @throws Ape1_no_valido 
	 * @throws Dni_no_valido 
	 * @throws Longitud_no_valida 
	 */
	public VentanaAlquileres() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido, Recargo_no_valido, Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida, Plazas_no_validas, Tipo_no_valido, Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido, Carnet_no_valido, Tarjeta_no_valida {
		JFrame yo=this;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAlquileres.class.getResource("/general/png/logo.png")));
		setTitle("Alquilar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 326);
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
				
			
				Alquiler alquiler;
				
				try {
					alquiler = AlquilerBD.listaAlquiler(Integer.parseInt(textCodigo.getText()));
					MetodosGui.activaFormulario(contentPane);
					textCodigo.setEnabled(false);
					
					if(alquiler!=null) {
						existe=1;
						
						String oficina=alquiler.getEmpleado().getOficina_trab().getCodigo();
						ofiVehiculo=null;
						ofiVehiculo= new DefaultComboBoxModel();
						ofiVehiculo.addAll(VehiculoBD.listaVehiculoOficina(oficina));
						cbVehiculo.setModel(ofiVehiculo);
						
						ofiEmpleado=null;
						ofiEmpleado= new DefaultComboBoxModel();
						ofiEmpleado.addAll(EmpleadoBD.listaEmpleados());
						cbEmpleado.setModel(ofiEmpleado);
						
						
						cbOficina.setSelectedItem(alquiler.getEmpleado().getOficina_trab());
						cbVehiculo.setSelectedItem((Vehiculo)(VehiculoBD.listaVehiculo(alquiler.getVehiculo().getMatricula())));
						cbEmpleado.setSelectedItem((Empleado)(EmpleadoBD.listaEmpleado(alquiler.getEmpleado().getDni())));
						cbOficinaDev.setSelectedItem(alquiler.getOficina_dev());
						calendarAlq.setDate(alquiler.getFecha_aql());
						calendarDev.setDate(alquiler.getFecha_dev());
						cbCliente.setSelectedItem(alquiler.getCliente());
						lblPrecio2.setText(String.valueOf(alquiler.getPrecio()));
						
							
						}
				} catch (NumberFormatException | Longitud_no_valida | Dni_no_valido | Ape1_no_valido
						| Ape2_no_valido | Carnet_no_valido | Tarjeta_no_valida | Descripcion_no_valida
						| Localidad_no_valida | Provincia_no_valida | Opcion_no_valida | Codigo_no_valido e1) {
					JOptionPane.showMessageDialog(null, "El código introducido no es válido,introduzca solo numeros","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		textCodigo.setBounds(245, 22, 96, 19);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblOficinas = new JLabel("Oficinas");
		lblOficinas.setBounds(91, 70, 53, 13);
		contentPane.add(lblOficinas);
		
		cbOficina = new JComboBox();
		cbOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ofiVehiculo=null;
				ofiVehiculo=new DefaultComboBoxModel();
				ofiVehiculo.addAll(VehiculoBD.listaVehiculoOficina(((Oficina)cbOficina.getSelectedItem()).getCodigo()));
				cbVehiculo.setModel(ofiVehiculo);
				
				try {
					ofiEmpleado=null;
					ofiEmpleado=new DefaultComboBoxModel();
					ofiEmpleado.addAll(EmpleadoBD.listaEmpleadoOficina(((Oficina)cbOficina.getSelectedItem()).getCodigo()));
					cbEmpleado.setModel(ofiEmpleado);
				} catch (SQLException | Longitud_no_valida | Dni_no_valido | Ape1_no_valido | Ape2_no_valido | Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida | Opcion_no_valida | Codigo_no_valido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cbOficina.setBounds(154, 66, 118, 21);
		of=new DefaultComboBoxModel();
		of.addAll(OficinaBD.listaOficinas());
		cbOficina.setModel(of);
		contentPane.add(cbOficina);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(91, 141, 66, 13);
		contentPane.add(lblEmpleado);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(364, 70, 45, 13);
		contentPane.add(lblCliente);
		
		cbEmpleado = new JComboBox();
		cbEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cbEmpleado.setBounds(154, 137, 169, 21);
		contentPane.add(cbEmpleado);
		
		cbCliente = new JComboBox();
		cbCliente.setBounds(476, 66, 186, 21);
		DefaultComboBoxModel client=new DefaultComboBoxModel();
		client.addAll(ClienteBD.listaClientes());
		cbCliente.setModel(client);
		contentPane.add(cbCliente);
		
		JLabel lblOficinaDev = new JLabel("Oficinas de devolucion");
		lblOficinaDev.setBounds(91, 192, 132, 13);
		contentPane.add(lblOficinaDev);
		
		JLabel lblVehiculo = new JLabel("Vehiculo");
		lblVehiculo.setBounds(91, 111, 66, 13);
		contentPane.add(lblVehiculo);
		
		cbVehiculo = new JComboBox();
		cbVehiculo.setBounds(154, 107, 169, 21);
		contentPane.add(cbVehiculo);
		
		cbOficinaDev = new JComboBox();
		cbOficinaDev.setBounds(239, 188, 132, 21);
		DefaultComboBoxModel ofiVuelta=new DefaultComboBoxModel();
		ofiVuelta.addAll(OficinaBD.listaOficinas());
		cbOficinaDev.setModel(ofiVuelta);
		contentPane.add(cbOficinaDev);
		
		JLabel lblFechaAlquiler = new JLabel("Fecha a alquilar");
		lblFechaAlquiler.setBounds(364, 111, 96, 13);
		contentPane.add(lblFechaAlquiler);
		
		JLabel lblFechaDev = new JLabel("Fecha a devolver");
		lblFechaDev.setBounds(364, 141, 102, 13);
		contentPane.add(lblFechaDev);
		
		calendarAlq = new JDateChooser();
		calendarAlq.setDateFormatString("yyyy-MM-dd");
		calendarAlq.setBounds(476, 111, 96, 19);
		contentPane.add(calendarAlq);
		
		calendarDev = new JDateChooser();
		calendarDev.setDateFormatString("yyyy-MM-dd");
		calendarDev.setBounds(476, 141, 96, 19);
		contentPane.add(calendarDev);
		
		btnGuardar = new JButton("Realizar alquiler");
		btnGuardar.setIcon(new ImageIcon(VentanaAlquileres.class.getResource("/general/png/16/diskette.png")));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date a=new java.sql.Date(calendarAlq.getDate().getTime());
				
				Date fechaAql=new java.sql.Date(calendarAlq.getDate().getTime());
				Date fechaDev=new java.sql.Date(calendarDev.getDate().getTime());
				
				double precio=AlquilerBD.calculaPrecio((Vehiculo)cbVehiculo.getSelectedItem(),fechaAql,fechaDev,((Vehiculo)cbVehiculo.getSelectedItem()).getCategoria(),(Oficina)cbOficina.getSelectedItem(),(Oficina)cbOficinaDev.getSelectedItem());
				Alquiler alquiler=new Alquiler(Integer.parseInt(textCodigo.getText()),(Vehiculo)cbVehiculo.getSelectedItem(),(Empleado)cbEmpleado.getSelectedItem(),(Cliente)cbCliente.getSelectedItem(),fechaAql,fechaDev,(Oficina)cbOficinaDev.getSelectedItem(),precio);
				if(existe==0) {
					try {
						AlquilerBD.creaAlquiler(alquiler);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					try {
						AlquilerBD.modificaAlquiler(alquiler);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				//Limpia los campos rellenados
				ofiVehiculo=null;
				ofiVehiculo=new DefaultComboBoxModel();
				cbVehiculo.setModel(ofiVehiculo);
				ofiEmpleado=null;
				ofiEmpleado=new DefaultComboBoxModel();
				cbEmpleado.setModel(ofiEmpleado);
				cbCliente.setSelectedIndex(-1);
				calendarAlq.setCalendar(null);
				calendarDev.setCalendar(null);
				cbOficinaDev.setSelectedIndex(-1);
				//Desactiva el formulario y activa el codigo del alquiler
				MetodosGui.desactivaFormulario(contentPane);
				textCodigo.setEnabled(true);
				lblPrecio2.setText("");
			}
		});
		btnGuardar.setBounds(503, 260, 159, 21);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(VentanaAlquileres.class.getResource("/general/png/16/checkbox_cross.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ofiVehiculo=null;
				ofiVehiculo=new DefaultComboBoxModel();
				cbVehiculo.setModel(ofiVehiculo);
				ofiEmpleado=null;
				ofiEmpleado=new DefaultComboBoxModel();
				cbEmpleado.setModel(ofiEmpleado);
				cbCliente.setSelectedIndex(-1);
				calendarAlq.setCalendar(null);
				calendarDev.setCalendar(null);
				cbOficinaDev.setSelectedIndex(-1);
				
				MetodosGui.desactivaFormulario(contentPane);
				textCodigo.setEnabled(true);
				lblPrecio2.setText("");
				textCodigo.setText("");
			}
		});
		btnCancelar.setBounds(253, 260, 118, 21);
		contentPane.add(btnCancelar);
		
		MetodosGui.centraVentana(this);
		MetodosGui.desactivaFormulario(contentPane);
		textCodigo.setEnabled(true);
		
		JLabel lblPrecio = new JLabel("Precio del alquiler:");
		lblPrecio.setBounds(91, 240, 118, 13);
		contentPane.add(lblPrecio);
		
		lblPrecio2 = new JLabel("");
		lblPrecio2.setBounds(219, 240, 66, 13);
		contentPane.add(lblPrecio2);
		
		btnElimina = new JButton("Eliminar");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//JDialog para avisar al usuario de que va a eliminar una oficina de la bd
				Object[] options = {"Aceptar",  "Cancelar",};
				int opc = JOptionPane.showOptionDialog(yo,"¿Desea eliminar este alquiler?","AVISO",JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
				
				if (opc==0) {
					try {
						AlquilerBD.eliminarAlquiler(Integer.parseInt(textCodigo.getText()));
						
						//desactiva y limpia el JFrame
						ofiVehiculo=null;
						ofiVehiculo=new DefaultComboBoxModel();
						cbVehiculo.setModel(ofiVehiculo);
						ofiEmpleado=null;
						ofiEmpleado=new DefaultComboBoxModel();
						cbEmpleado.setModel(ofiEmpleado);
						cbCliente.setSelectedIndex(-1);
						calendarAlq.setCalendar(null);
						calendarDev.setCalendar(null);
						cbOficinaDev.setSelectedIndex(-1);
						MetodosGui.desactivaFormulario(contentPane);
						textCodigo.setEnabled(true);
						lblPrecio2.setText("");
						textCodigo.setText("");
						textCodigo.setEnabled(true);
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		btnElimina.setIcon(new ImageIcon(VentanaAlquileres.class.getResource("/general/png/16/trash_can.png")));
		btnElimina.setBounds(391, 260, 102, 21);
		contentPane.add(btnElimina);
		
		
		
	}
}
