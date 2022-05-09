package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.*;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Localidad_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;
import accesoBD.*;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaOficinas extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textDescripcion;
	private JTextField textLocalidad;
	private JButton btnIntroducir;
	private JComboBox cbProvincia;
	private int existe=0; //variable para ver si la oficina existe o no para crear una nueva o modificar la existente
	private String codigo;
	private JCheckBox chckbxAeropuerto;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion.conexion();
					VentanaOficinas frame = new VentanaOficinas();
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
	public VentanaOficinas() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		JFrame yo=this;
		setTitle("Oficina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(21, 46, 66, 13);
		contentPane.add(lblCodigo);
		
		JLabel lblDescripción = new JLabel("Descripci\u00F3n");
		lblDescripción.setBounds(21, 69, 69, 13);
		contentPane.add(lblDescripción);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(21, 115, 69, 13);
		contentPane.add(lblLocalidad);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(21, 92, 69, 13);
		contentPane.add(lblProvincia);
		
		JLabel lblAeropuerto = new JLabel("Aeropuerto");
		lblAeropuerto.setBounds(21, 138, 69, 13);
		contentPane.add(lblAeropuerto);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(97, 43, 96, 19);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setEnabled(false);
		textDescripcion.setBounds(97, 66, 96, 19);
		contentPane.add(textDescripcion);
		textDescripcion.setColumns(10);
		
		cbProvincia = new JComboBox();
		cbProvincia.setEnabled(false);
		cbProvincia.setModel(new DefaultComboBoxModel(new String[] {"Alava", "Albacete", "Alicante", "Almer\u00EDa", "Asturias", "Avila", "Badajoz", "Barcelona", "Burgos", "Caceres", "Cadiz", "Cantabria", "Castellon", "Ciudad Real", "Cordoba", "Cuenca", "Gerona", "Granada", "Guadalajara", "Guipuzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaen", "La Coru\u00F1a", "La Rioja", "Las Palmas", "Leon", "Lerida", "Lugo", "Madrid", "Malaga", "Murcia", "Navarra", "Orense", "Palencia", "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia", "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza"}));
		cbProvincia.setSelectedIndex(-1);
		cbProvincia.setBounds(97, 88, 96, 21);
		contentPane.add(cbProvincia);
		
		textLocalidad = new JTextField();
		textLocalidad.setEnabled(false);
		textLocalidad.setBounds(97, 112, 96, 19);
		contentPane.add(textLocalidad);
		textLocalidad.setColumns(10);
		
		chckbxAeropuerto = new JCheckBox("");
		chckbxAeropuerto.setEnabled(false);
		chckbxAeropuerto.setBounds(97, 138, 21, 21);
		contentPane.add(chckbxAeropuerto);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String descripcion=textDescripcion.getText();
				String provincia=(String) cbProvincia.getSelectedItem();
				String localidad=textLocalidad.getText();
				boolean aeropuerto=chckbxAeropuerto.isSelected();
				
				try {
					Oficina.validaDescripcion(descripcion);
					Oficina.validaProvincia(provincia);
					Oficina.validaLocalidad(localidad);
					if(existe==1) {
						OficinaBD.modificaOficina(codigo, descripcion, provincia, localidad, aeropuerto);
					}else {
						OficinaBD.añadeOficina(textCodigo.getText(), descripcion, provincia, localidad, aeropuerto);
					}
					
					
					limpiaTexto(contentPane);
					desactivaFormulario(contentPane);
					textCodigo.setEnabled(true);
					btnIntroducir.setEnabled(true);
				}catch(SQLException a) {
					a.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocurrió un error durante la operación","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Descripcion_no_valida e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Longitud de la descripción introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Localidad_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Longitud de la localidad introducida no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (Provincia_no_valida e1) {
					JOptionPane.showMessageDialog(null, "Provincia no válida","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(424, 232, 85, 21);
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo=textCodigo.getText();
				try {
					PreparedStatement ps=Conexion.conexion.prepareStatement("DELETE FROM oficina where codigo=?");
					ps.setString(1, codigo);
					
					//JDialog para avisar al usuario de que va a eliminar una oficina de la bd
					Object[] options = {"Aceptar",  "Cancelar",};
					int opc = JOptionPane.showOptionDialog(yo,"¿Desea eliminar esta oficina?","AVISO",JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
					
					if (opc==0) {
						ps.executeUpdate();//elimina la oficina
						limpiaTexto(contentPane);
						desactivaFormulario(contentPane);
						textCodigo.setEnabled(true);
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminar.setBounds(329, 232, 85, 21);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desactivaFormulario(contentPane);
				limpiaTexto(contentPane);
				textCodigo.setEnabled(true);
				btnIntroducir.setEnabled(true);
				
				
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(234, 232, 85, 21);
		contentPane.add(btnCancelar);
		
		btnIntroducir = new JButton("Introducir");
		btnIntroducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codigo=textCodigo.getText();
				Oficina oficina=null;
				try {
					oficina = OficinaBD.listaOficina(codigo); //guardamos el resultado de buscar la oficina a través del código
					activaFormulario(contentPane);
					textCodigo.setEnabled(false); //desactivamos el codigo para que no se pueda cambiar
					if (oficina!=null) {  //Si existe esa oficina en la bd, se rellenan los demás campos con sus datos
						existe=1;
						textDescripcion.setText(oficina.getDescripcion());
						cbProvincia.setSelectedItem(oficina.getProvincia());
						textLocalidad.setText(oficina.getLocalidad());
						if(oficina.getAeropuerto()) {
							chckbxAeropuerto.setSelected(true);
						}
						btnEliminar.setEnabled(true);
						
					}
				} catch (Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida | Opcion_no_valida
						| Codigo_no_valido e1) {
					// En caso de que el código introducido no sea válido se mostrara este mensaje de error
					JOptionPane.showMessageDialog(null, "Código introducido no válido","Error",JOptionPane.ERROR_MESSAGE);
				}		
				
			}
		});
		btnIntroducir.setBounds(203, 42, 106, 21);
		contentPane.add(btnIntroducir);
		
		
		
		//Colocar el frame en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	public static void desactivaFormulario (JPanel p)
	{
		for (Component c:p.getComponents())
		{
			if(!(c instanceof JLabel)) {
				c.setEnabled(false);
			}
			//ver si el componente es otro panel para recorrerlo
			if(c instanceof JPanel) {
				desactivaFormulario((JPanel)c);
			}
			
		}
		
	}
	
	public static void activaFormulario (JPanel p)
	{
		for (Component c:p.getComponents())
		{
			if(!(c instanceof JLabel)) {
				c.setEnabled(true);
			}
			//ver si el componente es otro panel para recorrerlo
			if(c instanceof JPanel) {
				desactivaFormulario((JPanel)c);
			}
			
		}
		
	}
	
	public static void limpiaTexto(JPanel p)
	{
		for (Component c:p.getComponents())
		{
			if(c instanceof JTextField) {
				 ((JTextField) c).setText("");
			}
			
			if(c instanceof JCheckBox) {
				((JCheckBox) c).setSelected(false);
			}
			
			if(c instanceof JComboBox) {
				((JComboBox) c).setSelectedIndex(-1);
			}
		}
	}
}
