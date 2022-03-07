package clases;

import exceptions.*;

public class Cliente extends Persona{
	//propiedades
	private String licencia;
	private String  tarjeta;
	
	//Getters and Setters 
	public String getLicencia() {
		return licencia;
	}
	private void setLicencia(String licencia) throws Carnet_no_valido {
		if (licencia.toUpperCase()=="B" ||licencia.toUpperCase()=="C" || licencia.toUpperCase()=="D" 
				||licencia.toUpperCase()=="AM" || licencia.toUpperCase()=="A1" || licencia.toUpperCase()=="A2") {
			this.licencia = licencia;
		}else {
			throw new Carnet_no_valido("El carnet debe ser de tipo B/C/D/AM/A1/A2");
		}
		
	}
	public String getTarjeta() {
		return tarjeta;
	}
	private void setTarjeta(String tarjeta) throws Longitud_no_valida, Tarjeta_no_valida {
		if (tarjeta.length()==9) {
			if(validaTarjeta(tarjeta)) {
				this.tarjeta = tarjeta;
			}else {
				throw new Tarjeta_no_valida("Formato de la tarjeta no valida,introduzca solo numeros");
			}
		}else {
			throw new Longitud_no_valida();
		}
		
	}
	
	
	//Constructores
	public Cliente(String nombre, String ape1, String ape2, String dni, String licencia, String tarjeta) throws Carnet_no_valido, Longitud_no_valida, Tarjeta_no_valida {
		super(nombre, ape1, ape2, dni);
		setLicencia(licencia);
		setTarjeta(tarjeta);
	}
	
	//Metodos
	public static boolean validaTarjeta(String tarjeta) {
		for (int i=0; i<tarjeta.length(); i++) {
	        char c = tarjeta.charAt(i);
	        // Si no está entre a y z, ni entre A y Z, ni es un espacio
	        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	            return false;
	        }
	    }
	    return true;
	}
	
	

}
