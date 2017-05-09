import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceBoite extends JFrame {

	private JPanel container = new JPanel();
	private Font font = new Font("Arial", Font.BOLD, 20);
	private Color color = new Color(255, 255, 200);

	// Pour afficher la boite
	private JLabel nameBoite = new JLabel(GestionDonnees.tripletBoite[0] + " :");
	private JLabel addrBoite = new JLabel(GestionDonnees.tripletBoite[1]);
	private JLabel internetBoite = new JLabel(GestionDonnees.tripletBar[2]);
	// Boutons pour accepter ou refuser
	private JButton url = new JButton("Lien Google Maps");
	private JButton okBoite = new JButton("Je veux cette boite!");
	private JButton nonBoite = new JButton("Je ne veux pas cette boite!");
	// Bouton fin
	private JButton ciao = new JButton("Bonne soirée !!");

	public InterfaceBoite() {
		// On nomme la fenetre
		this.setTitle("Votre boite");
		// On definit sa taille en largeur et en hauteur
		this.setSize(500, 300);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setBackground(color);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

		// Ecrire les informations du bar
		JPanel panelBoite = new JPanel();
		panelBoite.setBackground(color);
		panelBoite.add(nameBoite);
		nameBoite.setFont(font);
		panelBoite.add(addrBoite);
		addrBoite.setFont(font);
		// panelBar.add(internetBar);
		container.add(panelBoite);

		// Bouton URL
		JPanel panelURL = new JPanel();
		panelURL.setBackground(color);
		panelURL.add(url);
		url.addActionListener(new BoutonListener4());
		container.add(panelURL);

		// Boutons validations
		JPanel panelButtonBoite = new JPanel();
		panelButtonBoite.setBackground(color);
		okBoite.addActionListener(new BoutonListener4());
		panelButtonBoite.add(okBoite);
		nonBoite.addActionListener(new BoutonListener4());
		panelButtonBoite.add(nonBoite);
		container.add(panelButtonBoite);

		this.setContentPane(container);
		this.setVisible(true);
	}

	public void addListener(JLabel label_url) {
		label_url.addMouseListener(new MouseAdapter() {
			// Click sur le lien
			public void mouseClicked(MouseEvent e) {
				JLabel label = (JLabel) e.getSource();
				String plainText = label.getText();
				System.out.println(plainText);
				try {
					Desktop.getDesktop().browse(new URI(plainText));
				} catch (URISyntaxException ex) {
					// Logger.getLogger(JLabelHyperlink.class.getName()).log(Level.SEVERE,
					// null, ex);
					System.out.println("Erreur");
				} catch (IOException ex) {
					// Logger.getLogger(JLabelHyperlink.class.getName()).log(Level.SEVERE,
					// null, ex);
					System.out.println("Erreur !");
				}
			}
		});
	}

	public class BoutonListener4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == nonBoite) {
				try {
					GestionDonnees.NearbySearchBoite(GestionDonnees.coordBar[0], GestionDonnees.coordBar[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				InterfaceBoite newF = new InterfaceBoite();
			}

			if (e.getSource() == url) {
				String plainText = internetBoite.getText();
				try {
					Desktop.getDesktop().browse(new URI(plainText));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (e.getSource() == okBoite) {
				container.setVisible(false);
				// Fenetre pour patienter
				JFrame finale = new JFrame();
				finale.setTitle("Kayak");
				finale.setSize(370, 150);
				finale.setLocationRelativeTo(null);
				Color c = new Color(233, 218, 250);

				// Fenêtre finale triplet
				JPanel panelFinale = new JPanel();
				panelFinale.setBackground(c);
				panelFinale.setLayout(new BoxLayout(panelFinale, BoxLayout.PAGE_AXIS));
				Font font2 = new Font("Arial", Font.CENTER_BASELINE, 14);
				JLabel l1 = new JLabel("Vous avez choisi : ");
				l1.setFont(font2);
				panelFinale.add(l1);
				JLabel nameBar = new JLabel(GestionDonnees.tripletBar[0]);
				// nameBar.setFont(font);
				panelFinale.add(nameBar);
				JLabel nameResto = new JLabel(GestionDonnees.tripletResto[0]);
				// nameResto.setFont(font);
				panelFinale.add(nameResto);
				JLabel nameBoite = new JLabel(GestionDonnees.tripletBoite[0]);
				// nameResto.setFont(font);
				panelFinale.add(nameBoite);

				// Bouton final
				JPanel buttonCiao = new JPanel();
				buttonCiao.setBackground(c);
				ciao.addActionListener(new BoutonListener4());
				buttonCiao.add(ciao);
				buttonCiao.add(ciao, BorderLayout.PAGE_END);
				panelFinale.add(buttonCiao);

				finale.add(panelFinale);
				finale.setVisible(true);
			}
			if (e.getSource() == ciao) {
				System.exit(0);
			}

		}
	}

}
