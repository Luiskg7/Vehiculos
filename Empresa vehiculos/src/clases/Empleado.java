package clases;

import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;

import exceptions.Ape1_no_valido;
import exceptions.Ape2_no_valido;
import exceptions.Dni_no_valido;
import exceptions.Longitud_no_valida;

public class Empleado extends Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//propiedades
	private GregorianCalendar fecha_alta;
	private Date fecha_alta2;
	private String oficina_trab;
	
	//Getters and Setters
	public GregorianCalendar getFecha_alta() {
		return fecha_alta;
	}
	public Date getFecha_alta2() {
		return fecha_alta2;
	}
	private void setFecha_alta(GregorianCalendar fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	private void setFecha_alta(Date fecha_alta2) {
		this.fecha_alta2 = fecha_alta2;
	}
	public String getOficina_trab() {
		return oficina_trab;
	}
	private void setOficina_trab(String oficina_trab) {
		this.oficina_trab = oficina_trab;
	}
	
	
	//Constructores
	/**
	 * 
	 * @param nombre String de longitud entre 2 y 25 que determina el nombre del empleado
	 * @param ape1 String de longitud entre 2 y 25 que determina el primer apellido del empleado
	 * @param ape2 String de longitud entre 2 y 25 que determina el segundo apellido del empleado
	 * @param dni String del cliente formado por 8 numeros y una letra
	 * @param fecha_alta GregorianCalendar  para la fecha de alta del empleado
	 * @param oficina_trab Clase Oficina en la que se encuentra el empleado
	 * @throws Longitud_no_valida
	 * @throws Dni_no_valido
	 * @throws Ape2_no_valido 
	 * @throws Ape1_no_valido 
	 */
	public Empleado(String nombre, String ape1, String ape2, String dni,GregorianCalendar fecha_nac, GregorianCalendar fecha_alta,
			String oficina_trab) throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido {
		super(nombre, ape1, ape2, dni,fecha_nac);
		setFecha_alta(fecha_alta);
		setOficina_trab(oficina_trab);
	}
	
	public Empleado(String nombre, String ape1, String ape2, String dni,Date fecha_nac, Date fecha_alta,
			String oficina_trab) throws Longitud_no_valida, Dni_no_valido, Ape1_no_valido, Ape2_no_valido {
		super(nombre, ape1, ape2, dni,fecha_nac);
		setFecha_alta(fecha_alta);
		setOficina_trab(oficina_trab);
	}
	
	

}
