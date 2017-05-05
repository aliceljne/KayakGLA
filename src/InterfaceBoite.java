import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceBoite extends JFrame {

	private JPanel container = new JPanel();
	private Font font = new Font("Arial", Font.BOLD, 20);

	// Pour afficher le bar
	private JLabel nameBoite = new JLabel(GestionDonnees.tripletBoite[0] + " :");
	private JLabel addrBoite = new JLabel(GestionDonnees.tripletBoite[1]);
	// private JLabel internetBar = new JLabel(GestionDonnees.tripletBar[2]);
	// Boutons pour accepter ou refuser
	private JButton okBoite = new JButton("Je veux cette boite!");
	private JButton nonBoite = new JButton("Je ne veux pas cette boite!");

	public InterfaceBoite() {
		// On nomme la fenetre
		this.setTitle("Votre boite");
		// On definit sa taille en largeur et en hauteur
		this.setSize(500, 300);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setBackground(Color.white);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));


		// Ecrire les informations du bar
		JPanel panelBoite = new JPanel();
		panelBoite.add(nameBoite);
		nameBoite.setFont(font);
		panelBoite.add(addrBoite);
		addrBoite.setFont(font);
		// panelBar.add(internetBar);
		container.add(panelBoite);

		// Boutons validations
		JPanel panelButtonBoite = new JPanel();
		okBoite.addActionListener(new BoutonListener4());
		panelButtonBoite.add(okBoite);
		nonBoite.addActionListener(new BoutonListener4());
		panelButtonBoite.add(nonBoite);
		container.add(panelButtonBoite);

		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public class BoutonListener4 implements ActionListener{
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
			if (e.getSource() == okBoite) {
				
				container.setVisible(false);
			}
		}
	}

}
