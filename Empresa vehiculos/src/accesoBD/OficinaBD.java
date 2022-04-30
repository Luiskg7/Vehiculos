package accesoBD;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import clases.Oficina;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Localidad_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;

public class OficinaBD {
	
	
	/**
	 * Devuelve todas las oficinas que contiene la base de datos a través de un ArrayList de objetos Oficinas
	 * @return ArrayList<Oficinas>
	 * @throws SQLException
	 * @throws Descripcion_no_valida
	 * @throws Localidad_no_valida
	 * @throws Provincia_no_valida
	 * @throws Opcion_no_valida
	 * @throws Codigo_no_valido
	 */
	public static ArrayList<Oficina> listaOficinas() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		
		ArrayList<Oficina> listaOficina=new ArrayList<Oficina>();
		ResultSet resultadoSql=Conexion.instruccionSelect(Conexion.conexion,"SELECT * FROM oficina");
		Oficina oficina;
		while(resultadoSql.next()) {
			oficina=new Oficina(resultadoSql.getString("codigo"),resultadoSql.getString("descripcion"),resultadoSql.getString("localidad"),resultadoSql.getString("provincia"),resultadoSql.getBoolean("aeropuerto"));
			listaOficina.add(oficina);
		}
		return listaOficina;
	}
	
	/**
	 * A través de un código de una oficina,devuelve un objeto oficina que contenga dicho codigo.Si no encuentra
	 * ninguna oficina devolverá el objeto nulo.
	 * @param codigo String que hace referencia al codigo de una oficina
	 * @return Objeto de tipo oficina
	 * @throws Descripcion_no_valida
	 * @throws Localidad_no_valida
	 * @throws Provincia_no_valida
	 * @throws Opcion_no_valida
	 * @throws Codigo_no_valido
	 */
	public static Oficina listaOficina(String codigo) throws Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		ResultSet resultadoSql=null;
		Oficina oficina=null;
		boolean existe=false; //saber si el código está en la base de datos
		boolean valido=true; //comprobar si el código introducido es válido en el caso de ser uno nuevo
		try {
			PreparedStatement ps=Conexion.conexion.prepareStatement("SELECT * FROM oficina where codigo=?");
			ps.setString(1, codigo);
			resultadoSql=ps.executeQuery();
			while (resultadoSql.next()) {
				existe=true; //si entra en el bucle, quiere decir que si existe el codigo
				oficina=new Oficina(resultadoSql.getString("codigo"),resultadoSql.getString("descripcion"),resultadoSql.getString("localidad"),resultadoSql.getString("provincia"),resultadoSql.getBoolean("aeropuerto"));
			}
			
			if(!existe) {
				Oficina.validaCodigo(codigo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return oficina;
	}
	
	/**
	 * Devuelve un ComboBox con todas las oficinas de la base de datos
	 * @return ComboBox
	 * @throws SQLException
	 * @throws Descripcion_no_valida
	 * @throws Localidad_no_valida
	 * @throws Provincia_no_valida
	 * @throws Opcion_no_valida
	 * @throws Codigo_no_valido
	 */
	public static JComboBox cbOficinas() throws SQLException, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		JComboBox cbOficinas = new JComboBox();
		DefaultComboBoxModel d=new DefaultComboBoxModel();
		d.addAll(OficinaBD.listaOficinas());
		cbOficinas.setModel(d);
		
		return cbOficinas;
	}
	
	public static void añadeOficina(String codigo,String descripcion,String provincia,String localidad,boolean aeropuerto) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO oficina VALUES (?,?,?,?,?)");
		ps.setString(1, codigo);
		ps.setString(2, descripcion);
		ps.setString(3, localidad);
		ps.setString(4, provincia);
		ps.setBoolean(5, aeropuerto);
		
		
		ps.executeUpdate();
		
	}
	
	
}
