package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {

	private VentanaPrincial ventanaPrincial;
	
	public Menu(VentanaPrincial ventanaPrincial) {

		this.ventanaPrincial = ventanaPrincial;
		
		// Configuraciones Generales
		setPreferredSize(new Dimension(150, 630));
		setLayout(new GridLayout(20, 3));
		
		
		JButton nuevo = new JButton("Nuevo");
		JButton reiniciar = new JButton("Reiniciar");
		JButton top = new JButton("Top-10");
		JButton CambJugador = new JButton("Cambiar jugador");
		
		JButton [] botones = {nuevo, reiniciar, top, CambJugador};
		
		add(new JLabel( ));
		
		for (JButton boton : botones) {
			boton.setBackground(Color.decode("#69a8f5"));
			boton.addActionListener( this );
			add(boton);
			add(new JLabel( ));
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		
		switch (command) {
		case "Nuevo":
			ventanaPrincial.nuevoJuego();
			break;
		case "Reiniciar":
			
			ventanaPrincial.reiniciarJuego();
			
			break;
		case "Top-10":
			ventanaPrincial.verTop10();
			break;
		case "Cambiar jugador":
			ventanaPrincial.cambiarJugador();
			break;

		default:
			break;
		}
		

	}

}
