package accesoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	//propiedades estaticas
	public static Connection conexion;
	public static Statement st;
	
	/**
	 * Método que conecta con la base de datos
	 */
	public static void conexion(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //driver MySQL
			conexion =DriverManager.getConnection("jdbc:mysql://localhost:3306/empresaVehiculos","root","22Marzo2001."); //conexion a través del usuario y su contraseña
			st=Conexion.conexion.createStatement();//Orden creada para realizar consultas
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Realiza una consulta de tipo SELECT a una base de datos introducida por parámetro.
	 * @param conexion Objeto de tipo Connection referente a la base de datos a ejecutar.
	 * @param query String de la consulta a realizar.
	 * @return Objeto de tipo ResulSet con el resultado del SELECT introducido
	 */
	public static ResultSet instruccionSelect(Connection conexion,String query){
		Statement instruccion;
		ResultSet resultado = null;
		try {
			instruccion = conexion.createStatement();
			resultado=instruccion.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public static void desactivarFK() throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
	}
	
	public static void activarFK() throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
	}
}
