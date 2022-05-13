package accesoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import clases.*;

public class VehiculoBD {
	
	public static Vehiculo listaVehiculo(String matricula) {
		Vehiculo vehiculo=null;
		Categoria categoria=null;
		Oficina oficina=null;
		ResultSet resultadoSql=null;
		ResultSet tipo=null;
		ResultSet tipo2=null;
		boolean hay=false;
		boolean hay2=false;
		int tipofinal=-1;
		
		try {
			PreparedStatement ps=Conexion.conexion.prepareStatement("SELECT * FROM vehiculos WHERE matricula=?");
			ps.setString(1, matricula);
			resultadoSql=ps.executeQuery();
			
			if(resultadoSql.next()) {
				ps=Conexion.conexion.prepareStatement("SELECT * FROM combustion WHERE vehiculos_matricula=?");
				ps.setString(1, matricula);
				tipo=ps.executeQuery();
				categoria=CategoriaBD.listaCategoria(resultadoSql.getString("categoria_codigo"));
				oficina=OficinaBD.listaOficina(resultadoSql.getString("oficina_codigo"));
				
				if(tipo.next()) {
					hay=true;
					ps=Conexion.conexion.prepareStatement("SELECT * FROM coche_combustion where combustion_vehiculos_matricula=?");
					ps.setString(1, matricula);
					tipo2=ps.executeQuery();
					
					if(tipo2.next()) {
						hay2=true;
						tipofinal=1;
					}
					
					if(!hay2) {
						ps=Conexion.conexion.prepareStatement("SELECT * FROM furgoneta where combustion_vehiculos_matricula=?");
						ps.setString(1, matricula);
						tipo2=ps.executeQuery();
						tipofinal=2;
					}
				}
				
				if(!hay) {
					ps=Conexion.conexion.prepareStatement("SELECT * FROM electrico where vehiculos_matricula=?");
					ps.setString(1, matricula);
					tipo=ps.executeQuery();
					
					if(tipo.next()) {
						ps=Conexion.conexion.prepareStatement("SELECT * FROM coche_electrico where electrico_vehiculos_matricula=?");
						ps.setString(1, matricula);
						tipo2=ps.executeQuery();
						
						if(tipo2.next()) {
							hay2=true;
							tipofinal=3;
						}
						if(!hay2) {
							ps=Conexion.conexion.prepareStatement("SELECT * FROM moto where electrico_vehiculos_matricula=?");
							ps.setString(1, matricula);
							tipo2=ps.executeQuery();
							tipofinal=4;
						}
					}
				}
				
			}
			switch(tipofinal) {
			case -1:
				break;
			case 1:				
				vehiculo=new Coche_combustion(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
						resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
						oficina,tipo.getDouble("consumo"),tipo.getInt("potencia"),tipo.getString("emisiones"),
						tipo2.getInt("plazas"),tipo2.getString("tipo"));
				break;
			case 2:
				vehiculo=new Furgoneta(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
						resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
						oficina,tipo.getDouble("consumo"),tipo.getInt("potencia"),tipo.getString("emisiones"),
						tipo2.getDouble("carga"),tipo2.getString("carnet"));
				break;
			case 3:
				vehiculo= new Coche_electrico(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
						resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
						oficina,tipo.getDouble("autonomia"),tipo.getInt("recarga"),
						tipo2.getInt("plazas"),tipo2.getString("tipo"));
				break;
			case 4:
				vehiculo= new Motos(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
						resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
						oficina,tipo.getDouble("autonomia"),tipo.getInt("recarga"),
						tipo2.getInt("cilindrada"),tipo2.getString("licencia"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vehiculo;
	}

}
