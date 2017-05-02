import javax.swing.*;


public class Interface extends JFrame {
	
	public Interface() {
		// On nomme la fenetre
		this.setTitle("Kayak");
		// On definit sa taille en largeur et en hauteur
		this.setSize(400, 100);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// On rend la fenetre visible
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Interface fenetre = new Interface();
	}
	
}

