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

	// Pour afficher le bar
	private JLabel nameBoite = new JLabel(GestionDonnees.tripletBoite[0] + " :");
	private JLabel addrBoite = new JLabel(GestionDonnees.tripletBoite[1]);
	private JLabel internetBoite = new JLabel(GestionDonnees.tripletBoite[2]);
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

		internetBoite.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addListener(internetBoite);
		// Ecrire les informations de la boite
		JPanel panelBoite = new JPanel();
		panelBoite.setBackground(Color.white);
		panelBoite.add(nameBoite);
		nameBoite.setFont(font);
		panelBoite.add(addrBoite);
		addrBoite.setFont(font);
		panelBoite.add(internetBoite);
		container.add(panelBoite);

		// Boutons validations
		JPanel panelButtonBoite = new JPanel();
		panelButtonBoite.setBackground(Color.white);
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
            //Click sur le lien
            public void mouseClicked(MouseEvent e) {
                JLabel label=(JLabel)e.getSource();
                String plainText = label.getText();
                System.out.println(plainText);
                try {
                    Desktop.getDesktop().browse(new URI(plainText));
                } catch (URISyntaxException ex) {
                    //Logger.getLogger(JLabelHyperlink.class.getName()).log(Level.SEVERE, null, ex);
                	System.out.println("Erreur");
                } catch (IOException ex) {
                    //Logger.getLogger(JLabelHyperlink.class.getName()).log(Level.SEVERE, null, ex);
                	System.out.println("Erreur !");
                }
            }
    	});
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
