package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Configuraciones extends JPanel {
	
	private VentanaPrincial ventanaPrincial;
	private JComboBox<String> comboBox;
	private ButtonGroup buttonGroup;
	
	public Configuraciones(VentanaPrincial ventanaPrincial) {
		this.ventanaPrincial = ventanaPrincial;
		//configuraciones generales
		setBackground(Color.decode("#69a8f5"));
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); // selecionar una distibución
		
		// Añadir elementos
		
		add(new JLabel("Tamaño:"));
		
		String[] tamañosLista = {"5x5", "8x8", "10x10"};
		comboBox = new JComboBox<>(tamañosLista);
		add(comboBox); 
		
		crearSelectores();
	}
	
	private void crearSelectores() {
		add(new JLabel("Dificultad:"));
		buttonGroup = new ButtonGroup();
		
		JRadioButton facilButton = new JRadioButton();
		JRadioButton medioButton = new JRadioButton();
		JRadioButton dificilButton = new JRadioButton();		
				
		buttonGroup.add(facilButton);
		buttonGroup.add(medioButton);
		buttonGroup.add(dificilButton);
		
		facilButton.setText("Facil");
		facilButton.setActionCommand("Facil");
		medioButton.setText("Medio");
		medioButton.setActionCommand("Medio");
		dificilButton.setText("Dificil");
		dificilButton.setActionCommand("Dificil");
		
		add(facilButton);
		add(medioButton);
		add(dificilButton);
		
		facilButton.setSelected(true);
	}
	
	public int getTamaño() {
		String[] selecionado = ((String)comboBox.getSelectedItem()).split("x");
		return Integer.parseInt(selecionado[0]);
	}
	
	public int getDificultad() {
		
		ButtonModel selected = buttonGroup.getSelection();
		String dificultad = selected.getActionCommand();
		int num = 0;
		
		switch (dificultad) {
		case "Facil":
			num = 1;
			break;
		case "Medio":
			num = 10;
			break;
		case "Dificil":
			num = 20;
			break;

		default:
			break;
		}
		
		return num;
		
	}


}
