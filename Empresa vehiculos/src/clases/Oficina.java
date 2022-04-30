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
	private boolean aeropuerto;
	
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
		try{
			validaDescripcion(descripcion);
			this.descripcion=descripcion;
		}catch(Descripcion_no_valida e) {
			e.printStackTrace();
		}
		
	}
	public String getLocalidad() {
		return localidad;
	}
	private void setLocalidad(String localidad) throws Localidad_no_valida {
		try {
			validaLocalidad(localidad);
			this.localidad = localidad;
		}catch(Localidad_no_valida e) {
			e.printStackTrace();
		}
		
	}
	public String getProvincia() {
		return provincia;
	}
	private void setProvincia(String provincia) throws Provincia_no_valida {
		try {
			validaProvincia(provincia);
			this.provincia=provincia;
		}catch(Provincia_no_valida e) {
			e.printStackTrace();
		}
		
	}
	public boolean getAeropuerto() {
		return aeropuerto;
	}
	private void setAeropuerto(boolean aeropuerto) throws Opcion_no_valida {
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
	public Oficina(String codigo, String descripcion, String localidad, String provincia, boolean aeropuerto) throws Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, Codigo_no_valido {
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
		if (codigo.length()==4) {
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
		}else {
			throw new Codigo_no_valido();
		}
	    return fin;
	}
	
	public static void validaDescripcion(String descripcion) throws Descripcion_no_valida {
		if(!(descripcion.length()>5 && descripcion.length()<=40)) {
			throw new Descripcion_no_valida("Longitud de la descripción no válida");
		}
	}
	
	public static void validaLocalidad(String localidad) throws Localidad_no_valida {
		if(!(localidad.length()>2 && localidad.length()<=30)) {
			throw new Localidad_no_valida();
		}
	}
	
	public static void validaProvincia (String provincia) throws Provincia_no_valida {
		boolean valido=false;
		switch(provincia) {
			case "Alava":
				valido=true;
				break;
			case "Albacete":
				valido=true;
				break;
			case "Alicante":
				valido=true;
				break;
			case "Almería":
				valido=true;
				break;
			case "Asturias":
				valido=true;
				break;
			case "Avila":
				valido=true;
				break;
			case "Badajoz":
				valido=true;
				break;
			case "Barcelona":
				valido=true;
				break;
			case "Burgos":
				valido=true;
				break;
			case "Caceres":
				valido=true;
				break;
			case "Cadiz":
				valido=true;
				break;
			case "Cantabria":
				valido=true;
				break;
			case "Castellon":
				valido=true;
				break;
			case "Ciudad Real":
				valido=true;
				break;
			case "Cordoba":
				valido=true;
				break;
			case "Cuenca":
				valido=true;
				break;
			case "Gerona":
				valido=true;
				break;
			case "Granada":
				valido=true;
				break;
			case "Guadalajara":
				valido=true;
				break;
			case "Guipuzcoa":
				valido=true;
				break;
			case "Huelva":
				valido=true;
				break;
			case "Huesca":
				valido=true;
				break;
			case "Islas Baleares":
				valido=true;
				break;
			case "Jaen":
				valido=true;
				break;
			case "La Coruña":
				valido=true;
				break;
			case "La Rioja":
				valido=true;
				break;
			case "Las Palmas":
				valido=true;
				break;
			case "Leon":
				valido=true;
				break;
			case "Lerida":
				valido=true;
				break;
			case "Lugo":
				valido=true;
				break;
			case "Madrid":
				valido=true;
				break;
			case "Malaga":
				valido=true;
				break;
			case "Murcia":
				valido=true;
				break;
			case "Navarra":
				valido=true;
				break;
			case "Orense":
				valido=true;
				break;
			case "Palencia":
				valido=true;
				break;
			case "Pontevedra":
				valido=true;
				break;
			case "Salamanca":
				valido=true;
				break;
			case "Santa Cruz de Tenerife":
				valido=true;
				break;
			case "Segovia":
				valido=true;
				break;
			case "Sevilla":
				valido=true;
				break;
			case "Soria":
				valido=true;
				break;
			case "Tarragona":
				valido=true;
				break;
			case "Teruel":
				valido=true;
				break;
			case "Toledo":
				valido=true;
				break;
			case "Valencia":
				valido=true;
				break;
			case "Valladolid":
				valido=true;
				break;
			case "Vizcaya":
				valido=true;
				break;
			case "Zamora":
				valido=true;
				break;
			case "Zaragoza":
				valido=true;
				break;
		}
		if (!valido) {
			throw new Provincia_no_valida();
		}
		
	}
	
	@Override
	public String toString() {
		return descripcion+" - "+codigo;
	}
	

}
