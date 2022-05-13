package clases;

import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;

import exceptions.*;


abstract public class Combustion extends Vehiculo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//propiedades
	private double consumo;
	private int potencia;
	private String emisiones;
	
	//Getters and Setters
	public double getConsumo() {
		return consumo;
	}
	private void setConsumo(double consumo) throws Consumo_no_valido {
		if (consumo>=0) {
			this.consumo = consumo;
		}else {
			throw new Consumo_no_valido();
		}
		
	}
	public int getPotencia() {
		return potencia;
	}
	private void setPotencia(int potencia) throws Potencia_no_valida {
		if(potencia>=0) {
			this.potencia = potencia;
		}else {
			throw new Potencia_no_valida();
		}
		
	}
	public String getEmisiones() {
		return emisiones;
	}
	private void setEmisiones(String emisiones) throws Emision_no_valida {
		if(validaEmision(emisiones)) {
			this.emisiones = emisiones;
		}else {
			throw new Emision_no_valida("Introduzca una de las siguientes emisiones: A/B/C");
		}
		
	}
	
	
	//Constructor
	
	public Combustion(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq,
			double kms, Categoria categoria, Oficina ubicacion, double consumo, int potencia, String emisiones)
			throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Emision_no_valida, Potencia_no_valida, Consumo_no_valido {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion);
		setConsumo(consumo);
		setPotencia(potencia);
		setEmisiones(emisiones);
	}
	
	public Combustion(String matricula, String marca, String modelo, String color, Date fecha_adq2,
			double kms, Categoria categoria, Oficina ubicacion, double consumo, int potencia, String emisiones)
			throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Emision_no_valida, Potencia_no_valida, Consumo_no_valido {
		super(matricula, marca, modelo, color, fecha_adq2, kms, categoria, ubicacion);
		setConsumo(consumo);
		setPotencia(potencia);
		setEmisiones(emisiones);
	}
	
	//Métodos
	
	/**
	 * Metodo que comprueba si la emision del coche introducida es válida según las opciones existentes.
	 * @param emision String de 1 caracter
	 * @return Dato de tipo booleano
	 */
	public boolean validaEmision(String emision) {
		boolean fin=true;
		
		if(emision.toUpperCase().equals("A") || emision.toUpperCase().equals("B") || emision.toUpperCase().equals("C")) {
			fin=true;
		}else {
			fin=false;
		}
		return fin;
		
		
	}
	


}
