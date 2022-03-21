package clases;

import java.io.Serializable;

import exceptions.*;

abstract public class Persona implements Serializable{

	//Propiedades
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String ape1;
	protected String ape2;
	protected String dni;
	
	
	//Getters and Setters
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws Longitud_no_valida {
		if (nombre.length()>=2 && nombre.length()<=25){
			this.nombre = nombre;
		}else {
			throw new Longitud_no_valida();
		}
	}
	public String getApe1() {
		return ape1;
	}
	public void setApe1(String ape1) throws Longitud_no_valida {
		if (ape1.length()>=3 && ape1.length()<=25) {
			this.ape1 = ape1;
		}else {
			throw new Longitud_no_valida();
		}
	}
	public String getApe2() {
		return ape2;
	}
	public void setApe2(String ape2) throws Longitud_no_valida {
		if (ape2.length()>=3 && ape2.length()<=25) {
			this.ape2 = ape2;
		}else {
			throw new Longitud_no_valida();
		}
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) throws Dni_no_valido {
		if (validaDNI(dni)) {
		this.dni = dni;
		}else {
			throw new Dni_no_valido();
		}
	}
	
	
	//Constructores
	public Persona(String nombre, String ape1, String ape2, String dni) throws Longitud_no_valida, Dni_no_valido {
		setNombre(nombre);
		setApe1(ape1);
		setApe2(ape2);
		setDni(dni);
	}
	public Persona(String nombre, String ape1, String dni) throws Dni_no_valido, Longitud_no_valida {
		setNombre(nombre);
		setApe1(ape1);
		setApe2(null);
		setDni(dni);
	}
	
	
	
	public static boolean validaDNI(String eldni)
	{
		boolean valido=false;
		String letra;
		String numerostring=eldni.substring(0, 8);
		int numero=Integer.parseInt(numerostring);
		String letra_correcta=null;
		
		if(eldni.length()==9)
		{
			letra=eldni.substring(8);
			numero=numero%23;
			
			switch (numero)
			{
				case 0:
					letra_correcta="T";
					break;
				case 1:
					letra_correcta="R";
					break;
				case 2:
					letra_correcta="W";
					break;
				case 3:
					letra_correcta="A";
					break;
				case 4:
					letra_correcta="G";
					break;
				case 5:
					letra_correcta="M";
					break;
				case 6:
					letra_correcta="Y";
					break;
				case 7:
					letra_correcta="F";
					break;
				case 8:
					letra_correcta="P";
					break;
				case 9:
					letra_correcta="D";
					break;
				case 10:
					letra_correcta="X";
					break;
				case 11:
					letra_correcta="B";
					break;
				case 12:
					letra_correcta="N";
					break;
				case 13:
					letra_correcta="J";
					break;
				case 14:
					letra_correcta="Z";
					break;
				case 15:
					letra_correcta="S";
					break;
				case 16:
					letra_correcta="Q";
					break;
				case 17:
					letra_correcta="V";
					break;
				case 18:
					letra_correcta="H";
					break;
				case 19:
					letra_correcta="L";
					break;
				case 20:
					letra_correcta="C";
					break;
				case 21:
					letra_correcta="K";
					break;
				case 22:
					letra_correcta="E";
					break;
			}
		
			if (letra.toUpperCase().equals(letra_correcta))
			{
				valido=true;
			}else
			{
				valido=false;
			}
		}
		return valido;
	}
	
}
