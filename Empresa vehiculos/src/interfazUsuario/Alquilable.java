package interfazUsuario;

import java.io.Serializable;
import java.util.GregorianCalendar;

import clases.*;

public class Alquilable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//propiedades
	private Vehiculo vehiculo;
	private Empleado empleado;
	private Cliente cliente;
	private GregorianCalendar fecha_alq;
	private Oficina oficina;
	private GregorianCalendar fecha_dev;
	
	
	//Getters and setters
	
	private Vehiculo getVehiculo() {
		return vehiculo;
	}
	private void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	private Empleado getEmpleado() {
		return empleado;
	}
	private void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	private Cliente getCliente() {
		return cliente;
	}
	private void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	private GregorianCalendar getFecha_alq() {
		return fecha_alq;
	}
	private void setFecha_alq(GregorianCalendar fecha_alq) {
		this.fecha_alq = fecha_alq;
	}
	private Oficina getOficina() {
		return oficina;
	}
	private void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}
	private GregorianCalendar getFecha_dev() {
		return fecha_dev;
	}
	private void setFecha_dev(GregorianCalendar fecha_dev) {
		this.fecha_dev = fecha_dev;
	}
	
	
	//Constructores 
	
	public Alquilable(Vehiculo vehiculo, Empleado empleado, Cliente cliente, GregorianCalendar fecha_alq,
			Oficina oficina, GregorianCalendar fecha_dev) {
		super();
		this.vehiculo = vehiculo;
		this.empleado = empleado;
		this.cliente = cliente;
		this.fecha_alq = fecha_alq;
		this.oficina = oficina;
		this.fecha_dev = fecha_dev;
	}
	

}
