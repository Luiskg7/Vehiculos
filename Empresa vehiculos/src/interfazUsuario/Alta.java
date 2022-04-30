package interfazUsuario;

import clases.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import exceptions.*;
import principal.Principal;
import serializacion.Serializar;

public class Alta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Scanner lector= new Scanner(System.in);

	/**
	 * Metodo que guarda en los datos de la empresa un coche electrico no registrado anteriormente. El método 
	 * pide al usuario todos los datos necesario para generar este vehiculo
	 * @throws Matricula_no_valida
	 * @throws Tipo_no_valido 
	 * @throws Plazas_no_validas 
	 * @throws Recarga_no_valida 
	 * @throws Autonomia_no_valida 
	 * @throws Color_no_valido 
	 * @throws Modelo_no_valido 
	 * @throws Marca_no_valida 
	 * @throws Km_no_valido 
	 * @throws IOException 
	 */
	public static void darAltaCocheElectrico(Empresa empresa) throws Matricula_no_valida, Km_no_valido, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida, Plazas_no_validas, Tipo_no_valido, IOException{
		ArrayList coche = new ArrayList();
		
		try {
			System.out.println("Introduzca la matricula del coche");
			String matricula=lector.nextLine();
			clases.Vehiculo.validaMatricula(matricula);
			coche.add(matricula);
		}catch(Matricula_no_valida e) {
			e.getStackTrace();
			
		}
	
		System.out.println("Introduzca la marca del coche");
		String marca=lector.nextLine();
		coche.add(marca);
		
		System.out.println("Introduzca el modelo del coche");
		String modelo=lector.nextLine();
		coche.add(modelo);
		
		try {
			System.out.println("Introduzca el color del coche");
			String color=lector.nextLine();
			clases.Vehiculo.validaColor(color);
			coche.add(color);
		}catch(Color_no_valido e) {
			
		}
		
		Calendar fecha= new GregorianCalendar();
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        String fechanueva=año+"/"+mes+"/"+dia;
        
		coche.add(fechanueva);
		
		try {
			System.out.println("Introduzca los km del coche");
			double kms=lector.nextDouble();
			lector.nextLine();
			clases.Vehiculo.validaKm(kms);
			coche.add(kms);
		}catch(Km_no_valido e) {
			e.printStackTrace();
		}
		
		
		try {
			System.out.println("Introduce el código de la categoría del coche");
			String categoria=lector.nextLine();
			Categoria c=clases.Empresa.compruebaCategoria(categoria,empresa);
			coche.add(c);
		} catch (Categoria_no_existe e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Introduzca la oficina del coche");
			String oficina= lector.nextLine();
			Oficina o=clases.Empresa.compruebaOficina(oficina,empresa);
			coche.add(o);
		} catch (Oficina_no_existe e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Introduzca la autonomia del coche");
		double autonomia=lector.nextDouble();
		lector.nextLine();
		coche.add(autonomia);
		
		System.out.println("Introduzca la recarga del coche");
		int recarga=lector.nextInt();
		lector.nextLine();
		coche.add(recarga);
		
		System.out.println("Introduzca el número de plazas del coche");
		int plazas=lector.nextInt();
		lector.nextLine();
		coche.add(plazas);
		
		System.out.println("Introduzca el tipo del coche");
		String tipo=lector.nextLine();
		coche.add(tipo);
		
		
			
		
		
		
		
		Coche_electrico nuevoCocheElectrico= new Coche_electrico((String) coche.get(0),(String) coche.get(1),(String) coche.get(2),(String) coche.get(3),(GregorianCalendar) coche.get(4),(double) coche.get(5),(Categoria) coche.get(6),(Oficina) coche.get(7),(double) coche.get(8),(int) coche.get(9),(int) coche.get(10), (String) coche.get(11));
		empresa.añadeVehiculo(0,nuevoCocheElectrico);
		Serializar.registrar_datos(empresa);
	}
	
	public static void darAltaMoto(Empresa empresa) throws Matricula_no_valida, Km_no_valido, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida, Plazas_no_validas, Tipo_no_valido, IOException, Carnet_no_valido, Cilindrada_no_valida{
		ArrayList moto = new ArrayList();
		
		try {
			System.out.println("Introduzca la matricula de la moto");
			String matricula=lector.nextLine();
			clases.Vehiculo.validaMatricula(matricula);
			moto.add(matricula);
		}catch(Matricula_no_valida e) {
			e.getStackTrace();
			
		}
	
		System.out.println("Introduzca la marca de la moto");
		String marca=lector.nextLine();
		moto.add(marca);
		
		System.out.println("Introduzca el modelo de la moto");
		String modelo=lector.nextLine();
		moto.add(modelo);
		
		try {
			System.out.println("Introduzca el color de la moto");
			String color=lector.nextLine();
			clases.Vehiculo.validaColor(color);
			moto.add(color);
		}catch(Color_no_valido e) {
			
		}
		
		Calendar fecha= new GregorianCalendar();
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        String fechanueva=año+"/"+mes+"/"+dia;
        
		moto.add(fechanueva);
		
		try {
			System.out.println("Introduzca los km de la moto");
			double kms=lector.nextDouble();
			lector.nextLine();
			clases.Vehiculo.validaKm(kms);
			moto.add(kms);
		}catch(Km_no_valido e) {
			e.printStackTrace();
		}
		
		
		try {
			System.out.println("Introduce el código de la categoría de la moto");
			String categoria=lector.nextLine();
			Categoria c=clases.Empresa.compruebaCategoria(categoria,empresa);
			moto.add(c);
		} catch (Categoria_no_existe e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Introduzca la oficina de la moto");
			String oficina= lector.nextLine();
			Oficina o=clases.Empresa.compruebaOficina(oficina,empresa);
			moto.add(o);
		} catch (Oficina_no_existe e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Introduzca la autonomia de la moto");
		double autonomia=lector.nextDouble();
		lector.nextLine();
		moto.add(autonomia);
		
		System.out.println("Introduzca la recarga de la moto");
		int recarga=lector.nextInt();
		lector.nextLine();
		moto.add(recarga);
		
		System.out.println("Introduzca la cilindrada de la moto");
		int cilindrada=lector.nextInt();
		lector.nextLine();
		moto.add(cilindrada);
		
		System.out.println("Introduzca el carnet requerido para la moto");
		String carnet=lector.nextLine();
		moto.add(carnet);
		
		
			
		
		
		
		
		Motos nuevaMoto= new Motos((String) moto.get(0),(String) moto.get(1),(String) moto.get(2),(String) moto.get(3),(GregorianCalendar) moto.get(4),(double) moto.get(5),(Categoria) moto.get(6),(Oficina) moto.get(7),(double) moto.get(8),(int) moto.get(9),(int) moto.get(10), (String) moto.get(11));
		empresa.añadeVehiculo(0,nuevaMoto);
		Serializar.registrar_datos(empresa);
	}
	
	
	public static void darAltaCategoria(Empresa empresa) throws Codigo_no_valido, Descripcion_no_valida, Recargo_no_valido, IOException {
		ArrayList categoria=new ArrayList();
		
		try {
			System.out.println("Introduce el código de la categoria a crear");
			String codigo=lector.nextLine();
			clases.Categoria.validaCodigo(codigo);
			categoria.add(codigo);
		}catch (Codigo_no_valido e) {
			
		}
		
		System.out.println("Introduce la descripcion de la categoria");
		String descripcion=lector.nextLine();
		categoria.add(descripcion);
		
		System.out.println("Introduce el recargo de la categoria");
		int recargo=lector.nextInt();
		categoria.add(recargo);
		

		Categoria c=new Categoria((String) categoria.get(0),(String) categoria.get(1),(int) categoria.get(2));
		empresa.añadeCategoria(0, c);
		serializacion.Serializar.registrar_datos(empresa);
		
	
	}
}
	
	//public static void darAltaOficina(Empresa empresa) throws Codigo_no_valido, Descripcion_no_valida, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, IOException {
		//ArrayList oficina=new ArrayList();
		
		//try {
		//	System.out.println("Introduzca el código de la oficina a crear:");
		//	String codigo= lector.nextLine();
		//	clases.Oficina.validaCodigo(codigo);
		//	oficina.add(codigo);
		//}catch (Codigo_no_valido e) {
			
		//}
		
		//System.out.println("Introduzca la descripción de la oficina");
		//String descripcion=lector.nextLine();
		//oficina.add(descripcion);
		
		//System.out.println("Introduzca la provincia de la oficina");
		//String provincia=lector.nextLine();
		//oficina.add(provincia);
		
		//System.out.println("Introduzca la localidad de la oficina");
		//String localidad=lector.nextLine();
		//oficina.add(localidad);
		
		//try {
		//	System.out.println("¿Esta oficina dispone de aeropuerto? Introduzca Y/N");
		//	String aeropuerto;
		//	boolean valido=true;
		//	boolean aero=false;
		//	while (valido==false) {
		//		aeropuerto=lector.nextLine();
		//		if (aeropuerto=="Y") {
		//			aero=true;
		//		}else if(aeropuerto!="N") {
		//			System.out.println("Opcion introducida no válida, debe de ser Y/N");
		//		}
		//	}
			
			//oficina.add(aero);
		//}catch(Opcion_no_valida e) {
			
		//}
		
		//Oficina o=new Oficina(oficina.get(0),oficina.get(1),oficina.get(3),oficina.get(2),oficina.get(4));
		//empresa.añadeOficina(0,o);
		//serializacion.Serializar.registrar_datos(empresa);
			
		
	//}
	
	
	
//}
