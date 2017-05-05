import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceResto extends JFrame {

	private JPanel container = new JPanel();
	private Font font = new Font("Arial", Font.BOLD, 20);

	// Pour afficher le bar
	private JLabel nameResto = new JLabel(GestionDonnees.tripletResto[0] + " :");
	private JLabel addrResto = new JLabel(GestionDonnees.tripletResto[1]);
	// private JLabel internetBar = new JLabel(GestionDonnees.tripletBar[2]);
	// Boutons pour accepter ou refuser
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

		container.setBackground(Color.white);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));


		// Ecrire les informations du bar
		JPanel panelResto = new JPanel();
		panelResto.setBackground(Color.white);
		panelResto.add(nameResto);
		nameResto.setFont(font);
		panelResto.add(addrResto);
		addrResto.setFont(font);
		// panelBar.add(internetBar);
		container.add(panelResto);

		// Boutons validations
		JPanel panelButtonResto = new JPanel();
		panelButtonResto.setBackground(Color.white);
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
			if (e.getSource() == nonResto) {
				try {
					GestionDonnees.NearbySearchResto(GestionDonnees.coordBar[0], GestionDonnees.coordBar[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				InterfaceResto newF = new InterfaceResto();
			}
			if (e.getSource() == okResto) {
				GestionDonnees.compteurRecherche = 0;
				try {
					GestionDonnees.NearbySearchBoite(GestionDonnees.coordResto[0], GestionDonnees.coordResto[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				InterfaceBoite newF = new InterfaceBoite();
				container.setVisible(false);
			}
		}
	}

}
