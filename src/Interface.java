import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class Interface extends JFrame {
	private JPanel container = new JPanel();
	String[] tabJour = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	String[] tabMois = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] tabHoraire = { "16", "17", "18", "19", "20", "21", "22", "23", "00", "1", "2" };
	String[] tabTransport = { "Marche", "Vélo", "Voiture", "Transports en commun" };
	private ButtonGroup GroupePreference = new ButtonGroup();
	private JRadioButton bouton1 = new JRadioButton("Végétarien");
	private JRadioButton bouton2 = new JRadioButton("Grec");
	private JRadioButton bouton3 = new JRadioButton("Italien");
	private JRadioButton bouton4 = new JRadioButton("Burger");
	private JRadioButton bouton5 = new JRadioButton("Japonais");
	private JRadioButton bouton6 = new JRadioButton("Chinois");
	private JRadioButton bouton7 = new JRadioButton("Portugais");
	private JComboBox comboJour = new JComboBox(tabJour);
	private JComboBox comboMois = new JComboBox(tabMois);
	private JComboBox comboHoraire = new JComboBox(tabHoraire);
	private JComboBox comboTransport = new JComboBox(tabTransport);
	private JLabel jour = new JLabel("Jour");
	private JLabel mois = new JLabel("Mois");
	private JLabel horaire = new JLabel("Horaires");
	private JLabel heures = new JLabel("Heures");
	private JLabel transport = new JLabel("Transport");
	private JLabel restos = new JLabel("Type(s) de restaurant :");
	private JLabel adresses = new JLabel("Adresse(s) :");
	private JButton bouton = new JButton("Valider");
	private JTextField addr1 = new JTextField("");
	private JTextField addr2 = new JTextField("");
	private JTextField addr3 = new JTextField("");
	private JTextField addr4 = new JTextField("");
	private JTextField addr5 = new JTextField("");
	private JButton ok1 = new JButton("+");
	private JButton ok2 = new JButton("+");
	private JButton ok3 = new JButton("+");
	private JButton ok4 = new JButton("+");
	private JButton ok5 = new JButton("+");
	private JButton sup1 = new JButton("-");
	private JButton sup2 = new JButton("-");
	private JButton sup3 = new JButton("-");
	private JButton sup4 = new JButton("-");
	private JButton sup5 = new JButton("-");
	private Color color = new Color(211, 237, 248);

	public Interface() throws IOException {
		// On nomme la fenetre
		this.setTitle("Kayak");
		// On definit sa taille en largeur et en hauteur
		this.setSize(800, 600);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initialisation des comboBox
		container.setBackground(color);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		comboJour.setPreferredSize(new Dimension(100, 20));
		comboMois.setPreferredSize(new Dimension(120, 20));
		comboHoraire.setPreferredSize(new Dimension(100, 20));
		comboTransport.setPreferredSize(new Dimension(120, 20));
		ok1.setPreferredSize(new Dimension(30, 20));
		ok2.setPreferredSize(new Dimension(30, 20));
		ok3.setPreferredSize(new Dimension(30, 20));
		ok4.setPreferredSize(new Dimension(30, 20));
		ok5.setPreferredSize(new Dimension(30, 20));
		sup1.setPreferredSize(new Dimension(30, 20));
		sup2.setPreferredSize(new Dimension(30, 20));
		sup3.setPreferredSize(new Dimension(30, 20));
		sup4.setPreferredSize(new Dimension(30, 20));
		sup5.setPreferredSize(new Dimension(30, 20));

		// ComboBox pour la date
		JPanel datePanel = new JPanel();
		datePanel.setBackground(color);
		datePanel.add(jour);
		datePanel.add(comboJour);
		datePanel.add(mois);
		datePanel.add(comboMois);
		container.add(datePanel);
		// Ajout du listener
		comboJour.addItemListener(new ItemState());
		comboMois.addItemListener(new ItemState());

		// ComboBox pour l'horaire
		JPanel horairePanel = new JPanel();
		horairePanel.setBackground(color);
		horairePanel.add(horaire);
		horairePanel.add(comboHoraire);
		horairePanel.add(heures);
		container.add(horairePanel);
		// Ajout du listener
		comboHoraire.addItemListener(new ItemState());

		// ComboBox pour le transport 
		JPanel transportPanel = new JPanel();
		transportPanel.setBackground(color);
		transportPanel.add(transport);
		transportPanel.add(comboTransport);
		container.add(transportPanel);
		// Ajout du listener
		comboTransport.addItemListener(new ItemState());

		// RadioButton pour les préférences alimentaires
		JPanel preferencesPanel = new JPanel();
		preferencesPanel.setBackground(color);
		bouton1.setBackground(color);
		bouton2.setBackground(color);
		bouton3.setBackground(color);
		bouton4.setBackground(color);
		bouton5.setBackground(color);
		bouton6.setBackground(color);
		bouton7.setBackground(color);
		bouton1.addActionListener(new StateListener());
		bouton2.addActionListener(new StateListener());
		bouton3.addActionListener(new StateListener());
		bouton4.addActionListener(new StateListener());
		bouton5.addActionListener(new StateListener());
		bouton6.addActionListener(new StateListener());
		bouton7.addActionListener(new StateListener());
		GroupePreference.add(bouton1);
		GroupePreference.add(bouton2);
		GroupePreference.add(bouton3);
		GroupePreference.add(bouton4);
		GroupePreference.add(bouton5);
		GroupePreference.add(bouton6);
		GroupePreference.add(bouton7);
		preferencesPanel.add(restos);
		preferencesPanel.add(bouton1);
		preferencesPanel.add(bouton2);
		preferencesPanel.add(bouton3);
		preferencesPanel.add(bouton4);
		preferencesPanel.add(bouton5);
		preferencesPanel.add(bouton6);
		preferencesPanel.add(bouton7);
		container.add(preferencesPanel);

		// TextBox pour les adresses
		JPanel adressesPanel = new JPanel();
		adressesPanel.setBackground(color);
		Font police = new Font("Arial", Font.BOLD, 14);
		addr1.setFont(police);
		addr1.setPreferredSize(new Dimension(250, 30));
		addr1.setForeground(Color.BLACK);
		addr2.setFont(police);
		addr2.setPreferredSize(new Dimension(250, 30));
		addr2.setForeground(Color.BLACK);
		addr3.setFont(police);
		addr3.setPreferredSize(new Dimension(250, 30));
		addr3.setForeground(Color.BLACK);
		addr4.setFont(police);
		addr4.setPreferredSize(new Dimension(250, 30));
		addr4.setForeground(Color.BLACK);
		addr5.setFont(police);
		addr5.setPreferredSize(new Dimension(250, 30));
		addr5.setForeground(Color.BLACK);
		adressesPanel.add(adresses);
		ok1.addActionListener(new BoutonListener());
		ok2.addActionListener(new BoutonListener());
		ok3.addActionListener(new BoutonListener());
		ok4.addActionListener(new BoutonListener());
		ok5.addActionListener(new BoutonListener());
		sup1.addActionListener(new BoutonListener());
		sup2.addActionListener(new BoutonListener());
		sup3.addActionListener(new BoutonListener());
		sup4.addActionListener(new BoutonListener());
		sup5.addActionListener(new BoutonListener());
		adressesPanel.add(addr1);
		adressesPanel.add(ok1);
		adressesPanel.add(sup1);
		adressesPanel.add(addr2);
		adressesPanel.add(ok2);
		adressesPanel.add(sup2);
		adressesPanel.add(addr3);
		adressesPanel.add(ok3);
		adressesPanel.add(sup3);
		adressesPanel.add(addr4);
		adressesPanel.add(ok4);
		adressesPanel.add(sup4);
		adressesPanel.add(addr5);
		adressesPanel.add(ok5);
		adressesPanel.add(sup5);
		container.add(adressesPanel);

		// Bouton de validation
		JPanel validerPanel = new JPanel();
		validerPanel.setBackground(color);
		bouton.addActionListener(new BoutonListener());
		validerPanel.add(bouton);
		container.add(validerPanel);

		this.setContentPane(container);
		this.setVisible(true);

	}

	public class StateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// On ajoute la preference de l'utilisateur
			if (((JRadioButton) e.getSource()).isSelected() == true) {
				GestionDonnees.u.ajoutPreference(((JRadioButton) e.getSource()).getText());
				GestionDonnees.u.affichePreference();
			}
		}

	}

	class ItemState implements ItemListener {
		public void itemStateChanged(ItemEvent e) {

			// Ajouter la date et l'horaire de début
			if (e.getSource() == comboJour || e.getSource() == comboMois || e.getSource() == comboHoraire) {
				String jour = (String) comboJour.getSelectedItem();
				String mois = (String) comboMois.getSelectedItem();
				String dateStr = jour + "/" + mois + "/2017";
				SimpleDateFormat date = new SimpleDateFormat(dateStr);
				String horaire = (String) comboHoraire.getSelectedItem();
				GestionDonnees.u.ajoutDateEtHoraire(date, horaire);
				GestionDonnees.u.afficheDateEtHoraire();
			}

			// Ajouter le transport
			else {
				String transport = (String) comboTransport.getSelectedItem();
				GestionDonnees.u.ajoutTransport(transport);
				GestionDonnees.u.afficheTransport();
				GestionDonnees.u.definitionPerimetreMax(transport);
				System.out.println(GestionDonnees.u.perimetre);
			}
		}
	}

	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Ajout des adresses
			if (e.getSource() == ok1) {
				GestionDonnees.u.ajoutAdresse(addr1.getText());
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			if (e.getSource() == ok2) {
				GestionDonnees.u.ajoutAdresse(addr2.getText());
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			if (e.getSource() == ok3) {
				GestionDonnees.u.ajoutAdresse(addr3.getText());
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			if (e.getSource() == ok4) {
				GestionDonnees.u.ajoutAdresse(addr4.getText());
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			if (e.getSource() == ok5) {
				GestionDonnees.u.ajoutAdresse(addr5.getText());
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}

			// Suppression des adresses
			if (e.getSource() == sup1) {
				GestionDonnees.u.supprimerAdresse(addr1.getText());
				addr1.setText("");
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			if (e.getSource() == sup2) {
				GestionDonnees.u.supprimerAdresse(addr2.getText());
				addr2.setText("");
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			if (e.getSource() == sup3) {
				GestionDonnees.u.supprimerAdresse(addr3.getText());
				addr3.setText("");
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			if (e.getSource() == sup4) {
				GestionDonnees.u.supprimerAdresse(addr4.getText());
				addr3.setText("");
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			if (e.getSource() == sup5) {
				GestionDonnees.u.supprimerAdresse(addr5.getText());
				addr5.setText("");
				GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			}
			
			// bouton de validation
			if (e.getSource() == bouton) {

				container.setEnabled(false);
				container.setVisible(false);

				// Calcule le barycentre des adresses
				GestionDonnees.getBarycentre();
				
				try {
					GestionDonnees.NearbySearchBar(GestionDonnees.centerLat, GestionDonnees.centerLng);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				InterfaceBar rep = new InterfaceBar();
			}

		}

	}

}
