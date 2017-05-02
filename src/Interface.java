import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class Interface extends JFrame {
	Utilisateur u = new Utilisateur();
	private JPanel container = new JPanel();
	String[] tabJour = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};			
	String[] tabMois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
	String[] tabHoraire = {"16h", "17h", "18h", "19h", "20h", "21h", "22h", "23h", "00h", "1h", "2h"};
	String[] tabTransport = {"Marche", "Vélo", "Voiture", "Transports en commun"};
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
	private JLabel transport = new JLabel("Transport");
	private JLabel restos = new JLabel("Type(s) de restaurant :");
	private JLabel adresses = new JLabel("Adresse(s) :");
	private JButton bouton = new JButton("Valider");
	private JTextField addr1 = new JTextField("");
	private JTextField addr2 = new JTextField("");
	private JTextField addr3 = new JTextField("");
	private JTextField addr4 = new JTextField("");
	private JTextField addr5 = new JTextField("");
	private JButton ok1 = new JButton("Ajouter");
	private JButton ok2 = new JButton("Ajouter");
	private JButton ok3 = new JButton("Ajouter");
	private JButton ok4 = new JButton("Ajouter");
	private JButton ok5 = new JButton("Ajouter");
	
	public Interface() {
		// On nomme la fenetre
		this.setTitle("Kayak");
		// On definit sa taille en largeur et en hauteur
		this.setSize(800, 650);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setBackground(Color.white);
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		comboJour.setPreferredSize(new Dimension(100, 20));
		comboMois.setPreferredSize(new Dimension(120, 20));
		comboHoraire.setPreferredSize(new Dimension(100, 20));
		comboTransport.setPreferredSize(new Dimension(200, 20));
		
		JPanel datePanel = new JPanel();
		datePanel.add(jour);
		datePanel.add(comboJour);
		datePanel.add(mois);
		datePanel.add(comboMois);
		container.add(datePanel);

		JPanel horairePanel = new JPanel();
		horairePanel.add(horaire);
		horairePanel.add(comboHoraire);
		container.add(horairePanel);
		//container.add(horairePanel, FlowLayout.CENTER);
		
		JPanel transportPanel = new JPanel();
		transportPanel.add(transport);
		transportPanel.add(comboTransport);
		container.add(transportPanel);
		//	container.add(transportPanel, FlowLayout.CENTER);
		
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
	    ok1.addActionListener(new BoutonListener());
	    ok2.addActionListener(new BoutonListener());
	    ok3.addActionListener(new BoutonListener());
	    ok4.addActionListener(new BoutonListener());
	    ok5.addActionListener(new BoutonListener());
	    adressesPanel.add(adresses);
	    adressesPanel.add(addr1);
	    adressesPanel.add(ok1);
	    adressesPanel.add(addr2);
	    adressesPanel.add(ok2);
	    adressesPanel.add(addr3);
	    adressesPanel.add(ok3);
	    adressesPanel.add(addr4);
	    adressesPanel.add(ok4);
	    adressesPanel.add(addr5);
	    adressesPanel.add(ok5);
	    container.add(adressesPanel);
	    
	    bouton.addActionListener(new BoutonListener());
	    JPanel validerPanel = new JPanel();
	    validerPanel.add(bouton);
	    container.add(validerPanel);
	    
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public SimpleDateFormat getDate(){
		String jour = (String) comboJour.getSelectedItem();
		String mois = (String) comboMois.getSelectedItem();
		SimpleDateFormat date = new SimpleDateFormat();
		return date; 
	}
	
	
	public class StateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// On ajoute les preferences de l'utilisateur
				if(((JCheckBox)e.getSource()).isSelected() == true) {
					u.ajoutPreference(((JCheckBox)e.getSource()).getText());
					u.affiche(u.preferences);
				}
				else if(((JCheckBox)e.getSource()).isSelected() == false) {
					u.supprimePreference(((JCheckBox)e.getSource()).getText());
					u.affiche(u.preferences);
				}
		    }

	}
	class BoutonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      if (e.getSource() == ok1){
	    	  u.ajoutAdresse(addr1.getText());
	    	  u.affiche(u.adresses);
	    	  System.out.println("addr1");
	      }
	      if (e.getSource() == ok2){
	    	  u.ajoutAdresse(addr2.getText());
	    	  u.affiche(u.adresses);
	      }
	      if (e.getSource() == ok3){
	    	  u.ajoutAdresse(addr2.getText());
	    	  u.affiche(u.adresses);
	      }
	      if (e.getSource() == ok4){
	    	  u.ajoutAdresse(addr2.getText());
	    	  u.affiche(u.adresses);
	      }
	      if (e.getSource() == ok5){
	    	  u.ajoutAdresse(addr2.getText());
	    	  u.affiche(u.adresses);
	      }
	      
	      if (e.getSource() == bouton) {
	    	  System.out.println("Valider");
		      JFrame f = new JFrame();
		      f.setTitle("Merci de patienter...");
		      f.setSize(800, 200);
		      f.setLocationRelativeTo(null);
		      
		      /*setContentPane(buildContentPane());
		      
		    private JPanel buildContentPane(){
		    	JPanel panel = new JPanel();
			    panel.setLayout(new FlowLayout());
			    JLabel label = new JLabel("Merci de patienter...");
			    panel.add(label);
			    return panel;
		    }*/
		      f.setVisible(true);
	      }
	      
	    }
	}

	public static void main(String[] args) {
		
		Interface fenetre = new Interface();
	}

}
