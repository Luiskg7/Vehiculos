package clases;

import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;

import exceptions.*;

public class Coche_combustion extends Combustion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * @param plazas Int con el numero de plazas del coche
	 * @param tipo String del tipo de coche
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
			double kms, Categoria categoria, Oficina ubicacion, double consumo, int potencia, String emisiones,
			int plazas, String tipo) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido,
			Color_no_valido, Emision_no_valida, Potencia_no_valida, Consumo_no_valido, Plazas_no_validas, Tipo_no_valido {
		super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion, consumo, potencia, emisiones);
		setPlazas(plazas);
		setTipo(tipo);
	}
	
	public Coche_combustion(String matricula, String marca, String modelo, String color, Date fecha_adq2,
			double kms, Categoria categoria, Oficina ubicacion, double consumo, int potencia, String emisiones,
			int plazas, String tipo) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido,
			Color_no_valido, Emision_no_valida, Potencia_no_valida, Consumo_no_valido, Plazas_no_validas, Tipo_no_valido {
		super(matricula, marca, modelo, color, fecha_adq2, kms, categoria, ubicacion, consumo, potencia, emisiones);
		setPlazas(plazas);
		setTipo(tipo);
	}
	
	//Metodos
	/**
	 * Metodo que valida que el tipo de coche que se ha introducido es correcto
	 * @param tipo String del tipo de coche
	 * @return
	 */
	public boolean validaTipo (String tipo) {
		boolean fin=true;
		
		if (tipo.toUpperCase().equals("DEPORTIVO") || tipo.equals("FAMILIAR") || tipo.equals("4x4")) {
			fin=true;
		}else {
			fin=false;
		}
		
		return fin;
	}
	

}
