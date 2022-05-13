package clases;

import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;

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
	protected GregorianCalendar fecha_nac;
	protected Date fecha_nac2;
	
	
	//Getters and Setters
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws Longitud_no_valida {
		validaNombre(nombre);
		this.nombre=nombre;
	}
	public String getApe1() {
		return ape1;
	}
	public void setApe1(String ape1) throws Ape1_no_valido {
		validaApe1(ape1);
		this.ape1=ape1;
	}
	public String getApe2() {
		return ape2;
	}
	public void setApe2(String ape2) throws Ape2_no_valido {
		validaApe2(ape2);
		this.ape2=ape2;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) throws Dni_no_valido {
		this.dni = dni;
	}
	public GregorianCalendar getFecha_nac() {
		return fecha_nac;
	}
	public Date getFecha_nac2() {
		return fecha_nac2;
	}
	public void setFecha_nac(GregorianCalendar fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public void setFecha_nac(Date fecha_nac2) {
		this.fecha_nac2 = fecha_nac2;
	}
	//Constructores
	public Persona(String nombre, String ape1, String ape2, String dni,GregorianCalendar fecha_nac) throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido {
		setNombre(nombre);
		setApe1(ape1);
		setApe2(ape2);
		setDni(dni);
		setFecha_nac(fecha_nac);
	}
	public Persona(String nombre, String ape1, String dni,GregorianCalendar fecha_nac) throws Dni_no_valido, Longitud_no_valida, Ape1_no_valido, Ape2_no_valido {
		setNombre(nombre);
		setApe1(ape1);
		setApe2(null);
		setDni(dni);
		setFecha_nac(fecha_nac);
	}
	public Persona(String nombre, String ape1, String ape2, String dni,Date fecha_nac) throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido {
		setNombre(nombre);
		setApe1(ape1);
		setApe2(ape2);
		setDni(dni);
		setFecha_nac(fecha_nac);
	}
	
	
	public static void validaDNI(String eldni) throws Dni_no_valido
	{
		boolean valido=false;
		
		
		if(eldni.length()==9)
		{
			String letra;
			String numerostring=eldni.substring(0, 8);
			int numero=Integer.parseInt(numerostring);
			String letra_correcta=null;
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
		if(!valido) {
			throw new Dni_no_valido();
		}
	}
	
	public static void validaNombre(String nombre) throws Longitud_no_valida {
		if (!((nombre.length()>=2) && nombre.length()<=25)){
			throw new Longitud_no_valida();
		}
	}
	
	public static void validaApe1 (String ape) throws Ape1_no_valido {
		if (!(ape.length()>=3 && ape.length()<=25)) {
			throw new Ape1_no_valido();
		}
	}
	
	public static void validaApe2 (String ape) throws Ape2_no_valido {
		if (!(ape.length()>=3 && ape.length()<=25)||(ape=="")||(ape==null)) {
			throw new Ape2_no_valido();
		}
	}
	
}
