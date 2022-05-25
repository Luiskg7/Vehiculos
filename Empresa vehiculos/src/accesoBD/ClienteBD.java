package accesoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import clases.*;
import exceptions.Ape1_no_valido;
import exceptions.Ape2_no_valido;
import exceptions.Carnet_no_valido;
import exceptions.Dni_no_valido;
import exceptions.Longitud_no_valida;
import exceptions.Tarjeta_no_valida;

public class ClienteBD {

public static ArrayList<Cliente> listaClientes() throws SQLException, Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido, Carnet_no_valido, Tarjeta_no_valida {
		
		ArrayList<Cliente> listaEmpleados=new ArrayList<Cliente>();
		ResultSet resultadoSql=Conexion.instruccionSelect(Conexion.conexion,"select dni,nombre,ape1,ape2,fecha_nac,licencia,tarjeta from persona,cliente where dni=persona_dni ");
		Cliente cliente;
		while(resultadoSql.next()) {
			cliente=new Cliente(resultadoSql.getString("nombre"),resultadoSql.getString("ape1"),resultadoSql.getString("ape2"),resultadoSql.getString("dni"),resultadoSql.getDate("fecha_nac"),resultadoSql.getString("licencia"),resultadoSql.getString("tarjeta"));
			listaEmpleados.add(cliente);
		}
		return listaEmpleados;
	}
	
	
	public static Cliente listaCliente(String dni) throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido, Carnet_no_valido, Tarjeta_no_valida {
		ResultSet resultadoSql=null;
		Cliente cliente=null;
		try {
			//java.sql.Date fecha=new java.sql.Date(calendarFechaNac.)
			PreparedStatement ps=Conexion.conexion.prepareStatement("select dni,nombre,ape1,ape2,fecha_nac,licencia,tarjeta from persona,cliente where dni=? and persona_dni=dni");
			ps.setString(1, dni);
			resultadoSql=ps.executeQuery();
			while (resultadoSql.next()) {
				//si entra en el bucle, quiere decir que si existe el cliente
				cliente=new Cliente(resultadoSql.getString("nombre"),resultadoSql.getString("ape1"),resultadoSql.getString("ape2"),dni,resultadoSql.getDate("fecha_nac"),resultadoSql.getString("licencia"),resultadoSql.getString("tarjeta"));
			}
		} catch (SQLException e) {

		}
		return cliente;
	}
	
	public static void añadeCliente(Cliente cliente) throws SQLException {
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO persona VALUES (?,?,?,?,?)");
		ps.setString(1, cliente.getDni());
		ps.setString(2, cliente.getNombre());
		ps.setString(3, cliente.getApe1());
		ps.setString(4, cliente.getApe2());
		ps.setDate(5, cliente.getFecha_nac2());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO cliente VALUES(?,?,?)");
		ps.setString(1, cliente.getLicencia());
		ps.setString(2, cliente.getTarjeta());
		ps.setString(3,cliente.getDni());
		
		ps.executeUpdate();
	}
	
	public static void modificaCliente(Cliente cliente) throws SQLException {
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("UPDATE persona SET nombre=?, ape1=?, ape2=?, fecha_nac=? where dni=?");
		ps.setString(1, cliente.getNombre());
		ps.setString(2, cliente.getApe1());
		ps.setString(3, cliente.getApe2());
		ps.setDate(4, cliente.getFecha_nac2());
		ps.setString(5, cliente.getDni());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE cliente SET licencia=?,tarjeta=? where persona_dni=?");
		ps.setString(1, cliente.getLicencia());
		ps.setString(2,cliente.getTarjeta());
		ps.setString(3, cliente.getDni());
		ps.executeUpdate();
		
	}
	
	public static void eliminarEmpleado(String dni) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("delete persona,cliente from persona join cliente on persona_dni=dni where dni=?");
		ps.setString(1, dni);
		ps.executeUpdate();
	}
}
