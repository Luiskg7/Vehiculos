package clases;

import java.util.GregorianCalendar;

import exceptions.*;

public class Furgoneta extends Combustion{
	
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
		if (carnet.toUpperCase()=="B" || carnet.toUpperCase()=="C" || carnet.toUpperCase()=="D") {
			this.carnet = carnet;
		}else {
			throw new Carnet_no_valido("El carnet debe ser de tipo B/C/D");
		}
		
	}
	
	//Constructores
	
	public Furgoneta(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq,
			double kms, Categoria categoria, Oficina ubicacion, double carga, String carnet) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Potencia_no_valida, Emision_no_valida, Consumo_no_valido, Carnet_no_valido, Carga_no_valida {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion);
		setCarga(carga);
		setCarnet(carnet);
	}
	

}
