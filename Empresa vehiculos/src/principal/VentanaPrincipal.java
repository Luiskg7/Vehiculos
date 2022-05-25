package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoBD.Conexion;
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
import gui.ListaCategorias;
import gui.ListadoAlquileres;
import gui.ListadoClientes;
import gui.ListadoEmpleados;
import gui.ListadoOficinas;
import gui.ListadoVehiculos;
import gui.MetodosGui;
import gui.VentanaAlquileres;
import gui.VentanaCategorias;
import gui.VentanaClientes;
import gui.VentanaEmpleados;
import gui.VentanaOficinas;
import gui.VentanaVehiculo;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion.conexion();
					VentanaPrincipal frame=new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/general/png/logo.png")));
		setTitle("Principal");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 568);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		JMenuItem mntmOficina = new JMenuItem("Oficinas");
		mntmOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						VentanaOficinas oficinas= new VentanaOficinas();
						oficinas.setVisible(true);
					} catch (SQLException | Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida
							| Opcion_no_valida | Codigo_no_valido e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		mnMantenimiento.add(mntmOficina);
		
		JMenuItem mntmCategoria = new JMenuItem("Categorias");
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaCategorias categorias=new VentanaCategorias();
				categorias.setVisible(true);
			}
		});
		mnMantenimiento.add(mntmCategoria);
		
		JMenu mnPersona = new JMenu("Personas");
		mnMantenimiento.add(mnPersona);
		
		JMenuItem mntmEmpleado = new JMenuItem("Empleados");
		mntmEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					VentanaEmpleados empleados=empleados = new VentanaEmpleados();
					empleados.setVisible(true);
				} catch (SQLException | Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida
						| Opcion_no_valida | Codigo_no_valido | Dni_no_valido | Longitud_no_valida e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnPersona.add(mntmEmpleado);
		
		JMenuItem mntmCliente = new JMenuItem("Clientes");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaClientes clientes=new VentanaClientes();
				clientes.setVisible(true);
			}
		});
		mnPersona.add(mntmCliente);
		
		JMenuItem mntmVehiculo = new JMenuItem("Vehiculos");
		mntmVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					VentanaVehiculo vehiculos=vehiculos = new VentanaVehiculo();
					vehiculos.setVisible(true);
				} catch (SQLException | Codigo_no_valido | Descripcion_no_valida | Recargo_no_valido
						| Localidad_no_valida | Provincia_no_valida | Opcion_no_valida e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnMantenimiento.add(mntmVehiculo);
		
		JMenu mnListado = new JMenu("Listados");
		menuBar.add(mnListado);
		
		JMenuItem mntmOficina2 = new JMenuItem("Oficinas");
		mntmOficina2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListadoOficinas lista=new ListadoOficinas();
					lista.setVisible(true);
				} catch (SQLException | Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida
						| Opcion_no_valida | Codigo_no_valido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnListado.add(mntmOficina2);
		
		JMenuItem mntmCategoria2 = new JMenuItem("Categorias");
		mntmCategoria2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCategorias lista;
				try {
					lista = new ListaCategorias();
					lista.setVisible(true);
				} catch (SQLException | Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida
						| Opcion_no_valida | Codigo_no_valido | Recargo_no_valido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnListado.add(mntmCategoria2);
		
		JMenu mnPersona2 = new JMenu("Personas");
		mnListado.add(mnPersona2);
		
		JMenuItem mntmEmpleado2 = new JMenuItem("Empleados");
		mntmEmpleado2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoEmpleados lista;
				try {
					lista = new ListadoEmpleados();
					lista.setVisible(true);
				} catch (SQLException | Longitud_no_valida | Dni_no_valido | Ape1_no_valido | Ape2_no_valido
						| Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida | Opcion_no_valida
						| Codigo_no_valido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnPersona2.add(mntmEmpleado2);
		
		JMenuItem mntmCliente2 = new JMenuItem("Clientes");
		mntmCliente2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoClientes lista;
				try {
					lista = new ListadoClientes();
					lista.setVisible(true);
				} catch (SQLException | Longitud_no_valida | Dni_no_valido | Ape1_no_valido | Ape2_no_valido
						| Carnet_no_valido | Tarjeta_no_valida e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnPersona2.add(mntmCliente2);
		
		JMenuItem mntmVehiculo2 = new JMenuItem("Vehiculos");
		mntmVehiculo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoVehiculos lista;
				try {
					lista = new ListadoVehiculos();
					lista.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnListado.add(mntmVehiculo2);
		
		JMenuItem mntmAlquiler = new JMenuItem("Alquileres");
		mntmAlquiler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoAlquileres lista;
				try {
					lista = new ListadoAlquileres();
					lista.setVisible(true);
				} catch (Longitud_no_valida | Dni_no_valido | Ape1_no_valido | Ape2_no_valido | Carnet_no_valido
						| Tarjeta_no_valida | Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida
						| Opcion_no_valida | Codigo_no_valido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnListado.add(mntmAlquiler);
		
		JMenuItem mntmAlquilar = new JMenuItem("Alquilar");
		mntmAlquilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaAlquileres alquileres=new VentanaAlquileres();
					alquileres.setVisible(true);
				} catch (SQLException | Descripcion_no_valida | Localidad_no_valida | Provincia_no_valida
						| Opcion_no_valida | Codigo_no_valido | Recargo_no_valido | Km_no_valido | Matricula_no_valida
						| Marca_no_valida | Modelo_no_valido | Color_no_valido | Autonomia_no_valida | Recarga_no_valida
						| Plazas_no_validas | Tipo_no_valido | Longitud_no_valida | Dni_no_valido | Ape1_no_valido
						| Ape2_no_valido | Carnet_no_valido | Tarjeta_no_valida e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuBar.add(mntmAlquilar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/fondo2.png")));
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		MetodosGui.centraVentana(this);
	}
	
	 

}
