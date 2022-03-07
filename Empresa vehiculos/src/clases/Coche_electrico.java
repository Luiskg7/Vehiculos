package clases;

import java.util.GregorianCalendar;

import exceptions.*;

abstract public class Coche_electrico extends Electrico{
	
	//propiedades
	private int plazas;
	private String tipo;
	
	//Getters and Setters
	public int getPlazas() {
		return plazas;
	}
	private void setPlazas(int plazas) throws Plazas_no_validas {
		if(plazas>=2 && plazas<=9) {
			this.plazas = plazas;
		}else {
			throw new Plazas_no_validas();
		}
		
	}
	public String getTipo() {
		return tipo;
	}
	private void setTipo(String tipo) throws Tipo_no_valido {
		if (validaTipo(tipo)) {
			this.tipo = tipo;
		}else {
			throw new Tipo_no_valido("Introduzca uno de los siguientes tipos: Deportivo/Familiar/4x4");
		}
		
	}
	
	
	//Constructores

	public Coche_electrico(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq,
			double kms, Categoria categoria, Oficina ubicacion, double autonomia, int recarga, int plazas,
			String tipo) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida, Plazas_no_validas, Tipo_no_valido {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion, autonomia, recarga);
		setPlazas(plazas);
		setTipo(tipo);
	}
	
	//Metodos
	
	public boolean validaTipo (String tipo) {
		boolean fin=true;
		
		if (tipo=="DEPORTIVO" || tipo=="FAMILIAR" || tipo=="4x4") {
			fin=true;
		}else {
			fin=false;
		}
		
		return fin;
	}

}
