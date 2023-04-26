package Vista;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Top10;


public class VentanaPrincial extends JFrame {

	private Configuraciones configuraciones;
	private Juego juego;
	private Menu menu;
	private Top10 top10;
	private DatosJugador datosJugador;
	private TopVentana topVentana;
	
	public VentanaPrincial() {
		// Configuraciones Generales
		
		setSize(770, 730);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		configuraciones = new Configuraciones(this);
		add(configuraciones, BorderLayout.NORTH);
		
		
		
		menu = new Menu(this);
		add(menu,BorderLayout.EAST);
		
		datosJugador = new DatosJugador();
		add(datosJugador,BorderLayout.SOUTH);
		
		juego = new Juego(this);
		add(juego, BorderLayout.CENTER);
		nuevoJuego();
	
		top10 = new Top10();
		cargarTop10();
		
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					try {
						salvarRecords();
					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					};
				}

		});
		
	}

	public void nuevoJuego() {
		juego.actualizarConfiguraciones();
		juego.nuevoJuego();
		actualizarContador();
	}
	
	public void reiniciarJuego() {
		juego.reiniciarTablero();
		actualizarContador();
		
	}
	
	public void actualizarContador() {
		int jugadas = juego.getJugadas();
		datosJugador.setJugadas(jugadas);
	}

	public int getTamaño() {
		return configuraciones.getTamaño();
	}

	public int getDificultad() {
		return configuraciones.getDificultad();
	}
	

	public boolean existeJugador() {
		return datosJugador.existNombre();
	}

	public void checkGanador() {
		boolean ganador = juego.checkGanador();
		if (ganador) {
			agregarRegistro();
			JOptionPane.showMessageDialog(null, "¡Ganaste!\nGracias por jugar :)", "¡Felcidades!", JOptionPane.INFORMATION_MESSAGE);
			}

		
	}

	public void verTop10() {
		topVentana = new TopVentana(this);
		topVentana.setVisible(true);
		
	}
	
	public void cargarTop10() {
		top10.cargarRecords(new File("./data/top10.csv"));
	}
	
	public Collection<RegistroTop10> getTop() {
		return top10.darRegistros();
	}

	public void cambiarJugador() {
		datosJugador.setNombre("");
		nuevoJuego();

	}
	
	private void salvarRecords() throws FileNotFoundException, UnsupportedEncodingException {
		top10.salvarRecords(new File("./data/top10.csv"));
		
	}
	
	public void agregarRegistro() {
		String nombre = datosJugador.getNombre();
		int puntaje = juego.getPuntaje();
		top10.agregarRegistro(nombre, puntaje);
	}
	
	public static void main(String[] args) {
		FlatLightLaf.install();
		VentanaPrincial ventanaPrincial = new VentanaPrincial();
		ventanaPrincial.setLocationRelativeTo( null );
		ventanaPrincial.setVisible(true);
		
	}
	



}
