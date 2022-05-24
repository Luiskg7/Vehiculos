package principal;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import clases.Empleado;
import clases.Empresa;
import exceptions.*;
import serializacion.Deserializar;
public class Principal {

	public static void main(String[] args) throws Matricula_no_valida, ClassNotFoundException, IOException, Km_no_valido, Marca_no_valida, Modelo_no_valido, Color_no_valido, Autonomia_no_valida, Recarga_no_valida, Plazas_no_validas, Tipo_no_valido, Codigo_no_valido, Descripcion_no_valida, Recargo_no_valido, Localidad_no_valida, Provincia_no_valida, Opcion_no_valida, SQLException, Longitud_no_valida, Dni_no_valido {
		// TODO Auto-generated method stub
		Empresa empresa=new Empresa();
		empresa=Deserializar.recoger_datos(empresa);
		String [] opc= {"1- Hacer una modificacion","2- Alquilar un vehículo","3- Devolver un vehículo alquilado"};
		String [] opciones= {"1","2","3"};
		
		String paso=interfazUsuario.Menu.menuOpciones(opc,opciones,"Empresa vehiculos","*"); //Menu inicial
		
		switch(paso) { 
		case "1": //Menu para modificaciones
			String [] opc1= {"1- Oficinas","2- Categorias","3- Vehiculos","4- Empleados"};
			String [] opciones1= {"1","2","3","4"};
			
			String paso1=interfazUsuario.Menu.menuOpciones(opc1, opciones1, "Modificaciones", "-");
			
			switch(paso1) {//Menu para las modificaciones de la clase selecionada
				case "1":
					String [] opc11= {"1- Dar de alta una oficina","2- Modificar una oficina ya existente","3- Eliminar una oficina existente"};
					String [] opciones11= {"1","2","3"};
					String paso11=interfazUsuario.Menu.menuOpciones(opc11, opciones11, "Modificaciones", "-");
					
					switch(paso11) {
						case "1":
							//interfazUsuario.Alta.darAltaOficina(empresa);
							break;
						
					}
					break;
					
					
				case "2":
					String [] opc12= {"1- Dar de alta una categoria","2- Modificar una categoria ya existente","3- Eliminar una categoria existente"};
					String [] opciones12= {"1","2","3"};
					String paso12=interfazUsuario.Menu.menuOpciones(opc12, opciones12, "Modificaciones", "-");
					
					switch(paso12) {
						case "1":
							//interfazUsuario.Alta.darAltaCategoria(empresa);
							break;
						
					}
					break;
				case "3"://Menu para seleccionar sobre que tipo de vehiculo se va a hacer las modificaciones
					String [] opc13= {"1- Coche electrico","2- Coche combustion","3- Moto","4- Furgoneta"};
					String [] opciones13= {"1","2","3","4"};
					String paso13=interfazUsuario.Menu.menuOpciones(opc13, opciones13, "¿Sobre que vehiculo será la modificacion?", "-");
					
					switch(paso13) { //Menu de modificaciones a hacer sobre el vehiculo seleccionado
					
						case "1":
							String [] opc131= {"1- Dar de alta un coche","2- Modificar un coche ya existente","3- Eliminar un coche existente"};
							String [] opciones131= {"1","2","3","4"};
							String paso131=interfazUsuario.Menu.menuOpciones(opc131, opciones131, "¿Que desea hacer?", "-");
							switch(paso131) {
								case "1":
									//interfazUsuario.Alta.darAltaCocheElectrico(empresa);
									
								
							}
							
							
						case "2":
							String []opc132= {"1- Dar de alta un coche","2- Modificar un coche ya existente","3- Eliminar un coche existente"};
							String [] opciones132= {"1","2","3","4"};
							String paso132=interfazUsuario.Menu.menuOpciones(opc132, opciones132, "¿Que desea hacer?", "-");
							
						case "3":
							String []opc133= {"1- Dar de alta una moto","2- Modificar una moto ya existente","3- Eliminar una moto existente"};
							String [] opciones133= {"1","2","3","4"};
							String paso133=interfazUsuario.Menu.menuOpciones(opc133, opciones133, "¿Que desea hacer?", "-");
							
						case "4":
							String []opc134= {"1- Dar de alta una furgoneta","2- Modificar una furgoneta ya existente","3- Eliminar una furgoneta existente"};
							String [] opciones134= {"1","2","3","4"};
							String paso134=interfazUsuario.Menu.menuOpciones(opc134, opciones134, "¿Que desea hacer?", "-");
						
					}
					
				case "4": //Menu para modificar los empleados
			}
			
		case "2": //Menu para alquilar un vehiculo
			
			
			
		case "3": //Menu para devolver un vehiculo alquilado
			
			
			
		}

	}

}
