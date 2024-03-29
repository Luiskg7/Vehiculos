package serializacion;

import java.io.*;
import clases.*;

public class Deserializar implements Serializable{
	
	private static  FileInputStream file;
	private static  ObjectInputStream input;
	
	
	/**
	 * Lee los datos de la empresa en un fichero ubicado en la siguiente ruta: "C:/Users/lcgas/Documents/Datos_empresa/empresas.ser"
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void recoger_datos() throws IOException, ClassNotFoundException {
		file = new FileInputStream("C:/Users/lcgas/Documents/Datos_empresa/empresas.ser");
		input = new ObjectInputStream(file);
		
		Empresa.empresa=(Empresa) input.readObject();
		input.close();
		
	}

}
