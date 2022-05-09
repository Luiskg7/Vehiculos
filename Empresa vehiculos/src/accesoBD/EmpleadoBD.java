package accesoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Empleado;
import clases.Oficina;
import clases.Persona;
import exceptions.Dni_no_valido;
import exceptions.Longitud_no_valida;
import gui.VentanaEmpleados;


public class EmpleadoBD {

	public static ArrayList<Empleado> listaOficinas() throws SQLException, Longitud_no_valida, Dni_no_valido {
		
		ArrayList<Empleado> listaEmpleados=new ArrayList<Empleado>();
		ResultSet resultadoSql=Conexion.instruccionSelect(Conexion.conexion,"select dni,nombre,ape1,ape2,fecha_nac,fecha_alta,oficina_trab from persona,empleado,oficina where dni=persona_dni and oficina_codigo=oficina.codigo");
		Empleado empleado;
		while(resultadoSql.next()) {
			empleado=new Empleado(resultadoSql.getString("dni"),resultadoSql.getString("nombre"),resultadoSql.getString("ape1"),resultadoSql.getString("ape2"),resultadoSql.getDate("fecha_nac"),resultadoSql.getDate("fecha_alta"),resultadoSql.getString("oficina_trab"));
			listaEmpleados.add(empleado);
		}
		return listaEmpleados;
	}

	public static Empleado listaEmpleado(String dni) throws Longitud_no_valida, Dni_no_valido {
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
}