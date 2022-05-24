package accesoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.Alquiler;
import clases.Empleado;

public class AlquilerBD {

	public static Alquiler listaAlquiler(int codigo) {
		ResultSet resultadoSql=null;
		Alquiler alquiler=null;
		try {
			PreparedStatement ps=Conexion.conexion.prepareStatement("select * from alquiler where codigo=?");
			ps.setInt(1, codigo);
			resultadoSql=ps.executeQuery();
			while (resultadoSql.next()) {
				//si entra en el bucle, quiere decir que si existe el empleado
				alquiler=new Alquiler(codigo,VehiculoBD.listaVehiculo(resultadoSql.getString("matricula")),EmpleadoBD.listaEmpleado(resultadoSql.getString("persona_dni")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alquiler;
	}
	
}
