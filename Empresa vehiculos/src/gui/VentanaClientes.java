package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import accesoBD.ClienteBD;
import accesoBD.Conexion;
import accesoBD.EmpleadoBD;
import accesoBD.OficinaBD;
import clases.Cliente;
import clases.Empleado;
import clases.Oficina;
import clases.Persona;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class VentanaClientes extends JFrame {

	private JPanel contentPane;
	private JTextField textApe2;
	private JTextField textDni;
	private JTextField textNombre;
	private JTextField textApe1;
	private JTextField textTarjeta;
	private JComboBox cbLicencia;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JDateChooser calendarFechaNac;
	private JButton btnIntroducir;
	private int existe=0;

	/**
	 * Create the frame.
	 */
	public VentanaClientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaClientes.class.getResource("/general/png/logo.png")));
		JFrame yo=this;
		setTitle("Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(38, 54, 45, 13);
		contentPane.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(38, 77, 45, 13);
		contentPane.add(lblNombre);
		
		JLabel lblApe1 = new JLabel("Primer apellido");
		lblApe1.setBounds(38, 100, 93, 13);
		contentPane.add(lblApe1);
		
		JLabel lblApe2 = new JLabel("Segundo apellido");
		lblApe2.setBounds(38, 123, 109, 13);
		contentPane.add(lblApe2);
		
		JLabel lblLicencia = new JLabel("Licencia");
		lblLicencia.setBounds(38, 146, 63, 13);
		contentPane.add(lblLicencia);
		
		JLabel lblTarjeta = new JLabel("Tarjeta");
		lblTarjeta.setBounds(38, 169, 63, 13);
		contentPane.add(lblTarjeta);
		
		textApe2 = new JTextField();
		textApe2.setBounds(151, 120, 96, 19);
		contentPane.add(textApe2);
		textApe2.setColumns(10);
		
		textDni = new JTextField();
		textDni.setBounds(79, 51, 96, 19);
		contentPane.add(textDni);
		textDni.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(93, 74, 96, 19);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textApe1 = new JTextField();
		textApe1.setBounds(141, 97, 96, 19);
		contentPane.add(textApe1);
		textApe1.setColumns(10);
		
		textTarjeta = new JTextField();
		textTarjeta.setBounds(93, 169, 96, 19);
		contentPane.add(textTarjeta);
		textTarjeta.setColumns(10);
		
		calendarFechaNac = new JDateChooser();
		calendarFechaNac.setBounds(485, 48, 96, 19);
		contentPane.add(calendarFechaNac);
		
		JLabel lblFechaNac = new JLabel("Fecha de nacimiento");
		lblFechaNac.setBounds(341, 54, 134, 13);
		contentPane.add(lblFechaNac);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.sql.Date fecha=new java.sql.Date(calendarFechaNac.getDate().getTime());
					Cliente cliente=new Cliente(textNombre.getText(),textApe1.getText(),textApe2.getText(),textDni.getText(),fecha,(String)cbLicencia.getSelectedItem(),textTarjeta.getText());
					if(existe==0) {
						ClienteBD.añadeCliente(cliente);
					}else {
						ClienteBD.modificaCliente(cliente);
					}
					
					MetodosGui.limpiaTexto(contentPane);
					MetodosGui.desactivaFormulario(contentPane);
					textDni.setEnabled(true);
					btnIntroducir.setEnabled(true);
				}catch (Longitud_no_valida e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Longitud del nombre del cliente no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				}catch (Ape1_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Longitud del primer apellido cliente no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				}catch (Ape2_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Longitud del segundo apellido del cliente no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocurrió un error durante la operación","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Carnet_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Carnet introducido no valido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Tarjeta_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Formato de tarjeta no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Dni_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Dni introducido no válido","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(496, 248, 85, 21);
		contentPane.add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni=textDni.getText();
				
				try {
					//JDialog para avisar al usuario de que va a eliminar una oficina de la bd
					Object[] options = {"Aceptar",  "Cancelar",};
					int opc = JOptionPane.showOptionDialog(yo,"¿Desea eliminar este cliente?","AVISO",JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
					
					if(opc==0) {
						
						ClienteBD.eliminarEmpleado(dni);
						MetodosGui.limpiaTexto(contentPane);
						MetodosGui.desactivaFormulario(contentPane);
						textDni.setEnabled(true);
						btnIntroducir.setEnabled(true);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(401, 248, 85, 21);
		contentPane.add(btnEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosGui.desactivaFormulario(contentPane);
				MetodosGui.limpiaTexto(contentPane);
				textDni.setEnabled(true);
				btnIntroducir.setEnabled(true);
				calendarFechaNac.setEnabled(false);
			}
		});
		btnCancelar.setBounds(306, 248, 85, 21);
		contentPane.add(btnCancelar);
		
		cbLicencia = new JComboBox();
		cbLicencia.setModel(new DefaultComboBoxModel(new String[] {"B", "C", "D", "AM", "A1", "A2"}));
		cbLicencia.setBounds(93, 146, 52, 21);
		contentPane.add(cbLicencia);
		
		MetodosGui.desactivaFormulario(contentPane);
		textDni.setEnabled(true);
		
		btnIntroducir = new JButton("");
		btnIntroducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni=textDni.getText();
				Cliente cliente=null;
						try {
							cliente = ClienteBD.listaCliente(dni);
							
							MetodosGui.activaFormulario(contentPane);
							calendarFechaNac.setEnabled(true);
							textDni.setEnabled(false); //desactivamos el dni para que no se pueda cambiar
							if (cliente!=null) {  //Si existe ese empleado en la bd, se rellenan los demás campos con sus datos
								existe=1;
								textNombre.setText(cliente.getNombre());
								textApe1.setText(cliente.getApe1());
								textApe2.setText(cliente.getApe2());
								calendarFechaNac.setDate(cliente.getFecha_nac2());
								cbLicencia.setSelectedItem(cliente.getLicencia());
								textTarjeta.setText(cliente.getTarjeta());
							}
						} catch (Longitud_no_valida | Dni_no_valido | Ape1_no_valido | Ape2_no_valido | Carnet_no_valido
								| Tarjeta_no_valida e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
			}
		}
		);
		btnIntroducir.setIcon(new ImageIcon(VentanaClientes.class.getResource("/general/png/16/zoom.png")));
		btnIntroducir.setBounds(185, 50, 36, 21);
		contentPane.add(btnIntroducir);
		
	}
}
