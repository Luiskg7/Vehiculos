package accesoBD;

import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Empleado;
import clases.Oficina;
import clases.Persona;
import exceptions.Ape1_no_valido;
import exceptions.Ape2_no_valido;
import exceptions.Dni_no_valido;
import exceptions.Longitud_no_valida;
import gui.VentanaEmpleados;


public class EmpleadoBD {

	public static ArrayList<Empleado> listaOficinas() throws SQLException, Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido {
		
		ArrayList<Empleado> listaEmpleados=new ArrayList<Empleado>();
		ResultSet resultadoSql=Conexion.instruccionSelect(Conexion.conexion,"select dni,nombre,ape1,ape2,fecha_nac,fecha_alta,oficina_trab from persona,empleado,oficina where dni=persona_dni and oficina_codigo=oficina.codigo");
		Empleado empleado;
		while(resultadoSql.next()) {
			empleado=new Empleado(resultadoSql.getString("dni"),resultadoSql.getString("nombre"),resultadoSql.getString("ape1"),resultadoSql.getString("ape2"),resultadoSql.getDate("fecha_nac"),resultadoSql.getDate("fecha_alta"),resultadoSql.getString("oficina_trab"));
			listaEmpleados.add(empleado);
		}
		return listaEmpleados;
	}

	public static Empleado listaEmpleado(String dni) throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido {
		ResultSet resultadoSql=null;
		Empleado empleado=null;
		try {
			PreparedStatement ps=Conexion.conexion.prepareStatement("select dni,nombre,ape1,ape2,fecha_nac,fecha_alta,oficina_trab from persona,empleado,oficina where dni=? and dni=persona_dni and oficina_codigo=oficina.codigo");
			ps.setString(1, dni);
			resultadoSql=ps.executeQuery();
			while (resultadoSql.next()) {
				//si entra en el bucle, quiere decir que si existe el empleado
				empleado=new Empleado(resultadoSql.getString("nombre"),resultadoSql.getString("ape1"),resultadoSql.getString("ape2"),dni,resultadoSql.getDate("fecha_nac"),resultadoSql.getDate("fecha_alta"),resultadoSql.getString("oficina_trab"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empleado;
	}
	
	public static void añadeEmpleado(String dni,String nombre,String ape1,String ape2,Oficina oficina,Date fecha_nac,Date fecha_alta) throws SQLException {
		String ofDesc=oficina.getDescripcion();
		String ofCod=oficina.getCodigo();
		long segundos_nac=fecha_nac.getTime();
		long segundos_alta=fecha_alta.getTime();
		java.sql.Date nac=new java.sql.Date(segundos_nac);
		java.sql.Date alta=new java.sql.Date(segundos_alta);
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO persona VALUES (?,?,?,?,?)");
		ps.setString(1, dni);
		ps.setString(2, nombre);
		ps.setString(3, ape1);
		ps.setString(4, ape2);
		ps.setDate(5, nac);
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO empleado VALUES(?,?,?,?)");
		ps.setDate(1, alta);
		ps.setString(2, ofDesc);
		ps.setString(3,dni);
		ps.setString(4,ofCod);
		
		ps.executeUpdate();
	}
	
	public static void modificaEmpleado(String dni,String nombre,String ape1,String ape2,Oficina oficina,Date fecha_nac,Date fecha_alta) throws SQLException {
		String ofDesc=oficina.getDescripcion();
		String ofCod=oficina.getCodigo(); 
		long segundos_nac=fecha_nac.getTime();
		long segundos_alta=fecha_alta.getTime();
		java.sql.Date nac=new java.sql.Date(segundos_nac);
		java.sql.Date alta=new java.sql.Date(segundos_alta);
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("UPDATE persona SET nombre=?, ape1=?, ape2=?, fecha_nac=? where dni=?");
		ps.setString(1, nombre);
		ps.setString(2, ape1);
		ps.setString(3, ape2);
		ps.setDate(4, nac);
		ps.setString(5, dni);
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE empleado SET fecha_alta=?,oficina_trab=?,oficina_codigo=? where persona_dni=?");
		ps.setDate(1, alta);
		ps.setString(2,ofDesc);
		ps.setString(3, ofCod);
		ps.setString(4, dni);
		ps.executeUpdate();
		
	}
	
	public static void eliminarEmpleado(String dni) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("delete persona,empleado from persona join empleado on persona_dni=dni where dni=?");
		ps.setString(1, dni);
		ps.executeUpdate();
	}
}
