package clases;

import java.util.GregorianCalendar;

import exceptions.*;

public class Motos extends Electrico{

	//propiedades
	private int cilindrada;
	private String licencia;
	
	//Getters and Setters
	public int getCilindrada() {
		return cilindrada;
	}
	private void setCilindrada(int cilindrada) throws Cilindrada_no_valida {
		if(cilindrada==125 || cilindrada==250 || cilindrada==500) {
			this.cilindrada = cilindrada;
		}else {
			throw new Cilindrada_no_valida("La cilindrada debe ser de 125/250/500");
		}
		
	}
	public String getLicencia() {
		return licencia;
	}
	private void setLicencia(String licencia) throws Carnet_no_valido {
		if(licencia.toUpperCase()=="AM" || licencia.toUpperCase()=="A1" || licencia.toUpperCase()=="A2") {
			this.licencia = licencia;
		}else {
			throw new Carnet_no_valido("El carnet debe ser de tipo AM/A1/A2");
		}
		
	}
	
	//Constructores
	public Motos(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq, double kms,
			Categoria categoria, Oficina ubicacion, double autonomia, int recarga, int cilindrada, String licencia) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida, Carnet_no_valido, Cilindrada_no_valida {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion, autonomia, recarga);
		setCilindrada(cilindrada);
		setLicencia(licencia);
	}
	
	
}
