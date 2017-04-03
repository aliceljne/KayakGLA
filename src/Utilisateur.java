import java.util.ArrayList;
import java.util.Date;

public class Utilisateur {
	
	
	public enum TypeTransport{
		Marche, Velo, Voiture, TransportCommun;
	}
	
	public enum TypeResto{
		Vegetarien, Grec, Italien, Burger;
	}
	
	public ArrayList<String> adresses;
	public Date date;
	public int horaireDebut;
	public ArrayList<TypeResto> preferences;
	public TypeTransport transport;
	public int nbMaxPers;
	
	public Utilisateur(){}
	
	public void ajoutDateEtHoraire(Date d, int h){
		this.date = d;
		this.horaireDebut = h;
	}
	
	public void ajoutAdresse(String a){
		this.adresses.add(a);
	}
	
	public void supprimerAdresse(String a){
		this.adresses.remove(a);
	}
	
	public void ajoutTransport(TypeTransport t){
		this.transport = t;
	}
	
	public void ajoutPreference(TypeResto p){
		this.preferences.add(p);
	}
	
	
	

}
