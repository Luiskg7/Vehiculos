package clases;

import java.sql.Date;

public class Alquiler {
	//propiedades
	
	private int codigo;
	private Vehiculo vehiculo;
	private Empleado empleado;
	private Cliente cliente;
	private Date fecha_aql;
	private Date fecha_dev;
	private Oficina oficina_dev;
	
	//Getters and Setters
	
	public int getCodigo() {
		return codigo;
	}
	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Vehiculo getMatricula() {
		return vehiculo;
	}
	private void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	private void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Cliente getCliente() {
		return cliente;
	}
	private void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFecha_aql() {
		return fecha_aql;
	}
	private void setFecha_aql(Date fecha_aql) {
		this.fecha_aql = fecha_aql;
	}
	public Date getFecha_dev() {
		return fecha_dev;
	}
	private void setFecha_dev(Date fecha_dev) {
		this.fecha_dev = fecha_dev;
	}
	public Oficina getOficina_dev() {
		return oficina_dev;
	}
	private void setOficina_dev(Oficina oficina_dev) {
		this.oficina_dev = oficina_dev;
	}
	
	//Constructor
	
	public Alquiler(int codigo, Vehiculo vehiculo, Empleado empleado, Cliente cliente, Date fecha_aql, Date fecha_dev,
			Oficina oficina_dev) {
		super();
		setCodigo(codigo);
		setVehiculo(vehiculo);
		setEmpleado(empleado);
		setCliente(cliente);
		setFecha_aql(fecha_aql);
		setFecha_dev(fecha_dev);
		setOficina_dev(oficina_dev);
	}
	
	
	

}
