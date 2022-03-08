package principal;

import java.util.GregorianCalendar;
import interfazUsuario.*;

import clases.*;
import exceptions.*;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Cliente yo = new Cliente("Luis", "Gascon", "Morente", "77692947M", "B", "834o63846");
		} catch (Carnet_no_valido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Longitud_no_valida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Tarjeta_no_valida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Dni_no_valido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
