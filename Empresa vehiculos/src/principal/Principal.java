package principal;
import clases.*;
import exceptions.*;
import interfazUsuario.*;
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String [] opc= {"1- Hacer una modificacion","2- Alquilar un veh�culo","3- Devolver un veh�culo alquilado"};
		String [] opciones= {"1","2","3"};
		
		interfazUsuario.Menu.menuOpciones(opc,opciones,"Empresa vehiculos","*");

	}

}
