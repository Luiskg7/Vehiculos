package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MetodosGui;
import gui.VentanaVehiculo;

public class Cancelar_furgoneta implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		MetodosGui.desactivaFormulario(VentanaVehiculo.panel_4);
		MetodosGui.limpiaTexto(VentanaVehiculo.panel_4);
		MetodosGui.desactivaFormulario(VentanaVehiculo.panel);
		MetodosGui.limpiaTexto(VentanaVehiculo.panel);
		VentanaVehiculo.textMatricula.setEnabled(true);
		VentanaVehiculo.calendarAdq.setCalendar(null);
		VentanaVehiculo.tabbedPane.setEnabled(false);
	}

}
