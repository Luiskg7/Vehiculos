package clases;

import java.util.GregorianCalendar;

public class Empleado extends Persona{
	
	//propiedades
	private GregorianCalendar fecha_alta;
	private Oficina oficina_trab;
	
	//Getters and Setters
	public GregorianCalendar getFecha_alta() {
		return fecha_alta;
	}
	private void setFecha_alta(GregorianCalendar fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public Oficina getOficina_trab() {
		return oficina_trab;
	}
	private void setOficina_trab(Oficina oficina_trab) {
		this.oficina_trab = oficina_trab;
	}
	
	
	//Constructores
	
	public Empleado(String nombre, String ape1, String ape2, String dni, GregorianCalendar fecha_alta,
			Oficina oficina_trab) {
		super(nombre, ape1, ape2, dni);
		setFecha_alta(fecha_alta);
		setOficina_trab(oficina_trab);
	}
	
	

}
