package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import accesoBD.VehiculoBD;
import gui.MetodosGui;
import gui.VentanaVehiculo;

public class Eliminar_vehiculo implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String matricula=VentanaVehiculo.textMatricula.getText();
		
		try {
			//JDialog para avisar al usuario de que va a eliminar un vehiculo de la bd
			Object[] options = {"Aceptar",  "Cancelar",};
			int opc = JOptionPane.showOptionDialog(VentanaVehiculo.yo,"¿Desea eliminar este vehiculo?","AVISO",JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
			
			if(opc==0) {
				
				VehiculoBD.eliminaVehiculo(matricula);
				MetodosGui.limpiaTexto(VentanaVehiculo.panel);
				MetodosGui.desactivaFormulario(VentanaVehiculo.panel);
				MetodosGui.limpiaTexto(VentanaVehiculo.tabbedPane);
				MetodosGui.limpiaTexto(VentanaVehiculo.tabbedPane);
				VentanaVehiculo.textMatricula.setEnabled(true);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
