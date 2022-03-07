package clases;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import exceptions.*;

public abstract class Vehiculo {
	//propiedades
	
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
		if (validaMatricula(matricula)) {
			this.matricula = matricula;
		}else {
			throw new Matricula_no_valida();
		}
		
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
		if (validaColor(color)) {
			this.color = color;
		}else {
			throw new Color_no_valido("Compruebe que el color haya sido escrito correctamente y sin tíldes.");
		}
		
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
		if (kms>=0) {
			this.kms = kms;
		}else {
			throw new Km_no_valido("Introduzca un valor mayor o igual a 0");
		}
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
	
	public static boolean validaMatricula(String matricula) {
		boolean fin=true;
		if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
	        fin= true;
	    }else{
	        fin= false;
	    } 
		return fin;
	}
	
	public static boolean validaColor(String color) {
		boolean fin=true;
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
			if (i==color.toLowerCase()) {
				fin=true;
				break;
			}else {
				fin=false;
			}
		}
		return fin;
		
	}

}
