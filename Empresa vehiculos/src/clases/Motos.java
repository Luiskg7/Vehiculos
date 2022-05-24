package clases;

import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;

import exceptions.*;

public class Motos extends Electrico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
			if(licencia.toUpperCase().equals("AM") || licencia.toUpperCase().equals("A1") || licencia.toUpperCase().equals("A2")) {
				this.licencia = licencia;
			}else {
				throw new Carnet_no_valido("El carnet debe ser de tipo AM/A1/A2");
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
		 * @param autonomia Variable de tipo double que define los kilometros de autonomia del coche
		 * @param recarga Variable de tipo Int que indica los minutos que tarda en recargar el coche
		 * @param cilindrada Int que representa la cilindrada de la moto
		 * @param licencia String de 2 caracteres que forma el carnet que requiere la moto,las opciones son AM/A1/A2
		 * @throws Km_no_valido
		 * @throws Matricula_no_valida
		 * @throws Marca_no_valida
		 * @throws Modelo_no_valido
		 * @throws Color_no_valido
		 * @throws Autonomia_no_valida
		 * @throws Recarga_no_valida
		 * @throws Carnet_no_valido
		 * @throws Cilindrada_no_valida
		 */
		public Motos(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq, double kms,
				Categoria categoria, Oficina ubicacion, double autonomia, int recarga, int cilindrada, String licencia) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida, Carnet_no_valido, Cilindrada_no_valida {
			super(matricula, marca, modelo, color, fecha_adq, kms, categoria, ubicacion, autonomia, recarga);
			setCilindrada(cilindrada);
			setLicencia(licencia);
		}
		
		public Motos(String matricula, String marca, String modelo, String color, Date fecha_adq2, double kms,
				Categoria categoria, Oficina ubicacion, double autonomia, int recarga, int cilindrada, String licencia) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida, Carnet_no_valido, Cilindrada_no_valida {
			super(matricula, marca, modelo, color, fecha_adq2, kms, categoria, ubicacion, autonomia, recarga);
			setCilindrada(cilindrada);
			setLicencia(licencia);
		}
	

}
