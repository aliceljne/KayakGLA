import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utilisateur {
	
	
	public ArrayList<String> adresses = new ArrayList<String>();
	public SimpleDateFormat date;
	public String horaireDebut;
	public ArrayList<String> preferences = new ArrayList<String>();
	public String transport;
	double perimetre; // km
	
	public Utilisateur(){}
	
	public void ajoutDateEtHoraire(SimpleDateFormat d, String h){
		this.date = d;
		this.horaireDebut = h;
	}
	
	public void ajoutAdresse(String a){
		this.adresses.add(a);
	}
	
	public void supprimerAdresse(String a){
		this.adresses.remove(a);
	}
	
	public void ajoutTransport(String t){
		this.transport = t;
	}
	
	public void ajoutPreference(String p){
		this.preferences.add(p);
	}
	
	public void supprimePreference(String p){
		this.preferences.remove(p);
	}
	
	// Méthode qui définit un périmètre 
		public double definitionPerimetreMax(String t) {
			if (t == "Marche") {
				this.perimetre = 1;
			} 
			else if (t == "Vélo") {
				this.perimetre = 3;
			}  
			else if (t == "Voiture") {
				this.perimetre = 5;
			} 
			else if (t == "Transports en commun") {
				this.perimetre = 8;
			} 
			return this.perimetre;
		}
	
	// Affiche date et horaire
	public void afficheDateEtHoraire(){
		System.out.println("Date : "+this.date.format(new Date()));
		System.out.println("Horaire début : "+this.horaireDebut+" heures");
		System.out.println("\n");
	}
	
	// Affiche transport
	public void afficheTransport(){
		System.out.println("Transport : "+this.transport);
	}
	
	// Affiche préférences
	public void affichePreferences(ArrayList<String> a){
		System.out.println("Préférences :");
		for(String line : a){
			System.out.println(line);
		}
		System.out.println("\n");
	}
	
	// Affiche adresses
	public void afficheAdresses(ArrayList<String> a){
		System.out.println("Adresses :");
		for(String line : a){
			System.out.println(line);
		}
		System.out.println("\n");
	}
	

}
