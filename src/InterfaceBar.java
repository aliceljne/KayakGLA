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

public class InterfaceBar extends JFrame {

	// Initialisation des caractéristiques de la fenêtre
	private JPanel container = new JPanel();
	private Font font = new Font("Arial", Font.BOLD, 20);
	private Color color = new Color(248, 211, 219);

	// Label pour les infos du bar
	private JLabel nameBar = new JLabel(GestionDonnees.tripletBar[0] + " :");
	private JLabel addrBar = new JLabel(GestionDonnees.tripletBar[1]);
	private JLabel internetBar = new JLabel(GestionDonnees.tripletBar[2]);
	
	// Boutons pour l'URL Google Maps, accepter ou refuser un bar
	private JButton url = new JButton("Lien Google Maps");
	private JButton okBar = new JButton("Je veux ce bar!");
	private JButton nonBar = new JButton("Je ne veux pas ce bar!");

	public InterfaceBar() {
		// On nomme la fenetre
		this.setTitle("Votre bar");
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
		JPanel panelBar = new JPanel();
		panelBar.setBackground(color);
		panelBar.add(nameBar);
		nameBar.setFont(font);
		panelBar.add(addrBar);
		addrBar.setFont(font);
		container.add(panelBar);

		// Bouton URL
		JPanel panelURL = new JPanel();
		panelURL.setBackground(color);
		panelURL.add(url);
		url.addActionListener(new BoutonListener2());
		container.add(panelURL);
		
		// Boutons de validations
		JPanel panelButtonBar = new JPanel();
		panelButtonBar.setBackground(color);
		okBar.addActionListener(new BoutonListener2());
		panelButtonBar.add(okBar);
		nonBar.addActionListener(new BoutonListener2());
		panelButtonBar.add(nonBar);
		container.add(panelButtonBar);

		this.setContentPane(container);
		this.setVisible(true);
	}

	public class BoutonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			// Quand on accepte pas le resto proposé
			if (e.getSource() == nonBar) {
				try {
					// Rappel de la méthode recherche de bar
					GestionDonnees.NearbySearchBar(GestionDonnees.centerLat, GestionDonnees.centerLng);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				// Rappel de l'interface pour le bar
				InterfaceBar newF = new InterfaceBar();
			}
			
			// Quand on accepte le resto proposé
			if (e.getSource() == okBar) {
				// On remet le compteur de recherche à 0 
				GestionDonnees.compteurRecherche = 0;
				try {
					// Appel de la méthode recherche de resto
					GestionDonnees.NearbySearchResto(GestionDonnees.coordBar[0], GestionDonnees.coordBar[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				// Appel de l'interface pour le resto
				InterfaceResto newF = new InterfaceResto();
			}
			
			// Quand on clic sur "Lien Google Maps"
			if (e.getSource() == url){
                String plainText = internetBar.getText();
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
