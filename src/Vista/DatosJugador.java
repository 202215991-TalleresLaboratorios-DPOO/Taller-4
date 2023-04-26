package Vista;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DatosJugador extends JPanel {
	
	private JTextField jugadasInfo;
	private JTextField nombreJugador;

	public DatosJugador(){
		
		
		int alto = 50;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(Box.createRigidArea(new Dimension(10,alto)));

		add(new JLabel("Jugadas:"));
		add(Box.createRigidArea(new Dimension(50,alto)));

		
		jugadasInfo = new JTextField("0");
		
		add(jugadasInfo);
		jugadasInfo.setEditable(false);
		jugadasInfo.setDisabledTextColor(Color.black);
		
		add(Box.createRigidArea(new Dimension(20,alto)));
		
		JLabel jugador = new JLabel("Jugador:");
		add(jugador);
		add(Box.createRigidArea(new Dimension(50,alto)));

		
		nombreJugador = new JTextField();
		
		add(nombreJugador);
		
		add(Box.createRigidArea(new Dimension(10,alto)));
	}
	
	
	
	public void setJugadas(int jugadas) {
		String jugadasString = Integer.toString(jugadas);
		jugadasInfo.setText(jugadasString);
	}
	
	public boolean existNombre() {
		return !nombreJugador.getText().isEmpty();
	}
	
	public void setNombre(String nombre) {
		 nombreJugador.setText(nombre);;
	}
	public String getNombre() {
		return nombreJugador.getText();
	}
}

