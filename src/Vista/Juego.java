package Vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

public class Juego extends JPanel implements MouseListener, MouseMotionListener {
	
	private int tamaño;
	private int dificultad;
	private Tablero tablero;
	private int xPresente;
	private int yPresente;
	private BufferedImage image;
	private VentanaPrincial ventanaPrincial;
	
	public Juego(VentanaPrincial ventanaPrincial){
		this.ventanaPrincial = ventanaPrincial;
		
		
		
		// Configuraciones Generales
		setBackground(Color.black);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		try {
            // Cargar la imagen desde un archivo
            image = ImageIO.read(new File("./data/luz.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
	}
	
	public void actualizarConfiguraciones() {
		dificultad = ventanaPrincial.getDificultad();
		tamaño = xPresente = yPresente = ventanaPrincial.getTamaño();
		repaint();
	}
	
	public void nuevoJuego() {
		tablero = new Tablero(tamaño);
		tablero.desordenar(dificultad);
		repaint();
	}
	
	public int getJugadas() {
		return tablero.darJugadas();
	}
	
	public void reiniciarTablero() {
		tablero.reiniciar();
		repaint();
	}
	
	public boolean checkGanador() {
		return tablero.tableroIluminado();
		
	}
	
	public int getPuntaje() {
		return tablero.calcularPuntaje();
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		int tamañoRectangulo = (int)600/tamaño;
		RoundRectangle2D.Double rect ;
		RoundRectangle2D.Double rectImg ;
		TexturePaint texture;
	    Color darkGray = new Color(80, 80, 80);
	    Color black = new Color(10, 10, 10);

		
		boolean[][] jugadas = tablero.darTablero();
		for (int x = 0; x < tamaño; x++ ) {
			for (int y = 0; y < tamaño; y++ ) {
				rect = new RoundRectangle2D.Double(x*tamañoRectangulo+5, y*tamañoRectangulo+5, tamañoRectangulo-10, tamañoRectangulo-10,15,15);
				rectImg = new RoundRectangle2D.Double((x*tamañoRectangulo)+20, (y*tamañoRectangulo)+20, tamañoRectangulo-40, tamañoRectangulo-40,15,15);

				if (xPresente == x && yPresente == y) {
					GradientPaint gradient = new GradientPaint((float) rect.getMinX(), (float) rect.getMinY(), Color.decode("#1E90FF"),(float) rect.getMaxX(),(float) rect.getMaxY(), Color.decode("#87CEEB"));
					g2d.setPaint(gradient);
					g2d.fill(rect);
					
				} else if (jugadas[x][y]) {
					// relleno
					
					g2d.setColor(Color.decode("#F9E076"));
					g2d.fill(rect);
					
					texture = new TexturePaint(image, rectImg.getBounds());
					g2d.setPaint(texture);
					g2d.fill(rectImg);
					
			       
				}
				else {
					// relleno
					GradientPaint gradient = new GradientPaint((float) rect.getMinX(), (float) rect.getMinY(), black,(float) rect.getMaxX(),(float) rect.getMaxY(), darkGray);
					g2d.setPaint(gradient);
					g2d.fill(rect);
			       
					
				}			
				g2d.setColor(darkGray);
				g2d.draw(rect);;
			}
			
		}
		
	}

	
	
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		
		if (e!= null) {
			int tRect = (int)600 /tamaño;
			xPresente= e.getX()/tRect;
			yPresente = e.getY()/tRect;

			repaint();			

		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e!= null) {
			boolean hayJugador = ventanaPrincial.existeJugador();
			if (hayJugador) {
				int tRect = (int)630/tamaño;
				int x = e.getX()/tRect;
				int y = e.getY()/tRect;
				tablero.jugar(x, y);
				repaint();
				ventanaPrincial.actualizarContador();;
				ventanaPrincial.checkGanador();
			}
			else {
				JOptionPane.showMessageDialog(null, "Ingresa tu nombre en el cuadro de abajo\n a la derecha para poder continuar.", "Ingresar nombre", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {

	}


	@Override
	public void mouseReleased(MouseEvent e) {

	}


	@Override
	public void mouseEntered(MouseEvent e) {

	}


	@Override
	public void mouseExited(MouseEvent e) {
		xPresente = tamaño;
		yPresente = tamaño;
		repaint();
	}



}
