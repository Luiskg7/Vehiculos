package gui;

import java.awt.Component;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;

import java.sql.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JCalendar;

public class MetodosGui {
	
	public static void desactivaFormulario (JPanel p)
	{
		for (Component c:p.getComponents())
		{
			if(!(c instanceof JLabel)) {
				c.setEnabled(false);
			}
			if(c instanceof JCalendar) {
				c.setEnabled(false);
			}
			//ver si el componente es otro panel para recorrerlo
			if(c instanceof JPanel) {
				desactivaFormulario((JPanel)c);
			}
			if(c instanceof JTabbedPane) {
				desactivaFormulario((JTabbedPane)c);
			}
			
			
		}
		
	}
	
	public static void desactivaFormulario (JTabbedPane p)
	{
		for (Component c:p.getComponents())
		{
			if(!(c instanceof JLabel)) {
				c.setEnabled(false);
			}
			if(c instanceof JCalendar) {
				c.setEnabled(false);
			}
			//ver si el componente es un panel para recorrerlo
			if(c instanceof JPanel) {
				desactivaFormulario((JPanel)c);
			}
			if(c instanceof JTabbedPane) {
				desactivaFormulario((JTabbedPane)c);
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
			if(c instanceof JCalendar) {
				c.setEnabled(true);
			}
			//ver si el componente es otro panel para recorrerlo
			if(c instanceof JPanel) {
				activaFormulario((JPanel)c);
			}
			if(c instanceof JTabbedPane) {
				activaFormulario((JTabbedPane)c);
			}
			
		}
		
	}
	
	public static void activaFormulario (JTabbedPane p)
	{
		for (Component c:p.getComponents())
		{
			if(!(c instanceof JLabel)) {
				c.setEnabled(true);
			}
			if(c instanceof JCalendar) {
				c.setEnabled(true);			
			}
			//ver si el componente es un panel para recorrerlo
			if(c instanceof JPanel) {
				activaFormulario((JPanel)c);
			}
			if(c instanceof JTabbedPane) {
				activaFormulario((JTabbedPane)c);
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
			
			if(c instanceof JCalendar) {
				((JCalendar) c).setCalendar(null);
			}
		}
	}
	
	public static void limpiaTexto(JTabbedPane p)
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
			if(c instanceof JPanel) {
				limpiaTexto((JPanel) c);
			}
			if(c instanceof JCalendar) {
				((JCalendar)c).setCalendar(null);
			}
		}
	}

	public static void centraVentana(JFrame yo) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		yo.setLocation(dim.width/2-yo.getSize().width/2, dim.height/2-yo.getSize().height/2);
	}
	
	public static void SearchInJtable(JTable table, JTextField textField) 
    {

        TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());

        table.setRowSorter(sort);

        textField.getDocument().addDocumentListener(new DocumentListener()
        {
            public void insertUpdate(DocumentEvent e) {
                String str = textField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    //(?i) means case insensitive search
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
            public void removeUpdate(DocumentEvent e) {
                String str = textField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
            public void changedUpdate(DocumentEvent e) {}
        });
    }

}
