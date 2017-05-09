import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceResto extends JFrame {

	// Initialisation des caractéristiques de la fenêtre
	private JPanel container = new JPanel();
	private Font font = new Font("Arial", Font.BOLD, 20);
	private Color color = new Color(211, 248, 222);

	// Label pour les infos du resto
	private JLabel nameResto = new JLabel(GestionDonnees.tripletResto[0] + " :");
	private JLabel addrResto = new JLabel(GestionDonnees.tripletResto[1]);
	private JLabel internetResto = new JLabel(GestionDonnees.tripletBar[2]);
	
	// Boutons pour l'URL Google Maps, accepter ou refuser un bar
	private JButton url = new JButton("Lien Google Maps");
	private JButton okResto = new JButton("Je veux ce resto!");
	private JButton nonResto = new JButton("Je ne veux pas ce resto!");

	public InterfaceResto() {
		// On nomme la fenetre
		this.setTitle("Votre restaurant");
		// On definit sa taille en largeur et en hauteur
		this.setSize(500, 300);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Background et Layout
		container.setBackground(color);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

		// Ecrire les informations du bar
		JPanel panelResto = new JPanel();
		panelResto.setBackground(color);
		panelResto.add(nameResto);
		nameResto.setFont(font);
		panelResto.add(addrResto);
		addrResto.setFont(font);
		container.add(panelResto);
		
		// Bouton URL
		JPanel panelURL = new JPanel();
		panelURL.setBackground(color);
		panelURL.add(url);
		url.addActionListener(new BoutonListener3());
		container.add(panelURL);

		// Boutons de validations
		JPanel panelButtonResto = new JPanel();
		panelButtonResto.setBackground(color);
		okResto.addActionListener(new BoutonListener3());
		panelButtonResto.add(okResto);
		nonResto.addActionListener(new BoutonListener3());
		panelButtonResto.add(nonResto);
		container.add(panelButtonResto);

		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public class BoutonListener3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// Quand on accepte pas le resto proposé
			if (e.getSource() == nonResto) {
				try {
					// Rappel de la méthode recherche de resto
					GestionDonnees.NearbySearchResto(GestionDonnees.coordBar[0], GestionDonnees.coordBar[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				// Rappel de l'interface pour le resto
				InterfaceResto newF = new InterfaceResto();
			}
			
			// Quand on accepte le resto proposé
			if (e.getSource() == okResto) {
				// On remet le compteur de recherche à 0 
				GestionDonnees.compteurRecherche = 0;
				try {
					// Appel de la méthode recherche de boite
					GestionDonnees.NearbySearchBoite(GestionDonnees.coordResto[0], GestionDonnees.coordResto[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Appel de l'interface pour la boite
				InterfaceBoite newF = new InterfaceBoite();
				container.setVisible(false);
			}
			
			// Quand on clic sur "Lien Google Maps"
			if (e.getSource() == url){
                String plainText = internetResto.getText();
				try {
					// Ouverture de la page Google Maps sur internet
					Desktop.getDesktop().browse(new URI(plainText));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
