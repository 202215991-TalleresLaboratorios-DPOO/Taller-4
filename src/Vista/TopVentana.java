package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.RegistroTop10;

public class TopVentana extends JFrame implements MouseMotionListener {

	private VentanaPrincial ventanaPrincipal;
	private JPanel listaTop;
	private Collection<RegistroTop10> listaRejistro ;
	private JLabel[] listaLabels;
	
	public TopVentana(VentanaPrincial ventanaPrincipal) {
		
		// Configuraciones Generales
		setLayout(new BorderLayout());
		setSize(200, 500);
		setResizable(false);
		setLocationRelativeTo(null) ;
		addMouseMotionListener(this);
		
		this.ventanaPrincipal = ventanaPrincipal;
		
		JLabel nombres = new JLabel("# Nombre");
		listaTop =  new JPanel();
		listaRejistro =ventanaPrincipal.getTop();
		listaLabels = new JLabel[10];
		
		
		nombres.setHorizontalAlignment(0);
		nombres.setPreferredSize(new Dimension(WIDTH, 50));
		nombres.setBackground(Color.decode("#69a8f5"));;
		nombres.setOpaque(true);
		add(nombres,BorderLayout.NORTH);
		
		
		cargarTop();
		llenarTop();
	}
	
	public void cargarTop() {
		listaRejistro = ventanaPrincipal.getTop();
	}
	
	public void llenarTop() {
		
	
		listaTop.setLayout(new GridLayout(10, 1));
		
		
		int i = 1;
		for (RegistroTop10 registro: listaRejistro) {
			JLabel datos = new JLabel(i + " " + registro.darNombre() + "..." + registro.darPuntos());
			listaLabels[i-1]=datos;
			datos.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			listaTop.add(datos);
			i ++;
			
		}
		
		add(listaTop);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e!= null) {
			int y = e.getY();
			
			y = y - (500-listaTop.getHeight());
			
			if (y>0) {
				y = y/(listaTop.getHeight()/10);
				for (int i = 0; i<10; i++) {
					if (i==y) {
						listaLabels[y].setBackground(Color.black);
						listaLabels[y].setForeground(Color.WHITE);
						listaLabels[y].setOpaque(true);;
					} else {
						listaLabels[i].setForeground(Color.BLACK);
						listaLabels[i].setOpaque(false);;
					}
				}
			}
		} 
	}

}
