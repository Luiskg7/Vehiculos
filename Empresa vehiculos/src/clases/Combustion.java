package clases;

import java.util.GregorianCalendar;

import exceptions.*;


abstract public class Combustion extends Vehiculo{
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
			double kms, Categoria categoria, Oficina ubicacion) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Potencia_no_valida, Emision_no_valida, Consumo_no_valido {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion);
		// TODO Auto-generated constructor stub
		setConsumo(consumo);
		setPotencia(potencia);
		setEmisiones(emisiones);
	}
	
	//Métodos
	
	public boolean validaEmision(String emision) {
		boolean fin=true;
		
		if(emision.toUpperCase()=="A" || emision.toUpperCase()=="B" || emision.toUpperCase()=="C") {
			fin=true;
		}else {
			fin=false;
		}
		return fin;
		
		
	}

}
