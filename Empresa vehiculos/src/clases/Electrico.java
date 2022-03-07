package clases;

import java.util.GregorianCalendar;

import exceptions.*;

abstract public class Electrico extends Vehiculo{
	
	//propiedades
	private double autonomia;
	private int recarga;
	
	//Getters and Setters
	public double getAutonomia() {
		return autonomia;
	}
	private void setAutonomia(double autonomia) throws Autonomia_no_valida {
		if(autonomia>=0) {
			this.autonomia = autonomia;
		}else {
			throw new Autonomia_no_valida();
		}
		
	}
	public int getRecarga() {
		return recarga;
	}
	private void setRecarga(int recarga) throws Recarga_no_valida {
		if(recarga>=0) {
			this.recarga = recarga;
		}else {
			throw new Recarga_no_valida();
		}
		
	}
	
	
	//Constructores
	
	public Electrico(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq,
			double kms, Categoria categoria, Oficina ubicacion, double autonomia, int recarga) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion);
		setAutonomia(autonomia);
		setRecarga(recarga);
	}
	
	

}
