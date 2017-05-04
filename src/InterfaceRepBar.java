import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceRepBar extends JFrame {
	private JPanel container = new JPanel();
	private Font font = new Font("Arial", Font.BOLD, 20);

	// Pour afficher le bar
	public static JFrame fBar = new JFrame();
	public static JLabel nameBar = new JLabel(GestionDonnees.tripletBar[0] + " :");
	public static JLabel addrBar = new JLabel(GestionDonnees.tripletBar[1]);
	// private JLabel internetBar = new JLabel(GestionDonnees.tripletBar[2]);
	// Boutons pour accepter ou refuser
	public static JButton okBar = new JButton("Je veux ce bar!");
	public static JButton nonBar = new JButton("Je ne veux pas ce bar!");

	// Pour afficher le resto
	JFrame fResto = new JFrame();
	private JLabel nameResto = new JLabel(GestionDonnees.tripletResto[0]);
	private JLabel addrResto = new JLabel(GestionDonnees.tripletResto[1]);
	// private JLabel internetResto = new
	// JLabel(GestionDonnees.tripletResto[2]);
	// Boutons pour accepter ou refuser
	private JButton okResto = new JButton("Je veux ce resto!");
	private JButton nonResto = new JButton("Je ne veux pas ce resto!");

	// Pour afficher le Boite
	private JLabel nameBoite = new JLabel(GestionDonnees.tripletBoite[0]);
	private JLabel addrBoite = new JLabel(GestionDonnees.tripletBoite[1]);
	// private JLabel internetBoite = new
	// JLabel(GestionDonnees.tripletBoite[2]);
	// Boutons pour accepter ou refuser
	private JButton okBoite = new JButton("Je veux cette boite!");
	private JButton nonBoite = new JButton("Je ne veux pas cette boite!");

	public InterfaceRepBar() {

			// On nomme la fenetre
			this.setTitle("Kayak");
			// On definit sa taille en largeur et en hauteur
			this.setSize(500, 300);
			// On place la fenetre au milieu
			this.setLocationRelativeTo(null);
			// On termine le processus lorsqu'on clique sur la croix rouge
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			container.setBackground(Color.white);
			container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

			// Fenetre pour le bar
			fBar.setTitle("Kayak");
			fBar.setSize(50, 50);
			fBar.setLocationRelativeTo(null);

			// Ecrire les informations du bar
			JPanel panelBar = new JPanel();
			panelBar.add(nameBar);
			nameBar.setFont(font);
			panelBar.add(addrBar);
			addrBar.setFont(font);
			// panelBar.add(internetBar);
			container.add(panelBar);

			// Boutons validations
			JPanel panelButtonBar = new JPanel();
			okBar.addActionListener(new BoutonListener2());
			panelButtonBar.add(okBar);
			nonBar.addActionListener(new BoutonListener2());
			panelButtonBar.add(nonBar);
			container.add(panelButtonBar);

			this.setContentPane(container);
			this.setVisible(true);
		}


	class BoutonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == okBar) {
				GestionDonnees.compteurRecherche = 0;
				try {
					GestionDonnees.NearbySearchResto(GestionDonnees.centerLat, GestionDonnees.centerLng);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			/**	// Fenetre pour le bar
				fResto.setTitle("Kayak");
				fResto.setSize(50, 50);
				fResto.setLocationRelativeTo(null);

				// Ecrire les informations du bar
				JPanel panelResto = new JPanel();
				panelResto.add(nameBar);
				nameResto.setFont(font);
				panelResto.add(addrBar);
				addrResto.setFont(font);
				// panelBar.add(internetResto);
				container.add(panelResto);

				// Boutons validations
				JPanel panelButtonResto = new JPanel();
				okResto.addActionListener(new BoutonListener2());
				panelButtonResto.add(okResto);
				nonResto.addActionListener(new BoutonListener2());
				panelButtonResto.add(nonResto);
				container.add(panelButtonResto);**/
			}
			if (e.getSource() == nonBar) {
				try {
					GestionDonnees.NearbySearchBar(GestionDonnees.centerLat, GestionDonnees.centerLng);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(true);
				InterfaceRepBar newF = new InterfaceRepBar();
			}
		}
	}
}
