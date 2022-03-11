package interfazUsuario;

import clases.*;
import java.util.*;
import exceptions.*;

public class Alta {
	
	static Scanner lector= new Scanner(System.in);

	/**
	 * Metodo que guarda en los datos de la empresa un coche electrico no registrado anteriormente. El método 
	 * pide al usuario todos los datos necesario para generar este vehiculo
	 * @throws Matricula_no_valida
	 */
	public static void darAltaCocheElectrico() throws Matricula_no_valida{
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
			int kms=lector.nextInt();
			lector.nextLine();
			clases.Vehiculo.validaKm(kms);
			coche.add(kms);
		}catch(Km_no_valido e) {
			
		}
		
		System.out.println("Introduce el código de la categoría del coche");
		String categoria=lector.nextLine();
		coche.add(categoria);
		
		System.out.println("Introduzca la oficina del coche");
		String oficina= lector.nextLine();
		coche.add(oficina);
		
		System.out.println("Introduzca el número de plazas del coche");
		int plazas=lector.nextInt();
		lector.nextLine();
		coche.add(plazas);
		
		System.out.println("Introduzca el tipo del coche");
		String tipo=lector.nextLine();
		coche.add(tipo);
		
		
		
	//	Coche_electrico nuevoCocheElectrico= new Coche_electrico(coche.get(1),coche.get(2),coche.get(3),coche.get(4),coche.get(5),coche.get(6),coche.get(7),coche.get(8),coche.get(9),coche.get(10));
		
	}
	
	
	//public static void darAltaCategoria() {
	//	ArrayList categoria=new ArrayList();
		
		//try {
		//	System.out.println("Introduce el código de la categoria a crear");
		//	String codigo=lector.nextLine();
		//	clases.Categoria.validaCodigo(codigo);
		//}catch //TODO hacer el metodo de la clase Categoria
		
		
		
	//	return categoria;
	//}
	
	
	
}
