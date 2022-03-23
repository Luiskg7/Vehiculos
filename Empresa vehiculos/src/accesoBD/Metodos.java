package accesoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Metodos {


	/**
	 * Conecta con la base de datos MySQL de la empresa de vehiculos localhost a través del usuario
	 * root.
	 * @return Objeto de tipo Connection para poder operar con consultas
	 */
	public static Connection conexion(){
		Connection conexion =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //driver MySQL
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo","root","22Marzo2001."); //conexion a través del usuario y su contraseña
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conexion;
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
	
}
