package clases;

import exceptions.*;

public class Oficina {
	
	//propiedades
	private String codigo;
	private String descripcion;
	private String localidad;
	private String provincia;
	private String aeropuerto;
	
	//Getters and Setters
	public String getCodigo() {
		return codigo;
	}
	private void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	private void setDescripcion(String descripcion) throws Descripcion_no_valida {
		if(descripcion.length()>10 && descripcion.length()<=40) {
			this.descripcion = descripcion;
		}else {
			throw new Descripcion_no_valida("Longitud de la descripción no válida");
		}
		
	}
	public String getLocalidad() {
		return localidad;
	}
	private void setLocalidad(String localidad) throws Localidad_no_valida {
		if(localidad.length()>5 && localidad.length()<=30) {
			this.localidad = localidad;
		}else {
			throw new Localidad_no_valida();
		}
		
	}
	public String getProvincia() {
		return provincia;
	}
	private void setProvincia(String provincia) throws Provincia_no_valida {
		if(provincia.length()>5 && provincia.length()<=25) {
			this.provincia = provincia;
		}else {
			throw new Provincia_no_valida();
		}
		
	}
	public String getAeropuerto() {
		return aeropuerto;
	}
	private void setAeropuerto(String aeropuerto) throws Opcion_no_valida {
		aeropuerto=esAeropuerto(aeropuerto);
		this.aeropuerto = aeropuerto;
	}
	
	
	//Constructores
	
	public Oficina(String codigo, String descripcion, String localidad, String provincia, String aeropuerto) throws Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida {
		super();
		setCodigo(codigo);
		setDescripcion(descripcion);
		setLocalidad(localidad);
		setProvincia(provincia);
		setAeropuerto(aeropuerto);
	}
	
	//Metodos
	
	//public static boolean validaCodigo(String codigo) {
	//	String letras= codigo.substring(0,1);
		//int
		
	//}
	
	public static String esAeropuerto(String opcion) throws Opcion_no_valida {
		String resultado="SI";
		if (opcion.equalsIgnoreCase("y")) {
			resultado="SI";
		}else if(opcion.equalsIgnoreCase("n")){
			resultado="NO";
		}else {
			throw new Opcion_no_valida("La opcion introducida debe ser: Y/N");
		}
		return resultado;
	}
	

}
