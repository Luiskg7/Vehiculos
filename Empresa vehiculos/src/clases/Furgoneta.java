package clases;

import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;

import exceptions.*;

public class Furgoneta extends Combustion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//propiedades
	private double carga;
	private String carnet;
	
	//Getters and Setters
	public double getCarga() {
		return carga;
	}
	private void setCarga(double carga) throws Carga_no_valida {
		if(carga>=0 && carga<=4250) {
			this.carga = carga;
		}else {
			throw new Carga_no_valida();
		}
		
	}
	public String getCarnet() {
		return carnet;
	}
	private void setCarnet(String carnet) throws Carnet_no_valido {
		if (carnet.toUpperCase().equals("B") || carnet.toUpperCase().equals("C") || carnet.toUpperCase().equals("D")) {
			this.carnet = carnet;
		}else {
			throw new Carnet_no_valido("El carnet debe ser de tipo B/C/D");
		}
		
	}
	
	
	//Constructores
	/**
	 * 
	 * @param matricula String formado por 4 numeros seguido de 3 caracteres
	 * @param marca String de la marca del coche
	 * @param modelo String del modelo de coche
	 * @param color String del color del coche
	 * @param fecha_adq Tipo de dato GregorianCalendar de la fecha en la que se adquirió el coche
	 * @param kms Int del numero de kilometros que tiene el coche. Puede ser 0 si resulta ser nuevo
	 * @param categoria Variable de tipo categoria
	 * @param ubicacion Variable de tipo oficina en la que se encuentra el coche
	 * @param consumo Double de consumo de litros del coche por cada 100km
	 * @param potencia Int de CV del coche
	 * @param emisiones String de 1 caracter que puede ser A/B/C
	 * @param carga Double de la capacidad de carga de la furgoneta en metros cuadrados
	 * @param carnet String de 1 caracter que determina el tipo de carnet, las opciones son B/C/D
	 * @throws Km_no_valido
	 * @throws Matricula_no_valida
	 * @throws Marca_no_valida
	 * @throws Modelo_no_valido
	 * @throws Color_no_valido
	 * @throws Emision_no_valida
	 * @throws Potencia_no_valida
	 * @throws Consumo_no_valido
	 * @throws Carga_no_valida
	 * @throws Carnet_no_valido
	 */
	
	public Furgoneta(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq,
			double kms, Categoria categoria, Oficina ubicacion, double consumo, int potencia, String emisiones,
			double carga, String carnet) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido,
			Color_no_valido, Emision_no_valida, Potencia_no_valida, Consumo_no_valido, Carga_no_valida, Carnet_no_valido {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion, consumo, potencia, emisiones);
		setCarga(carga);
		setCarnet(carnet);
	}
	
	public Furgoneta(String matricula, String marca, String modelo, String color, Date fecha_adq2,
			double kms, Categoria categoria, Oficina ubicacion, double consumo, int potencia, String emisiones,
			double carga, String carnet) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido,
			Color_no_valido, Emision_no_valida, Potencia_no_valida, Consumo_no_valido, Carga_no_valida, Carnet_no_valido {
		super(matricula, marca, modelo, color, fecha_adq2, kms, categoria, ubicacion, consumo, potencia, emisiones);
		setCarga(carga);
		setCarnet(carnet);
	}
	
	@Override
	public String toString() {
		return getMarca()+" "+getModelo();
	}
	public boolean equals(Vehiculo a) {
		boolean igual=false;
		if (a!=null) {
			if (a.matricula.equals(this.matricula)) {
				igual=true;
			}
		}
		return igual;
	}

}
