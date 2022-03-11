package interfazUsuario;

import java.util.Scanner;

public class Menu {


	/**
	 * Método que genera un menú con una serie de opciones introducidas por el usuario.Devuelve un String validado a partir de las opciones
	 * introducidas a elegir.
	 * @param opciones Vector de String con las descripciones de las opciones a elegir
	 * @param opcionvalida Vector de String del String que debe introducir el usuario para poder elegir una de las opciones escritas
	 * @param titulo String que muestra el titulo del menú
	 * @param subrayado String de tamaño 1 que subraya el menú con el caracter introducido
	 * @return String 
	 */
	public static String menuOpciones(String[] opciones,String[] opcionvalida,String titulo,String subrayado)
	{
		String elegido;
		String dato="";
		Scanner lector = new Scanner(System.in);
		
		subrayaCadena(titulo,subrayado);
		
		pintaVector(opciones);
		
		dato=validaOpcion(opcionvalida);
		
		
		
		
		
		return dato;
	}
	
	public static void subrayaCadena (String texto, String caracter)
	{
		System.out.println(texto);
		System.out.println(caracter.repeat(texto.length()));
	}
	
	public static void subrayaCadena (String texto)
	{
		subrayaCadena("-".repeat(texto.length()));
	}

	public static String validaOpcion(String[] opcionesValidas)
	{
		Scanner lector = new Scanner(System.in);
		boolean valido=false;
		String eleccion;
		
		System.out.println("Elija una opción:");
		eleccion=lector.nextLine();
		
		do {
			for (int i=0;i<opcionesValidas.length;i++)
			{
				if (opcionesValidas[i].equals(eleccion))
				{
					valido=true;
					break;
				}
				if (i==opcionesValidas.length-1 && !valido) //TODO
				{
					System.out.print("Opción no valida,por favor introduzca una opción de las siguientes:");
					separaVectores(opcionesValidas,"/");
					eleccion=lector.nextLine();
				}
			}
			
		} while (!valido);
		
		
		return eleccion;
	}
	
	public static void pintaVector (String [] vector)
	{
		for (int i=0;i<vector.length;i++)
		{
			System.out.println(vector[i]);
		}
	}
	
	public static void separaVectores(String [] vector, String separador)
	{
		System.out.println("(");
		for (int i=0;i<vector.length;i++)
		{
			if (!(i==vector.length-1)) {
				System.out.print(vector[i]+separador);
			}else {
				System.out.print(vector[i]);
			}
		}
		System.out.println(")");
		System.out.println();
	}
	
}
