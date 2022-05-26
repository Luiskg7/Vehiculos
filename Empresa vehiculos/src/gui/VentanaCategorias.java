package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoBD.CategoriaBD;
import accesoBD.Conexion;
import clases.Categoria;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Recargo_no_valido;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class VentanaCategorias extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textDescripcion;
	private JTextField textRecargo;
	private JButton btnIntroducir;
	private int existe=0;

	/**
	 * Create the frame.
	 */
	public VentanaCategorias() {
		JFrame yo=this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(24, 24, 45, 13);
		contentPane.add(lblCodigo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(24, 47, 72, 13);
		contentPane.add(lblDescripcion);
		
		JLabel lblRecargo = new JLabel("Recargo");
		lblRecargo.setBounds(24, 70, 65, 13);
		contentPane.add(lblRecargo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {//Limpia y deshabilita los campos menos el codigo
			public void actionPerformed(ActionEvent e) {
				MetodosGui.desactivaFormulario(contentPane);
				MetodosGui.limpiaTexto(contentPane);
				textCodigo.setEnabled(true);
				btnIntroducir.setEnabled(true);
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(156, 232, 85, 21);
		contentPane.add(btnCancelar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo=textCodigo.getText();
				
				try {
					//JDialog para avisar al usuario de que va a eliminar una categoria de la bd
					Object[] options = {"Aceptar",  "Cancelar",};
					int opc = JOptionPane.showOptionDialog(yo,"¿Desea eliminar esta categoria? Los vehiculos que sean de esta categoria también serán eliminados","AVISO",JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
					
					if(opc==0) {//Si se introduce que si se quiere eliminar, se elimina la categoria y limpia los campos
						CategoriaBD.eliminaCategoria(codigo);
						MetodosGui.limpiaTexto(contentPane);
						MetodosGui.desactivaFormulario(contentPane);
						textCodigo.setEnabled(true);
						btnIntroducir.setEnabled(true);
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}	
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(251, 232, 85, 21);
		contentPane.add(btnEliminar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int recargo=Integer.parseInt(textRecargo.getText());
				
				try {
					Categoria categoria=new Categoria(textCodigo.getText(),textDescripcion.getText(),recargo);
					if(existe==1) {//Dependiendo de si existe o no la categoria,se añadirá o modificará
						CategoriaBD.modificaCategoria(categoria);
					}else {
						CategoriaBD.añadeCategoria(categoria);
					}
					MetodosGui.desactivaFormulario(contentPane);
					MetodosGui.limpiaTexto(contentPane);
					textCodigo.setEnabled(true);
					btnIntroducir.setEnabled(true);
				}catch(Descripcion_no_valida e1) {
					JOptionPane.showMessageDialog(null, "La longitud de la descripción no es válida","ERROR",JOptionPane.ERROR_MESSAGE);
				}catch(Recargo_no_valido e2) {
					JOptionPane.showMessageDialog(null, "El recargo debe ser entre 0 y 100","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				} catch (Codigo_no_valido e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(346, 232, 85, 21);
		contentPane.add(btnGuardar);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(97, 21, 96, 19);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setEnabled(false);
		textDescripcion.setBounds(97, 44, 96, 19);
		contentPane.add(textDescripcion);
		textDescripcion.setColumns(10);
		
		textRecargo = new JTextField();
		textRecargo.setEnabled(false);
		textRecargo.setBounds(97, 67, 96, 19);
		contentPane.add(textRecargo);
		textRecargo.setColumns(10);
		
		btnIntroducir = new JButton("");
		btnIntroducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo=textCodigo.getText();
				Categoria categoria=null;
				
				try {
					Categoria.validaCodigo(codigo);//Comprueba que el código para buscar o añadir es válido
					categoria=CategoriaBD.listaCategoria(codigo);//Busca si el código está en la bd
					MetodosGui.activaFormulario(contentPane);
					textCodigo.setEnabled(false);
					
					if(categoria!=null) {//Si existe el código se rellenan los campos con los datos de la categoria
						existe=1;
						textCodigo.setEnabled(false);
						textDescripcion.setText(categoria.getDescripcion());
						textRecargo.setText(String.valueOf(categoria.getRecargo()));
					}
				} catch (Codigo_no_valido | Descripcion_no_valida | Recargo_no_valido e1) {
					JOptionPane.showMessageDialog(null, "El código introducido no es válido,introduzca una sola letra","ERROR",JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		btnIntroducir.setIcon(new ImageIcon(VentanaCategorias.class.getResource("/general/png/16/zoom.png")));
		btnIntroducir.setBounds(203, 19, 24, 21);
		contentPane.add(btnIntroducir);
		
		//Colocar el frame en el centro de la pantalla
		MetodosGui.centraVentana(yo);
	}
}
