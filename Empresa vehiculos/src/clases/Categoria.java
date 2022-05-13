package clases;

import exceptions.*;

import java.io.Serializable;

import clases.*;

public class Categoria implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//propiedades
	private String codigo;
	private String descripcion;
	private int recargo;
	
	//Getters and Setters
	public String getCodigo() {
		return codigo;
	}
	private void setCodigo(String codigo) throws Codigo_no_valido {
		validaCodigo(codigo);
		this.codigo=codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	private void setDescripcion(String descripcion) throws Descripcion_no_valida {
		validaDescripcion(descripcion);
		this.descripcion=descripcion;
	}
	public int getRecargo() {
		return recargo;
	}
	private void setRecargo(int recargo) throws Recargo_no_valido {
		validaRecargo(recargo);
		this.recargo=recargo;
		
	}
	
	
	//Constructores
	/**
	 * 
	 * @param codigo String de tamaño de 1 caracter que contiene una letra
	 * @param descripcion String de tamaño maximo de 25 caracteres 
	 * @param recargo Int de valor entre 0 y 100 que supone el porcentaje de recargo en los alquileres
	 * @throws Codigo_no_valido
	 * @throws Descripcion_no_valida
	 * @throws Recargo_no_valido
	 */
	public Categoria(String codigo, String descripcion, int recargo) throws Codigo_no_valido, Descripcion_no_valida, Recargo_no_valido {
		super();
		setCodigo(codigo);
		setDescripcion(descripcion);
		setRecargo(recargo);
	}
	
	//metodos

	/**
	 * Comprueba que el código no tenga más de un caracter
	 * @param codigo
	 * @throws Codigo_no_valido
	 */
	public static void validaCodigo(String codigo) throws Codigo_no_valido {
		if (codigo.length()==1) {
			
		}else {
			throw new Codigo_no_valido();
		}
	}
	public static void validaDescripcion(String descripcion) throws Descripcion_no_valida {
		if (!(descripcion.length()>2 && descripcion.length()<=25)) {
			throw new Descripcion_no_valida("Número de caracteres introducido no válido");
		}
	}
	public static void validaRecargo (int recargo) throws Recargo_no_valido {
		if (!(recargo>0 && recargo<=100)) {
			throw new Recargo_no_valido();
		}
	}
	
	public boolean equals(Object a) {
		boolean igual=false;
		if (a!=null) {
			Categoria cat=(Categoria)a;
			if (cat.codigo.equals(this.codigo)) {
				igual=true;
			}
		}
		return igual;
	}
	
	@Override
	public String toString() {
		return descripcion;
	}

}
