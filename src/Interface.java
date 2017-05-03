import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class Interface extends JFrame {
	public Utilisateur u = new Utilisateur();
	private JPanel container = new JPanel();
	String[] tabJour = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	// String[] tabMois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
	// "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
	String[] tabMois = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] tabHoraire = { "16", "17", "18", "19", "20", "21", "22", "23", "00", "1", "2" };
	String[] tabTransport = { "Marche", "Vélo", "Voiture", "Transports en commun" };
	private JCheckBox check1 = new JCheckBox("Végétarien");
	private JCheckBox check2 = new JCheckBox("Grec");
	private JCheckBox check3 = new JCheckBox("Italien");
	private JCheckBox check4 = new JCheckBox("Burger");
	private JCheckBox check5 = new JCheckBox("Japonais");
	private JCheckBox check6 = new JCheckBox("Chinois");
	private JCheckBox check7 = new JCheckBox("Portugais");
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

	public Interface() {
		// On nomme la fenetre
		this.setTitle("Kayak");
		// On definit sa taille en largeur et en hauteur
		this.setSize(800, 600);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setBackground(Color.white);

		// Initialisation des comboBox
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		comboJour.setPreferredSize(new Dimension(100, 20));
		comboMois.setPreferredSize(new Dimension(120, 20));
		comboHoraire.setPreferredSize(new Dimension(100, 20));
		comboTransport.setPreferredSize(new Dimension(120, 20));
		ok1.setPreferredSize(new Dimension(30,20));
		ok2.setPreferredSize(new Dimension(30,20));
		ok3.setPreferredSize(new Dimension(30,20));
		ok4.setPreferredSize(new Dimension(30,20));
		ok5.setPreferredSize(new Dimension(30,20));
		sup1.setPreferredSize(new Dimension(30,20));
		sup2.setPreferredSize(new Dimension(30,20));
		sup3.setPreferredSize(new Dimension(30,20));
		sup4.setPreferredSize(new Dimension(30,20));
		sup5.setPreferredSize(new Dimension(30,20));
		
		/** ComboBox pour la date **/
		JPanel datePanel = new JPanel();
		datePanel.add(jour);
		datePanel.add(comboJour);
		datePanel.add(mois);
		datePanel.add(comboMois);
		container.add(datePanel);
		// Ajout du listener
		comboJour.addItemListener(new ItemState());
		comboMois.addItemListener(new ItemState());

		/** ComboBox pour l'horaire **/
		JPanel horairePanel = new JPanel();
		horairePanel.add(horaire);
		horairePanel.add(comboHoraire);
		horairePanel.add(heures);
		container.add(horairePanel);
		// Ajout du listener
		comboHoraire.addItemListener(new ItemState());

		/** ComboBox pour le transport **/
		JPanel transportPanel = new JPanel();
		transportPanel.add(transport);
		transportPanel.add(comboTransport);
		container.add(transportPanel);
		// Ajout du listener
		comboTransport.addItemListener(new ItemState());

		// CheckBox pour les préférences alimentaires
		JPanel preferencesPanel = new JPanel();
		check1.addActionListener(new StateListener());
		check2.addActionListener(new StateListener());
		check3.addActionListener(new StateListener());
		check4.addActionListener(new StateListener());
		check5.addActionListener(new StateListener());
		check6.addActionListener(new StateListener());
		check7.addActionListener(new StateListener());
		preferencesPanel.add(restos);
		preferencesPanel.add(check1);
		preferencesPanel.add(check2);
		preferencesPanel.add(check3);
		preferencesPanel.add(check4);
		preferencesPanel.add(check5);
		preferencesPanel.add(check6);
		preferencesPanel.add(check7);
		container.add(preferencesPanel);

		// TextBox pour les adresses
		JPanel adressesPanel = new JPanel();
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
		bouton.addActionListener(new BoutonListener());
		JPanel validerPanel = new JPanel();
		validerPanel.add(bouton);
		container.add(validerPanel);

		this.setContentPane(container);
		this.setVisible(true);
	}

	public class StateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// On ajoute les preferences de l'utilisateur
			if (((JCheckBox) e.getSource()).isSelected() == true) {
				u.ajoutPreference(((JCheckBox) e.getSource()).getText());
				u.affichePreferences(u.preferences);
			} else if (((JCheckBox) e.getSource()).isSelected() == false) {
				u.supprimePreference(((JCheckBox) e.getSource()).getText());
				u.affichePreferences(u.preferences);
			}

			// System.out.println("source : " +
			// ((JCheckBox)e.getSource()).getText() + " - état : " +
			// ((JCheckBox)e.getSource()).isSelected());
		}

	}

	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			// Ajout des adresses 
			if (e.getSource() == ok1) {
				u.ajoutAdresse(addr1.getText());
				u.afficheAdresses(u.adresses);
			}
			if (e.getSource() == ok2) {
				u.ajoutAdresse(addr2.getText());
				u.afficheAdresses(u.adresses);
			}
			if (e.getSource() == ok3) {
				u.ajoutAdresse(addr2.getText());
				u.afficheAdresses(u.adresses);
			}
			if (e.getSource() == ok4) {
				u.ajoutAdresse(addr2.getText());
				u.afficheAdresses(u.adresses);
			}
			if (e.getSource() == ok5) {
				u.ajoutAdresse(addr2.getText());
				u.afficheAdresses(u.adresses);
			}
			
			// Suppression des adresses
			
			if (e.getSource() == sup1) {
				u.supprimerAdresse(addr1.getText());
				addr1.setText("");
				u.afficheAdresses(u.adresses);
			}
			if (e.getSource() == sup2) {
				u.supprimerAdresse(addr2.getText());
				u.afficheAdresses(u.adresses);
				addr2.setText("");
			}
			if (e.getSource() == sup3) {
				u.supprimerAdresse(addr3.getText());
				u.afficheAdresses(u.adresses);
				addr3.setText("");
			}
			if (e.getSource() == sup4) {
				u.supprimerAdresse(addr4.getText());
				addr3.setText("");
				u.afficheAdresses(u.adresses);
			}
			if (e.getSource() == sup5) {
				u.supprimerAdresse(addr5.getText());
				addr5.setText("");
				u.afficheAdresses(u.adresses);
			}

			if (e.getSource() == bouton) {
				JFrame f = new JFrame();
				f.setTitle("Merci de patienter...");
				f.setSize(800, 200);
				f.setLocationRelativeTo(null);

				/*
				 * setContentPane(buildContentPane());
				 * 
				 * private JPanel buildContentPane(){ JPanel panel = new
				 * JPanel(); panel.setLayout(new FlowLayout()); JLabel label =
				 * new JLabel("Merci de patienter..."); panel.add(label); return
				 * panel; }
				 */

				f.setVisible(true);
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
				u.ajoutDateEtHoraire(date, horaire);
				u.afficheDateEtHoraire();
			}
			
			// Ajouter le transport
			else {
				String transport = (String) comboTransport.getSelectedItem();
				u.ajoutTransport(transport);
				u.afficheTransport();
				u.definitionPerimetreMax(transport);
				System.out.println(u.perimetre);
			}
		}
	}
	
	public static void main(String[] args) {
		Interface fenetre = new Interface();
	}


}
