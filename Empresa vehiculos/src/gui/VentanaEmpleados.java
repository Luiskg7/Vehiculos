package gui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoBD.Conexion;
import accesoBD.EmpleadoBD;
import accesoBD.OficinaBD;
import clases.Empleado;
import clases.Oficina;
import clases.Persona;
import exceptions.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEmpleados extends JFrame {

	private JPanel contentPane;
	private int existe=0;
	private JButton btnIntroducir;
	private JTextField textDni;

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
	 * @throws Dni_no_valido 
	 * @throws Longitud_no_valida 
	 */
	public VentanaEmpleados() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido, Dni_no_valido, Longitud_no_valida {
		JFrame yo=this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 445);
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
		lblFecha_Nac.setBounds(360, 35, 118, 13);
		contentPane.add(lblFecha_Nac);
		
		JLabel lblFecha_Alta = new JLabel("Fecha de alta");
		lblFecha_Alta.setBounds(393, 186, 90, 13);
		contentPane.add(lblFecha_Alta);
		
		textDni = new JTextField();
		textDni.setBounds(70, 32, 96, 19);
		contentPane.add(textDni);
		textDni.setColumns(10);
		
		JTextField textNombre = new JTextField();
		textNombre.setEnabled(false);
		textNombre.setBounds(94, 55, 96, 19);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JTextField textApe1 = new JTextField();
		textApe1.setEnabled(false);
		textApe1.setBounds(120, 78, 96, 19);
		contentPane.add(textApe1);
		textApe1.setColumns(10);
		
		JTextField textApe2 = new JTextField();
		textApe2.setEnabled(false);
		textApe2.setBounds(145, 101, 96, 19);
		contentPane.add(textApe2);
		textApe2.setColumns(10);
		
		JCalendar calendarNac = new JCalendar();
		calendarNac.setEnabled(false);
		calendarNac.setBounds(488, 35, 223, 139);
		contentPane.add(calendarNac);
		
		JCalendar calendarAlta = new JCalendar();
		calendarAlta.setEnabled(false);
		calendarAlta.setBounds(493, 184, 218, 139);
		contentPane.add(calendarAlta);
		
		JLabel lblOficina = new JLabel("Oficina de trabajo");
		lblOficina.setBounds(27, 127, 118, 13);
		contentPane.add(lblOficina);
		
		JComboBox cbOficinas = new JComboBox();
		cbOficinas.setEnabled(false);
		cbOficinas.setBounds(145, 130, 173, 21);
		DefaultComboBoxModel d=new DefaultComboBoxModel();
		d.addAll(OficinaBD.listaOficinas());
		cbOficinas.setModel(d);
		contentPane.add(cbOficinas);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaOficinas.desactivaFormulario(contentPane);
				VentanaOficinas.limpiaTexto(contentPane);
				textDni.setEnabled(true);
				btnIntroducir.setEnabled(true);
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(410, 377, 85, 21);
		contentPane.add(btnCancelar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni=textDni.getText();
				
				try {
					//JDialog para avisar al usuario de que va a eliminar una oficina de la bd
					Object[] options = {"Aceptar",  "Cancelar",};
					int opc = JOptionPane.showOptionDialog(yo,"¿Desea eliminar este empleado?","AVISO",JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
					
					if(opc==0) {
						Conexion.desactivarFK();
						PreparedStatement ps=Conexion.conexion.prepareStatement("delete persona,empleado from persona join empleado on persona_dni=dni where dni=?");
						ps.setString(1, dni);
						ps.executeUpdate();
						Conexion.activarFK();
						VentanaOficinas.limpiaTexto(contentPane);
						VentanaOficinas.desactivaFormulario(contentPane);
						textDni.setEnabled(true);
						btnIntroducir.setEnabled(true);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(513, 377, 85, 21);
		contentPane.add(btnEliminar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni=textDni.getText();
				String nombre=textNombre.getText();
				String ape1=textApe1.getText();
				String ape2=textApe2.getText();
				Oficina oficina=(Oficina)cbOficinas.getSelectedItem();
				Date fecha_nac=(Date) calendarNac.getDate();
				Date fecha_alta=(Date) calendarAlta.getDate();
				
				try {
					Persona.validaNombre(nombre);
					Persona.validaApe1(ape1);
					Persona.validaApe2(ape2);
					
					if(existe==1) {
						EmpleadoBD.modificaEmpleado(dni, nombre, ape1, ape2, oficina, fecha_nac, fecha_alta);
					}else {
						EmpleadoBD.añadeEmpleado(dni, nombre, ape1, ape2, oficina, fecha_nac, fecha_alta);
					}
					
					VentanaOficinas.limpiaTexto(contentPane);
					VentanaOficinas.desactivaFormulario(contentPane);
					textDni.setEnabled(true);
					btnIntroducir.setEnabled(true);
				}catch (Longitud_no_valida e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Longitud del nombre del empleado no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				}catch (Ape1_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Longitud del primer apellido empleado no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				}catch (Ape2_no_valido e1) {
					JOptionPane.showMessageDialog(null, "Longitud del segundo apellido del empleado no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocurrió un error durante la operación","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
				
					
				
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(608, 377, 85, 21);
		contentPane.add(btnGuardar);
		
		btnIntroducir = new JButton("");
		btnIntroducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni=textDni.getText();
				Empleado empleado=null;
				
				try {
					Persona.validaDNI(dni);
					empleado = EmpleadoBD.listaEmpleado(dni);
					
					VentanaOficinas.activaFormulario(contentPane);
					calendarNac.setEnabled(true);
					calendarAlta.setEnabled(true);
					textDni.setEnabled(false); //desactivamos el dni para que no se pueda cambiar
					if (empleado!=null) {  //Si existe ese empleado en la bd, se rellenan los demás campos con sus datos
						existe=1;
						textNombre.setText(empleado.getNombre());
						textApe1.setText(empleado.getApe1());
						textApe2.setText(empleado.getApe2());
						cbOficinas.setSelectedItem(OficinaBD.listaOficinaDesc(empleado.getOficina_trab()));
						calendarAlta.setDate(empleado.getFecha_alta2());
						calendarNac.setDate(empleado.getFecha_nac2());
						
						
					}
				} catch (Longitud_no_valida | Dni_no_valido | Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida | Opcion_no_valida | Codigo_no_valido  | Ape1_no_valido |Ape2_no_valido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
							
			}
		}
		);
				
			
		
		btnIntroducir.setIcon(new ImageIcon(VentanaEmpleados.class.getResource("/general/png/16/zoom.png")));
		btnIntroducir.setBounds(191, 31, 28, 19);
		contentPane.add(btnIntroducir);
		
		
		//Colocar el frame en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		
		
	}
}
