package accesoBD;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.*;
import exceptions.Autonomia_no_valida;
import exceptions.Codigo_no_valido;
import exceptions.Color_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Km_no_valido;
import exceptions.Localidad_no_valida;
import exceptions.Marca_no_valida;
import exceptions.Matricula_no_valida;
import exceptions.Modelo_no_valido;
import exceptions.Opcion_no_valida;
import exceptions.Plazas_no_validas;
import exceptions.Provincia_no_valida;
import exceptions.Recarga_no_valida;
import exceptions.Recargo_no_valido;
import exceptions.Tipo_no_valido;

public class VehiculoBD {
	
	/**
	 * Devuelve un ArrayList con todas las matriculas que hay en la base de datos
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> listaMatriculas() throws SQLException{
		ArrayList<String> listaMatricula=new ArrayList<String>();
		ResultSet resultadoSql=null;
		String matricula=null;
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("SELECT matricula FROM vehiculos");
		resultadoSql=ps.executeQuery();
		
		while(resultadoSql.next()) {
			matricula=resultadoSql.getString("matricula");
			listaMatricula.add(matricula);
		}
		return listaMatricula;
	}

	
	/**
	 * Devuelve un vehiculo de la base de datos a traves de su matricula
	 * @param matricula
	 * @return
	 */
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
						tipo2.getInt("plazas"),
						tipo2.getString("tipo"));
				break;
			case 2:
				if(tipo2.next()) {
					vehiculo=new Furgoneta(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
							resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
							oficina,tipo.getDouble("consumo"),tipo.getInt("potencia"),tipo.getString("emisiones"),
							tipo2.getDouble("carga"),
							tipo2.getString("carnet"));
				}
				
				break;
			case 3:
				vehiculo= new Coche_electrico(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
						resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
						oficina,tipo.getDouble("autonomia"),tipo.getInt("recarga"),
						tipo2.getInt("plazas"),
						tipo2.getString("tipo"));
				break;
			case 4:
				if (tipo2.next()) {
					vehiculo= new Motos(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
							resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
							oficina,tipo.getDouble("autonomia"),tipo.getInt("recarga"),
							tipo2.getInt("cilindrada"),
							tipo2.getString("licencia"));
				}
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			vehiculo=null;
		}
		return vehiculo;
	}
	
	/**
	 * Lista todos los vehiculos de la base de datos en un ArrayList
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Vehiculo> listaVehiculos() throws SQLException {
		Vehiculo vehiculo=null;
		ArrayList<Vehiculo> listaVehiculos=new ArrayList<Vehiculo>();
		Categoria categoria=null;
		Oficina oficina=null;
		ResultSet resultadoSql=null;
		ResultSet tipo=null;
		ResultSet tipo2=null;
		boolean hay=false;
		boolean hay2=false;
		int tipofinal=-1;
		int contador=-1;
		ArrayList<String> lista=listaMatriculas();
		
		try {
			PreparedStatement ps=Conexion.conexion.prepareStatement("SELECT * FROM vehiculos");
			resultadoSql=ps.executeQuery();

			while(resultadoSql.next()) {
				contador=contador+1;
				
				ps=Conexion.conexion.prepareStatement("SELECT * FROM combustion WHERE vehiculos_matricula=?");
				ps.setString(1, lista.get(contador));
				tipo=ps.executeQuery();
				categoria=CategoriaBD.listaCategoria(resultadoSql.getString("categoria_codigo"));
				oficina=OficinaBD.listaOficina(resultadoSql.getString("oficina_codigo"));
		
				if(tipo.next()) {
					hay=true;
					ps=Conexion.conexion.prepareStatement("SELECT * FROM coche_combustion where combustion_vehiculos_matricula=?");
					ps.setString(1, lista.get(contador));
					tipo2=ps.executeQuery();
					
					if(tipo2.next()) {
						hay2=true;
						tipofinal=1;
					}
					
					if(!hay2) {
						ps=Conexion.conexion.prepareStatement("SELECT * FROM furgoneta where combustion_vehiculos_matricula=?");
						ps.setString(1, lista.get(contador));
						tipo2=ps.executeQuery();
						tipofinal=2;
					}
				}
				
				if(!hay) {
					ps=Conexion.conexion.prepareStatement("SELECT * FROM electrico where vehiculos_matricula=?");
					ps.setString(1, lista.get(contador));
					tipo=ps.executeQuery();
					
					if(tipo.next()) {
						ps=Conexion.conexion.prepareStatement("SELECT * FROM coche_electrico where electrico_vehiculos_matricula=?");
						ps.setString(1, lista.get(contador));
						tipo2=ps.executeQuery();
						
						if(tipo2.next()) {
							hay2=true;
							tipofinal=3;
						}
						if(!hay2) {
							ps=Conexion.conexion.prepareStatement("SELECT * FROM moto where electrico_vehiculos_matricula=?");
							ps.setString(1, lista.get(contador));
							tipo2=ps.executeQuery();
							tipofinal=4;
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
						tipo2.getInt("plazas"),
						tipo2.getString("tipo"));
					break;
				case 2:
					if(tipo2.next()) {
						vehiculo=new Furgoneta(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
							resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
							oficina,tipo.getDouble("consumo"),tipo.getInt("potencia"),tipo.getString("emisiones"),
							tipo2.getDouble("carga"),
							tipo2.getString("carnet"));
					}
				
					break;
				case 3:
					vehiculo= new Coche_electrico(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
						resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
						oficina,tipo.getDouble("autonomia"),tipo.getInt("recarga"),
						tipo2.getInt("plazas"),
						tipo2.getString("tipo"));
					break;
				case 4:
					if (tipo2.next()) {
						vehiculo= new Motos(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
							resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
							oficina,tipo.getDouble("autonomia"),tipo.getInt("recarga"),
							tipo2.getInt("cilindrada"),
							tipo2.getString("licencia"));
					}
				
				
				}
				listaVehiculos.add(vehiculo);
				hay=false;
				hay2=false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			vehiculo=null;
		}
	
		return listaVehiculos;
	}
	
	public static void eliminaVehiculo(String matricula) throws SQLException {
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("DELETE FROM vehiculos WHERE matricula=?");
		ps.setString(1, matricula);
		ps.executeUpdate();
		
	}
	public static void añadeCoche_electrico(Coche_electrico vehiculo) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO vehiculos VALUES(?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1,vehiculo.getMatricula());
		ps.setString(2,vehiculo.getMarca());
		ps.setString(3,vehiculo.getModelo());
		ps.setString(4,vehiculo.getColor());
		ps.setDouble(5,vehiculo.getKms());
		ps.setString(6,vehiculo.getCategoria().getDescripcion());
		ps.setString(7,vehiculo.getUbicacion().getDescripcion());
		ps.setString(8,vehiculo.getUbicacion().getCodigo());
		ps.setDate(9,vehiculo.getFecha_adq2());
		ps.setString(10,vehiculo.getCategoria().getCodigo());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO electrico VALUES(?,?,?)");
		ps.setDouble(1, vehiculo.getAutonomia());
		ps.setInt(2, vehiculo.getRecarga());
		ps.setString(3, vehiculo.getMatricula());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO coche_electrico VALUES(?,?,?)");
		ps.setString(1, String.valueOf(vehiculo.getPlazas()));
		ps.setString(2,vehiculo.getTipo());
		ps.setString(3, vehiculo.getMatricula());
		
		ps.executeUpdate();
		
		
		
	}
	
	public static void añadeMoto(Motos vehiculo) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO vehiculos VALUES(?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1,vehiculo.getMatricula());
		ps.setString(2,vehiculo.getMarca());
		ps.setString(3,vehiculo.getModelo());
		ps.setString(4,vehiculo.getColor());
		ps.setDouble(5,vehiculo.getKms());
		ps.setString(6,vehiculo.getCategoria().getDescripcion());
		ps.setString(7,vehiculo.getUbicacion().getDescripcion());
		ps.setString(8,vehiculo.getUbicacion().getCodigo());
		ps.setDate(9,vehiculo.getFecha_adq2());
		ps.setString(10,vehiculo.getCategoria().getCodigo());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO electrico VALUES(?,?,?)");
		ps.setDouble(1, vehiculo.getAutonomia());
		ps.setInt(2, vehiculo.getRecarga());
		ps.setString(3, vehiculo.getMatricula());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO moto VALUES(?,?,?)");
		ps.setInt(1, vehiculo.getCilindrada());
		ps.setString(2,vehiculo.getLicencia());
		ps.setString(3, vehiculo.getMatricula());
		
		ps.executeUpdate();
		
		
		
	}
	
	public static void añadeCoche_combustion(Coche_combustion vehiculo) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO vehiculos VALUES(?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1,vehiculo.getMatricula());
		ps.setString(2,vehiculo.getMarca());
		ps.setString(3,vehiculo.getModelo());
		ps.setString(4,vehiculo.getColor());
		ps.setDouble(5,vehiculo.getKms());
		ps.setString(6,vehiculo.getCategoria().getDescripcion());
		ps.setString(7,vehiculo.getUbicacion().getDescripcion());
		ps.setString(8,vehiculo.getUbicacion().getCodigo());
		ps.setDate(9,vehiculo.getFecha_adq2());
		ps.setString(10,vehiculo.getCategoria().getCodigo());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO combustion VALUES(?,?,?,?)");
		ps.setDouble(1, vehiculo.getConsumo());
		ps.setInt(2, vehiculo.getPotencia());
		ps.setString(3, vehiculo.getEmisiones());
		ps.setString(4, vehiculo.getMatricula());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO coche_combustion VALUES(?,?,?)");
		ps.setInt(1, vehiculo.getPlazas());
		ps.setString(2,vehiculo.getTipo());
		ps.setString(3, vehiculo.getMatricula());
		
		ps.executeUpdate();
		
	}
	
	public static void añadeFurgoneta(Furgoneta vehiculo) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO vehiculos VALUES(?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1,vehiculo.getMatricula());
		ps.setString(2,vehiculo.getMarca());
		ps.setString(3,vehiculo.getModelo());
		ps.setString(4,vehiculo.getColor());
		ps.setDouble(5,vehiculo.getKms());
		ps.setString(6,vehiculo.getCategoria().getDescripcion());
		ps.setString(7,vehiculo.getUbicacion().getDescripcion());
		ps.setString(8,vehiculo.getUbicacion().getCodigo());
		ps.setDate(9,vehiculo.getFecha_adq2());
		ps.setString(10,vehiculo.getCategoria().getCodigo());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO combustion VALUES(?,?,?,?)");
		ps.setDouble(1, vehiculo.getConsumo());
		ps.setInt(2, vehiculo.getPotencia());
		ps.setString(3, vehiculo.getEmisiones());
		ps.setString(4, vehiculo.getMatricula());
		
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("INSERT INTO furgoneta VALUES(?,?,?)");
		ps.setDouble(1, vehiculo.getCarga());
		ps.setString(2,vehiculo.getCarnet());
		ps.setString(3, vehiculo.getMatricula());
		
		ps.executeUpdate();
		
	}
	
	public static void modificaCoche_electrico(Coche_electrico coche,Categoria categoria,Oficina ubicacion) throws SQLException {
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("UPDATE vehiculos SET  marca=?, modelo=?, color=?, kms=?, categoria=?,ubicacion=?,oficina_codigo=?,fecha_adq=?,categoria_codigo=? where matricula=?");
		ps.setString(1, coche.getMarca());
		ps.setString(2, coche.getModelo());
		ps.setString(3, coche.getColor());
		ps.setDouble(4, coche.getKms());
		ps.setString(5, categoria.getDescripcion());
		ps.setString(6, ubicacion.getDescripcion());
		ps.setString(7, ubicacion.getCodigo());
		ps.setDate(8, coche.getFecha_adq2());
		ps.setString(9, categoria.getCodigo());
		ps.setString(10, coche.getMatricula());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE electrico SET autonomia=?,recarga=? WHERE vehiculos_matricula=?");
		ps.setDouble(1, coche.getAutonomia());
		ps.setInt(2,coche.getRecarga());
		ps.setString(3, coche.getMatricula());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE coche_electrico SET plazas=?,tipo=? where electrico_vehiculos_matricula=?");
		ps.setInt(1, coche.getPlazas());
		ps.setString(2, coche.getTipo());
		ps.setString(3, coche.getMatricula());
		
		ps.executeUpdate();
	}
	
	public static void modificaMoto(Motos moto,Categoria categoria,Oficina ubicacion) throws SQLException {
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("UPDATE vehiculos SET  marca=?, modelo=?, color=?, kms=?, categoria=?,ubicacion=?,oficina_codigo=?,fecha_adq=?,categoria_codigo=? where matricula=?");
		ps.setString(1, moto.getMarca());
		ps.setString(2, moto.getModelo());
		ps.setString(3, moto.getColor());
		ps.setDouble(4, moto.getKms());
		ps.setString(5, categoria.getDescripcion());
		ps.setString(6, ubicacion.getDescripcion());
		ps.setString(7, ubicacion.getCodigo());
		ps.setDate(8, moto.getFecha_adq2());
		ps.setString(9, categoria.getCodigo());
		ps.setString(10, moto.getMatricula());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE electrico SET autonomia=?,recarga=? WHERE vehiculos_matricula=?");
		ps.setDouble(1, moto.getAutonomia());
		ps.setInt(2,moto.getRecarga());
		ps.setString(3, moto.getMatricula());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE moto SET cilindrada=?,licencia=? where electrico_vehiculos_matricula=?");
		ps.setInt(1, moto.getCilindrada());
		ps.setString(2, moto.getLicencia());
		ps.setString(3, moto.getMatricula());
		
		ps.executeUpdate();
	}
	
	public static void modificaCoche_combustion(Coche_combustion coche,Categoria categoria,Oficina ubicacion) throws SQLException {
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("UPDATE vehiculos SET  marca=?, modelo=?, color=?, kms=?, categoria=?,ubicacion=?,oficina_codigo=?,fecha_adq=?,categoria_codigo=? where matricula=?");
		ps.setString(1, coche.getMarca());
		ps.setString(2, coche.getModelo());
		ps.setString(3, coche.getColor());
		ps.setDouble(4, coche.getKms());
		ps.setString(5, categoria.getDescripcion());
		ps.setString(6, ubicacion.getDescripcion());
		ps.setString(7, ubicacion.getCodigo());
		ps.setDate(8, coche.getFecha_adq2());
		ps.setString(9, categoria.getCodigo());
		ps.setString(10, coche.getMatricula());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE combustion SET consumo=?,potencia=?,emisiones=? WHERE vehiculos_matricula=?");
		ps.setDouble(1, coche.getConsumo());
		ps.setInt(2,coche.getPotencia());
		ps.setString(3, coche.getEmisiones());
		ps.setString(4, coche.getMatricula());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE coche_combustion SET plazas=?,tipo=? where combustion_vehiculos_matricula=?");
		ps.setInt(1, coche.getPlazas());
		ps.setString(2, coche.getTipo());
		ps.setString(3, coche.getMatricula());
		
		ps.executeUpdate();
	}
	
	public static void modificaFurgoneta(Furgoneta furgoneta,Categoria categoria,Oficina ubicacion) throws SQLException {
		
		PreparedStatement ps=Conexion.conexion.prepareStatement("UPDATE vehiculos SET  marca=?, modelo=?, color=?, kms=?, categoria=?,ubicacion=?,oficina_codigo=?,fecha_adq=?,categoria_codigo=? where matricula=?");
		ps.setString(1, furgoneta.getMarca());
		ps.setString(2, furgoneta.getModelo());
		ps.setString(3, furgoneta.getColor());
		ps.setDouble(4, furgoneta.getKms());
		ps.setString(5, categoria.getDescripcion());
		ps.setString(6, ubicacion.getDescripcion());
		ps.setString(7, ubicacion.getCodigo());
		ps.setDate(8, furgoneta.getFecha_adq2());
		ps.setString(9, categoria.getCodigo());
		ps.setString(10, furgoneta.getMatricula());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE combustion SET consumo=?,potencia=?,emisiones=? WHERE vehiculos_matricula=?");
		ps.setDouble(1, furgoneta.getConsumo());
		ps.setInt(2,furgoneta.getPotencia());
		ps.setString(3, furgoneta.getEmisiones());
		ps.setString(4, furgoneta.getMatricula());
		ps.executeUpdate();
		
		ps=Conexion.conexion.prepareStatement("UPDATE furgoneta SET carga=?,carnet=? where combustion_vehiculos_matricula=?");
		ps.setDouble(1, furgoneta.getCarga());
		ps.setString(2, furgoneta.getCarnet());
		ps.setString(3, furgoneta.getMatricula());
		
		ps.executeUpdate();
	}


	/**
	 * Devuelve un vehiculo a través del codigo de la oficina a la que pertenece
	 * @param codigo
	 * @return
	 */
	public static ArrayList<Vehiculo> listaVehiculoOficina(String codigo) {
		Vehiculo vehiculo=null;
		Categoria categoria=null;
		Oficina oficina=null;
		ArrayList<Vehiculo> listaVehiculos=new ArrayList<Vehiculo>();
		ResultSet resultadoSql=null;
		ResultSet tipo=null;
		ResultSet tipo2=null;
		boolean hay=false;
		boolean hay2=false;
		int tipofinal=-1;
		
		try {
			PreparedStatement ps=Conexion.conexion.prepareStatement("SELECT * FROM vehiculos WHERE oficina_codigo=?");
			ps.setString(1, codigo);
			resultadoSql=ps.executeQuery();
			
			while(resultadoSql.next()) {
					ps=Conexion.conexion.prepareStatement("SELECT * FROM combustion,vehiculos where vehiculos_matricula=matricula");
					tipo=ps.executeQuery();
					categoria=CategoriaBD.listaCategoria(resultadoSql.getString("categoria_codigo"));
					oficina=OficinaBD.listaOficina(resultadoSql.getString("oficina_codigo"));
			
					if(tipo.next()) {
						hay=true;
						ps=Conexion.conexion.prepareStatement("SELECT * FROM coche_combustion,vehiculos where combustion_vehiculos_matricula=matricula");

						tipo2=ps.executeQuery();
						
						if(tipo2.next()) {
							hay2=true;
							tipofinal=1;
						}
						
						if(!hay2) {
							ps=Conexion.conexion.prepareStatement("SELECT * FROM vehiculos where combustion_vehiculos_matricula=matricula");
							
							tipo2=ps.executeQuery();
							tipofinal=2;
						}
					}
					
					if(!hay) {
						ps=Conexion.conexion.prepareStatement("SELECT * FROM electrico,vehiculos where vehiculos_matricula=matricula");
						
						tipo=ps.executeQuery();
						
						if(tipo.next()) {
							ps=Conexion.conexion.prepareStatement("SELECT * FROM coche_electrico,vehiculos where electrico_vehiculos_matricula=matricula");
							
							tipo2=ps.executeQuery();
							
							if(tipo2.next()) {
								hay2=true;
								tipofinal=3;
							}
							if(!hay2) {
								ps=Conexion.conexion.prepareStatement("SELECT * FROM moto,vehiculos where electrico_vehiculos_matricula=matricula");
								
								tipo2=ps.executeQuery();
								tipofinal=4;
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
							tipo2.getInt("plazas"),
							tipo2.getString("tipo"));
					break;
				case 2:
					if(tipo2.next()) {
						vehiculo=new Furgoneta(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
								resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
								oficina,tipo.getDouble("consumo"),tipo.getInt("potencia"),tipo.getString("emisiones"),
								tipo2.getDouble("carga"),
								tipo2.getString("carnet"));
					}
					
					break;
				case 3:
					vehiculo= new Coche_electrico(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
							resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
							oficina,tipo.getDouble("autonomia"),tipo.getInt("recarga"),
							tipo2.getInt("plazas"),
							tipo2.getString("tipo"));
					break;
				case 4:
					if (tipo2.next()) {
						vehiculo= new Motos(resultadoSql.getString("matricula"),resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
								resultadoSql.getString("color"),resultadoSql.getDate("fecha_adq"),resultadoSql.getDouble("kms"),categoria,
								oficina,tipo.getDouble("autonomia"),tipo.getInt("recarga"),
								tipo2.getInt("cilindrada"),
								tipo2.getString("licencia"));
					}
					
					
				}
				listaVehiculos.add(vehiculo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaVehiculos;
	}

}
