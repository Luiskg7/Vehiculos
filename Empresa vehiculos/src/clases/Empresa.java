package clases;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.TreeMap;

import exceptions.*;

public class Empresa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//propiedades
	
	private TreeMap<Integer,Vehiculo> vehiculo;
	private TreeMap<Integer,Cliente> cliente;
	private TreeMap<Integer,Empleado> empleado;
	private TreeMap<Integer,Categoria> categoria;
	private TreeMap<Integer,Oficina> oficina;
	
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
	public TreeMap<Integer, Categoria> getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer key,Categoria categoria) {
		this.categoria.put(key, categoria);
		
	}
	public TreeMap<Integer, Oficina> getOficina() {
		return oficina;
	}
	public void setOficina(TreeMap<Integer, Oficina> oficina) {
		this.oficina = oficina;
	}
	//Constructores
	public Empresa(TreeMap<Integer, Vehiculo> vehiculo, TreeMap<Integer, Cliente> cliente,
			TreeMap<Integer, Empleado> empleado, TreeMap<Integer, Categoria> categoria,
			TreeMap<Integer, Oficina> oficina) {
		super();
		new Empresa();
		this.vehiculo = vehiculo;
		this.cliente = cliente;
		this.empleado = empleado;
		this.categoria = categoria;
		this.oficina = oficina;
	}
	
	
	public Empresa() {
		this.vehiculo= new TreeMap<>();
		this.empleado= new TreeMap<>();
		this.cliente= new TreeMap<>();
		this.categoria= new TreeMap<>();
		this.oficina= new TreeMap<>();
	}
	//Metodos
	
	
	
	
	public static Categoria compruebaCategoria (String codigo,Empresa empresa) throws Categoria_no_existe {
		
		for(Entry<Integer, Categoria> categoria : empresa.categoria.entrySet()) {
			
			int key =categoria.getKey();
			Categoria value= categoria.getValue(); 
					
			if(value.getCodigo().equals(codigo)) {
				return value;
				}
		}
		throw new Categoria_no_existe();
	}
	
public static Oficina compruebaOficina (String codigo,Empresa empresa) throws Oficina_no_existe {
		
		for(Entry<Integer, Oficina> oficina : empresa.oficina.entrySet()) {
			
			int key =oficina.getKey();
			Oficina value= oficina.getValue(); 
					
			if(value.getCodigo().equals(codigo)) {
				return value;
				}
		}
		throw new Oficina_no_existe();
	}
	
	public void añadeCategoria(Integer key,Categoria categoria) {
		this.categoria.put(key,categoria);
	}
	
	public void añadeOficina(Integer key,Oficina oficina) {
		this.oficina.put(key,oficina);
	}
	
	public void añadeVehiculo(Integer key,Vehiculo oficina) {
		this.vehiculo.put(key,oficina);
	}
	
}
