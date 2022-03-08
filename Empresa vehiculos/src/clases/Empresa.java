package clases;

import java.util.TreeMap;

public class Empresa {
	
	//propiedades
	
	private TreeMap<String,Vehiculo> vehiculo;
	private TreeMap<String,Cliente> cliente;
	private TreeMap<String,Empleado> empleado;
	
	

	
	//Getters and setters
	private TreeMap<String, Vehiculo> getVehiculo() {
		return vehiculo;
	}
	private void setVehiculo(TreeMap<String, Vehiculo> vehiculo) {
		this.vehiculo = vehiculo;
	}
	private TreeMap<String, Cliente> getCliente() {
		return cliente;
	}
	private void setCliente(TreeMap<String, Cliente> cliente) {
		this.cliente = cliente;
	}
	private TreeMap<String, Empleado> getEmpleado() {
		return empleado;
	}
	private void setEmpleado(TreeMap<String, Empleado> empleado) {
		this.empleado = empleado;
	}
	
}
