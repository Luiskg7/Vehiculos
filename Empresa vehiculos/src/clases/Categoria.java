package clases;

import exceptions.*;

public class Categoria {
	
	//propiedades
	private String codigo;
	private String descripcion;
	private int recargo;
	
	//Getters and Setters
	public String getCodigo() {
		return codigo;
	}
	private void setCodigo(String codigo) throws Codigo_no_valido {
		if (codigo.length()>0 && codigo.length()<=1) {
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
			throw new Descripcion_no_valida("Número de caracteres introducido no válido");
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
	
	public Categoria(String codigo, String descripcion, int recargo) throws Codigo_no_valido, Descripcion_no_valida, Recargo_no_valido {
		super();
		setCodigo(codigo);
		setDescripcion(descripcion);
		setRecargo(recargo);
	}
	
	

}
