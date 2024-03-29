package clases;

import exceptions.*;
import clases.*;

public class Categoria{
	
	//propiedades
	private String codigo;
	private String descripcion;
	private int recargo;
	
	//Getters and Setters
	public String getCodigo() {
		return codigo;
	}
	private void setCodigo(String codigo) throws Codigo_no_valido {
		if (codigo.length()==1) {
			this.codigo = codigo;
		}else {
			throw new Codigo_no_valido();
		}
	}
	public String getDescripcion() {
		return descripcion;
	}
	private void setDescripcion(String descripcion) throws Descripcion_no_valida {
		if (descripcion.length()>0 && descripcion.length()<=25) {
			this.descripcion = descripcion;
		}else {
			throw new Descripcion_no_valida("N�mero de caracteres introducido no v�lido");
		}
	}
	public int getRecargo() {
		return recargo;
	}
	private void setRecargo(int recargo) throws Recargo_no_valido {
		if (recargo>0 && recargo<=100) {
			this.recargo = recargo;
		}else {
			throw new Recargo_no_valido();
		}
		
	}
	
	
	//Constructores
	/**
	 * 
	 * @param codigo String de tama�o de 1 caracter que contiene una letra
	 * @param descripcion String de tama�o maximo de 25 caracteres 
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
	 * Comprueba que el c�digo no tenga m�s de un caracter
	 * @param codigo
	 * @throws Codigo_no_valido
	 */
	public static void validaCodigo(String codigo) throws Codigo_no_valido {
		if (codigo.length()==1) {
			
		}else {
			throw new Codigo_no_valido();
		}
	}
	
	

}
