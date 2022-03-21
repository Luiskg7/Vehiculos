package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import exceptions.*;

public abstract class Vehiculo implements Serializable{
	//propiedades
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	private GregorianCalendar fecha_adq;
	private double kms;
	private Categoria categoria;
	private Oficina ubicacion;
	
	//Getters and Setters
	
	public String getMatricula() {
		return matricula;
	}
	private void setMatricula(String matricula) throws Matricula_no_valida {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	private void setMarca(String marca) throws Marca_no_valida {
		if(marca.length()>=0 && marca.length()<=20) {
			this.marca = marca;
		}else {
			throw new Marca_no_valida();
		}
	}
	public String getModelo() {
		return modelo;
	}
	private void setModelo(String modelo) throws Modelo_no_valido {
		if (modelo.length()>=0 && modelo.length()<=20) {
			this.modelo = modelo;
		}else {
			throw new Modelo_no_valido();
		}
		
	}
	public String getColor() {
		return color;
	}
	private void setColor(String color) throws Color_no_valido {
		this.color = color;
	}
	public GregorianCalendar getFecha_adq() {
		return fecha_adq;
	}
	private void setFecha_adq(GregorianCalendar fecha_adq) {
		this.fecha_adq = fecha_adq;
	} //TODO ver si la fecha es incorrecta o no
	public double getKms() {
		return kms;
	}
	private void setKms(double kms) throws Km_no_valido {
		this.kms = kms;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	private void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Oficina getUbicacion() {
		return ubicacion;
	}
	private void setUbicacion(Oficina ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	//Constructores
	
	public Vehiculo(String matricula, String marca, String modelo, String color, GregorianCalendar fecha_adq,
			double kms, Categoria categoria, Oficina ubicacion) throws Km_no_valido, Matricula_no_valida, Marca_no_valida, Modelo_no_valido, Color_no_valido {
		super();
		setMatricula(matricula);
		setMarca(marca);
		setModelo(modelo);
		setColor(color);
		setFecha_adq(fecha_adq);
		setKms(kms);
		setCategoria(categoria);
		setUbicacion(ubicacion);
	}
	
	
	//Métodos 
	/**
	 * Comprueba si la matrícula introducida tiene un formato válido, devuelve un booleano.
	 * @param matricula Matricula que validar
	 * @return
	 * @throws Matricula_no_valida 
	 */
	public static void validaMatricula(String matricula) throws Matricula_no_valida {
		if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
	        
	    }else{
	    	throw new Matricula_no_valida();
	    } 
		
	}
	
	public static void validaColor(String color) throws Color_no_valido{
		int o=0;
		ArrayList<String> colores=new ArrayList<String>();
		colores.add("negro");
		colores.add("azul");
		colores.add("marron");
		colores.add("gris");
		colores.add("verde");
		colores.add("naranja");
		colores.add("rosa");
		colores.add("purpura");
		colores.add("rojo");
		colores.add("blanco");
		colores.add("amarillo");
		for(String i: colores) {
			if (i.equalsIgnoreCase(color)) {
				o=1;
				break;
			}
		}
		if (o==0) {
			throw new Color_no_valido("Compruebe que el color haya sido escrito correctamente y sin tíldes.");
		}
		
	}
	
	public static void validaKm(double km) throws Km_no_valido {
		if (km>=0) {
			
		}else {
			throw new Km_no_valido("Debe ser superior a 0");
		}
	}
	/**
	 * Compara dos vehiculos a través de su matricula, si son los mismos vehiculos devolverá un 0, si son distintos devolverá un 1
	 */
	public int equals(Vehiculo a) {
		int resultado=0;
		if(this.matricula==a.matricula) {
			resultado=0;
		}else{
			resultado=1;
		}
		
		return resultado;
	}

}
