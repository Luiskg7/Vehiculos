package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MetodosGui {
	
	public static void desactivaFormulario (JPanel p)
	{
		for (Component c:p.getComponents())
		{
			if(!(c instanceof JLabel)) {
				c.setEnabled(false);
			}
			//ver si el componente es otro panel para recorrerlo
			if(c instanceof JPanel) {
				desactivaFormulario((JPanel)c);
			}
			
		}
		
	}
	
	public static void activaFormulario (JPanel p)
	{
		for (Component c:p.getComponents())
		{
			if(!(c instanceof JLabel)) {
				c.setEnabled(true);
			}
			//ver si el componente es otro panel para recorrerlo
			if(c instanceof JPanel) {
				desactivaFormulario((JPanel)c);
			}
			
		}
		
	}
	
	public static void limpiaTexto(JPanel p)
	{
		for (Component c:p.getComponents())
		{
			if(c instanceof JTextField) {
				 ((JTextField) c).setText("");
			}
			
			if(c instanceof JCheckBox) {
				((JCheckBox) c).setSelected(false);
			}
			
			if(c instanceof JComboBox) {
				((JComboBox) c).setSelectedIndex(-1);
			}
		}
	}

	public static void centraVentana(JFrame yo) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		yo.setLocation(dim.width/2-yo.getSize().width/2, dim.height/2-yo.getSize().height/2);
	}
}
