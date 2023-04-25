package Vista;

import java.awt.*;

import javax.swing.*;

public class Juego extends JFrame{
	
	private Container container;
	private JPanel superior;
	
	public Juego() {
		setTitle("Hola");
		setSize(500, 500);
		container = getContentPane();
	
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		superior = new JPanel();
		Color lateralColor = new Color(50, 50, 255);
		superior.setBackground(lateralColor);
		superior.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel tamañoJLabel = new JLabel("Tamaño:");
		superior.add(tamañoJLabel);
		String[] optionsTamaño = {"3x3", "5x5"};
		JComboBox<String> jComboBox = new JComboBox<>(optionsTamaño);
		superior.add(jComboBox);
		JLabel dificultadJLabel = new JLabel("Dificultad:");
		superior.add(dificultadJLabel);
		
		ButtonGroup dificultadOpciones = new ButtonGroup();
		JRadioButton facilButton = new JRadioButton();
		JRadioButton medioButton = new JRadioButton();
		JRadioButton dificilButton = new JRadioButton();
		
		dificultadOpciones.add(facilButton);
		dificultadOpciones.add(medioButton);
		dificultadOpciones.add(dificilButton);
		
		superior.add(facilButton);
		superior.add(medioButton);
		superior.add(dificilButton);
		superior.setSize(20,20);
		container.add(superior);
		
		// Agregar un espacio en blanco que se expande verticalmente
		container.add(Box.createVerticalGlue());

		// Agregar un panel vacío en la parte inferior para empujar el panel superior hacia arriba
		JPanel inferior = new JPanel();
		inferior.setSize(20,100);
		container.add(inferior);
		repaint();
	}
	
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.setVisible(true);
	}
}
