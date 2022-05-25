package accesoBD;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.*;
import exceptions.Ape1_no_valido;
import exceptions.Ape2_no_valido;
import exceptions.Carnet_no_valido;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Dni_no_valido;
import exceptions.Localidad_no_valida;
import exceptions.Longitud_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;
import exceptions.Tarjeta_no_valida;
import gui.VentanaAlquileres;

public class AlquilerBD {

	public static Alquiler listaAlquiler(int codigo) throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido, Carnet_no_valido, Tarjeta_no_valida, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		ResultSet resultadoSql=null;
		Alquiler alquiler=null;
		Categoria categoria=null;
		Oficina oficina=null;
		try {
			PreparedStatement ps=Conexion.conexion.prepareStatement("select * from alquiler where codigo=?");
			ps.setInt(1, codigo);
			resultadoSql=ps.executeQuery();
			while (resultadoSql.next()) {
				//si entra en el bucle, quiere decir que si existe el empleado
				categoria=(VehiculoBD.listaVehiculo(resultadoSql.getString("vehiculos_matricula"))).getCategoria();
				oficina=(VehiculoBD.listaVehiculo(resultadoSql.getString("vehiculos_matricula"))).getUbicacion();
				alquiler=new Alquiler(codigo,VehiculoBD.listaVehiculo(resultadoSql.getString("vehiculos_matricula")),EmpleadoBD.listaEmpleado(resultadoSql.getString("empleado_persona_dni")),ClienteBD.listaCliente(resultadoSql.getString("cliente_persona_dni")),resultadoSql.getDate("fecha_inicio"),resultadoSql.getDate("fecha_dev"),OficinaBD.listaOficina(resultadoSql.getString("empleado_oficina_codigo")),resultadoSql.getDouble("precio"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alquiler;
	}
	
	public static ArrayList<Alquiler> listaAlquileres() throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido, Carnet_no_valido, Tarjeta_no_valida, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		ResultSet resultadoSql=null;
		Alquiler alquiler=null;
		Categoria categoria=null;
		Oficina oficina=null;
		ArrayList<Alquiler> lista=new ArrayList<Alquiler>();
		try {
			PreparedStatement ps=Conexion.conexion.prepareStatement("select * from alquiler");
			resultadoSql=ps.executeQuery();
			while (resultadoSql.next()) {
				//si entra en el bucle, quiere decir que si existe el empleado
				categoria=(VehiculoBD.listaVehiculo(resultadoSql.getString("vehiculos_matricula"))).getCategoria();
				oficina=(VehiculoBD.listaVehiculo(resultadoSql.getString("vehiculos_matricula"))).getUbicacion();
				alquiler=new Alquiler(resultadoSql.getInt("codigo"),VehiculoBD.listaVehiculo(resultadoSql.getString("vehiculos_matricula")),EmpleadoBD.listaEmpleado(resultadoSql.getString("empleado_persona_dni")),ClienteBD.listaCliente(resultadoSql.getString("cliente_persona_dni")),resultadoSql.getDate("fecha_inicio"),resultadoSql.getDate("fecha_dev"),OficinaBD.listaOficina(resultadoSql.getString("empleado_oficina_codigo")),resultadoSql.getDouble("precio"));
				lista.add(alquiler);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public static double calculaPrecio(Alquiler alquiler) {
		Vehiculo vehiculo=alquiler.getVehiculo();
		Categoria categoria=vehiculo.getCategoria();
		Oficina alquilado=alquiler.getEmpleado().getOficina_trab();
		double cargoCategoria=0;
		double precio=0;
		long dias=calculaDias(alquiler.getFecha_aql(),alquiler.getFecha_dev());
		
		
		if(vehiculo.getClass()==Coche_electrico.class) {
			
			precio=(50*dias);
			precio=precio+(precio*0.15);
			cargoCategoria=(precio*(categoria.getRecargo()/100));
			precio=precio+cargoCategoria;
			
			
		     if(alquilado.getAeropuerto()||alquiler.getOficina_dev().getAeropuerto()) {
		    	 precio=precio+(precio*0.1);
		     }
		     
		}else if(vehiculo.getClass()==Coche_combustion.class) {
			precio=(50*dias);
			cargoCategoria=(precio*(categoria.getRecargo()/100));
			precio=precio+cargoCategoria;
			
			
		     if(alquilado.getAeropuerto()||alquiler.getOficina_dev().getAeropuerto()) {
		    	 precio=precio+(precio*0.1);
		     }
		}else if(vehiculo.getClass()==Motos.class) {
			precio=(10*dias);
			precio=precio+(precio*0.15);
			cargoCategoria=(precio*(categoria.getRecargo()/100));
			precio=precio+cargoCategoria;
			
			
		     if(alquilado.getAeropuerto()||alquiler.getOficina_dev().getAeropuerto()) {
		    	 precio=precio+(precio*0.1);
		     }
		}else if(vehiculo.getClass()==Furgoneta.class){
			precio=(70*dias);
			cargoCategoria=(precio*(categoria.getRecargo()/100));
			precio=precio+cargoCategoria;
			
			
		     if(alquilado.getAeropuerto()||alquiler.getOficina_dev().getAeropuerto()) {
		    	 precio=precio+(precio*0.1);
		     }
		}
		
		return precio;
	}
	
	public static double calculaPrecio(Vehiculo vehiculo,Date fechaAlq,Date fechaDev,Categoria categoria,Oficina ofAlq,Oficina ofDev) {
		double cargoCategoria=0;
		double precio=0;
		long dias=calculaDias(fechaAlq,fechaDev);
		
		
		if(vehiculo.getClass()==Coche_electrico.class) {
			
			precio=(50*dias);
			precio=precio+(precio*0.15);
			cargoCategoria=(precio*(categoria.getRecargo()/100));
			precio=precio+cargoCategoria;
			
			
		     if(ofAlq.getAeropuerto()||ofDev.getAeropuerto()) {
		    	 precio=precio+(precio*0.1);
		     }
		     
		}else if(vehiculo.getClass()==Coche_combustion.class) {
			precio=(50*dias);
			cargoCategoria=(precio*(categoria.getRecargo()/100));
			precio=precio+cargoCategoria;
			
			
		     if(ofAlq.getAeropuerto()||ofDev.getAeropuerto()) {
		    	 precio=precio+(precio*0.1);
		     }
		}else if(vehiculo.getClass()==Motos.class) {
			precio=(10*dias);
			precio=precio+(precio*0.15);
			cargoCategoria=(precio*(categoria.getRecargo()/100));
			precio=precio+cargoCategoria;
			
			
		     if(ofAlq.getAeropuerto()||ofDev.getAeropuerto()) {
		    	 precio=precio+(precio*0.1);
		     }
		}else if(vehiculo.getClass()==Furgoneta.class){
			precio=(70*dias);
			cargoCategoria=(precio*(categoria.getRecargo()/100));
			precio=precio+cargoCategoria;
			
			
		     if(ofAlq.getAeropuerto()||ofDev.getAeropuerto()) {
		    	 precio=precio+(precio*0.1);
		     }
		}
		
		return precio;
	}
	
	public static long calculaDias(Date inicio,Date fin) {
		long empiezaTime = inicio.getTime() ;
	     long terminaTime = fin.getTime();
	     long diasDesde = (long) Math.floor(empiezaTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora 
	     long diasHasta = (long) Math.floor(terminaTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
	     long dias = diasHasta - diasDesde;
	     
	     return dias;
	}
	
	public static void creaAlquiler(Alquiler alquiler) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO alquiler VALUES(?,?,?,?,?,?,?,?)");
		ps.setInt(1, alquiler.getCodigo());
		ps.setDate(2, alquiler.getFecha_aql());
		ps.setDate(3, alquiler.getFecha_dev());
		ps.setDouble(4, alquiler.getPrecio());
		ps.setString(5, alquiler.getVehiculo().getMatricula());
		ps.setString(6, alquiler.getEmpleado().getDni());
		ps.setString(7, alquiler.getOficina_dev().getCodigo());
		ps.setString(8, alquiler.getCliente().getDni());
		ps.executeUpdate();
	}
	
	public static void modificaAlquiler(Alquiler alquiler) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("UPDATE alquiler SET fecha_inicio=?,fecha_dev=?,precio=?,vehiculos_matricula=?,empleado_persona_dni=?,empleado_oficina_codigo=?,cliente_persona_dni=? where codigo=?");
		ps.setDate(1, alquiler.getFecha_aql());
		ps.setDate(2, alquiler.getFecha_dev());
		ps.setDouble(3, alquiler.getPrecio());
		ps.setString(4, alquiler.getVehiculo().getMatricula());
		ps.setString(5, alquiler.getEmpleado().getDni());
		ps.setString(6, alquiler.getOficina_dev().getCodigo());
		ps.setString(7, alquiler.getCliente().getDni());
		ps.setInt(8, alquiler.getCodigo());
		ps.executeUpdate();
	}
	
	public static void eliminarAlquiler(int codigo) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("delete from alquiler where codigo=?");
		ps.setInt(1, codigo);
		ps.executeUpdate();
	}
	
}
