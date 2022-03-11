package clases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Empresa{
	
	//propiedades
	
	private TreeMap<Integer,Vehiculo> vehiculo;
	private TreeMap<Integer,Cliente> cliente;
	private TreeMap<Integer,Empleado> empleado;
	private FileOutputStream file;
	private ObjectOutputStream output;
	

	
	//Getters and setters
	
	public TreeMap<Integer, Vehiculo> getVehiculo() {
		return vehiculo;
	}
	private void setVehiculo(TreeMap<Integer, Vehiculo> vehiculo) {
		this.vehiculo = vehiculo;
	}
	public TreeMap<Integer, Cliente> getCliente() {
		return cliente;
	}
	private void setCliente(TreeMap<Integer, Cliente> cliente) {
		this.cliente = cliente;
	}
	public TreeMap<Integer, Empleado> getEmpleado() {
		return empleado;
	}
	private void setEmpleado(TreeMap<Integer, Empleado> empleado) {
		this.empleado = empleado;
	}

	
	public static Empresa empresa;
	//Constructores
	public Empresa(TreeMap<Integer, Vehiculo> vehiculo, TreeMap<Integer, Cliente> cliente,
			TreeMap<Integer, Empleado> empleado) {
		super();
		this.vehiculo = vehiculo;
		this.cliente = cliente;
		this.empleado = empleado;
	}
	
	//Metodos
	
	
	
	public boolean compruebaCategoria (String codigo) {
		
		for(Entry<Integer, Vehiculo> vehiculo : vehiculo.entrySet()) {
			
			int key =vehiculo.getKey();
			Vehiculo value= vehiculo.getValue(); 
					
			if(value.equals(codigo)) {
				return true;
					
				}
		}
		return false;
	}
	
		
	
}
