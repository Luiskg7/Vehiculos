package clases;

import java.io.Serializable;

import exceptions.*;

public class Oficina implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private void setCodigo(String codigo) throws Codigo_no_valido {
		if(validaCodigo(codigo)) {
			this.codigo = codigo;
		}else {
			throw new Codigo_no_valido();
		}
		
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
		if(localidad.length()>2 && localidad.length()<=30) {
			this.localidad = localidad;
		}else {
			throw new Localidad_no_valida();
		}
		
	}
	public String getProvincia() {
		return provincia;
	}
	private void setProvincia(String provincia) throws Provincia_no_valida {
		if(provincia.length()>3 && provincia.length()<=25) {
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
	
	/**
	 * 
	 * @param codigo String de longitud 4 formado por los dos letras y dos numeros
	 * @param descripcion String para introducir donde esta la oficina
	 * @param localidad String para introducir la localidad de la oficina
	 * @param provincia String para introducir la provincia de la oficina
	 * @param aeropuerto String que indica si la oficina consta de aeropuerto, la opciones a introducir son Y/N
	 * @throws Descripcion_no_valida
	 * @throws Localidad_no_valida
	 * @throws Provincia_no_valida
	 * @throws Opcion_no_valida
	 * @throws Codigo_no_valido
	 */
	public Oficina(String codigo, String descripcion, String localidad, String provincia, String aeropuerto) throws Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
		super();
		setCodigo(codigo);
		setDescripcion(descripcion);
		setLocalidad(localidad);
		setProvincia(provincia);
		setAeropuerto(aeropuerto);
	}
	
	//Metodos
	
	public static boolean validaCodigo(String codigo) throws Codigo_no_valido {
		boolean fin=true;
		
		for (int i=0; i<2; i++) {
	        char c = codigo.charAt(i);
	        // Si no está entre a y z, ni entre A y Z, ni es un espacio
	        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	            fin= true;
	        }else {
		    	throw new Codigo_no_valido();
		    }
	    }
		
		for (int i=2; i<4; i++) {
	        char c = codigo.charAt(i);
	        // Si no está entre a y z, ni entre A y Z, ni es un espacio
	        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	            fin= true;
	        }else {
	        	throw new Codigo_no_valido();
		    }
	    }
	    
	    return fin;
	}
	
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
