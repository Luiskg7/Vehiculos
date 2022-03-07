package clases;

import java.util.GregorianCalendar;

import exceptions.*;

public class Coche_combustion extends Combustion{

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
	/**
	 * 
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param color
	 * @param fecha_adq
	 * @param kms
	 * @param categoria
	 * @param ubicacion
	 * @param plazas
	 * @param tipo
	 * @throws Km_no_valido
	 * @throws Matricula_no_valida
	 * @throws Marca_no_valida
	 * @throws Modelo_no_valido
	 * @throws Color_no_valido
	 * @throws Potencia_no_valida
	 * @throws Emision_no_valida
	 * @throws Consumo_no_valido
	 * @throws Plazas_no_validas
	 * @throws Tipo_no_valido
	 */
	public Coche_combustion(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq,
			double kms, Categoria categoria, Oficina ubicacion, int plazas, String tipo) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Potencia_no_valida, Emision_no_valida, Consumo_no_valido, Plazas_no_validas, Tipo_no_valido {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion);
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
