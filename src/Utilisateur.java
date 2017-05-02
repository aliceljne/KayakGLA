import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utilisateur {
	
	
	public ArrayList<String> adresses = new ArrayList<String>();
	public SimpleDateFormat date;
	public String horaireDebut;
	public ArrayList<String> preferences = new ArrayList<String>();
	public String transport;
	public int nbMaxPers;
	
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
		this.preferences.add(p);
	}
	
	
	

}
