package serializacion;

import java.io.*;
import clases.*;

public class Serializar implements Serializable{
	
	private static FileOutputStream file;
	private static ObjectOutputStream output;
	
	//Abrir el fichero
	 /**
	  * Guarda los datos de la empresa en un fichero en la ruta:"C:/Users/lcgas/Documents/Datos_empresa/empresas.ser"
	  * @throws IOException
	  */
	public static void registrar_datos() throws IOException{
		file = new FileOutputStream("C:/Users/lcgas/Documents/Datos_empresa/empresas.ser");
		output = new ObjectOutputStream(file);
		output.writeObject(Empresa.empresa); 
		output.close();
	}

}
