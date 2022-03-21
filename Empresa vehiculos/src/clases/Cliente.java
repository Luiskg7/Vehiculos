package clases;

import java.io.Serializable;

import exceptions.*;

public class Cliente extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	/**
	 * 
	 * @param nombre String de longitud entre 2 y 25 que determina el nombre del cliente
	 * @param ape1 String de longitud entre 2 y 25 que determina el primer apellido del cliente
	 * @param ape2 String de longitud entre 2 y 25 que determina el segundo del cliente
	 * @param dni String del cliente formado por 8 numeros y una letra
	 * @param licencia String del tipo del carnet del cliente, opciones: B/C/D/AM/A1/A2
	 * @param tarjeta String  formado por 9 numeros formando la tarjeta del cliente
	 * @throws Carnet_no_valido 
	 * @throws Longitud_no_valida 
	 * @throws Tarjeta_no_valida
	 * @throws Dni_no_valido 
	 */
	//Constructores
	public Cliente(String nombre, String ape1, String ape2, String dni, String licencia, String tarjeta) throws Carnet_no_valido, Longitud_no_valida, Tarjeta_no_valida, Dni_no_valido {
		super(nombre, ape1, ape2, dni);
		setLicencia(licencia);
		setTarjeta(tarjeta);
	}
	
	//Metodos
	
	/**
	 * Verifica que la estructura de una tarjeta introducida es válida
	 * @param tarjeta String de la tarjeta a introducir (9 numeros)
	 * @return
	 */
	public static boolean validaTarjeta(String tarjeta) {
		boolean fin=true;
		for (int i=0; i<tarjeta.length(); i++) {
	        char c = tarjeta.charAt(i);
	        // Si no está entre a y z, ni entre A y Z, ni es un espacio
	        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	            fin= true;
	        }else {
		    	return false;
		    }
	    }
	    
	    return fin;
	}
	@Override
	public String toString()
	{
		return nombre+" "+ape1+" con dni: "+dni+" y con un carnet de: "+licencia;
	}
}
