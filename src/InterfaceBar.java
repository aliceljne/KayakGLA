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


public class InterfaceBar extends JFrame {

	private JPanel container = new JPanel();
	private Font font = new Font("Arial", Font.BOLD, 20);

	// Pour afficher le bar
	private JLabel nameBar = new JLabel(GestionDonnees.tripletBar[0] + " :");
	private JLabel addrBar = new JLabel(GestionDonnees.tripletBar[1]);
	private JLabel internetBar = new JLabel(GestionDonnees.tripletBar[2]);
	// Boutons pour accepter ou refuser
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

		container.setBackground(Color.white);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

		internetBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addListener(internetBar);
		// Ecrire les informations du bar
		JPanel panelBar = new JPanel();
		panelBar.setBackground(Color.white);
		panelBar.add(nameBar);
		nameBar.setFont(font);
		panelBar.add(addrBar);
		addrBar.setFont(font);
		panelBar.add(internetBar);
		container.add(panelBar);

		// Boutons validations
		JPanel panelButtonBar = new JPanel();
		panelButtonBar.setBackground(Color.white);
		okBar.addActionListener(new BoutonListener2());
		panelButtonBar.add(okBar);
		nonBar.addActionListener(new BoutonListener2());
		panelButtonBar.add(nonBar);
		container.add(panelButtonBar);

		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public void addListener(JLabel label_url) {
    	label_url.addMouseListener(new MouseAdapter() {
            //Click sur le lien
            public void mouseClicked(MouseEvent e) {
                JLabel label=(JLabel)e.getSource();
                String plainText = label.getText().replace("</a>", "");
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
	
	public class BoutonListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == nonBar) {
				try {
					GestionDonnees.NearbySearchBar(GestionDonnees.centerLat, GestionDonnees.centerLng);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				InterfaceBar newF = new InterfaceBar();
			}
			if (e.getSource() == okBar) {
				GestionDonnees.compteurRecherche = 0;
				try {
					GestionDonnees.NearbySearchResto(GestionDonnees.coordBar[0], GestionDonnees.coordBar[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				InterfaceResto newF = new InterfaceResto();
			}
		}
	}

}
