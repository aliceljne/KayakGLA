
public class GestionDonnées {
	
	double perimetre;
	Utilisateur utilisateur;
	String jsonToGoogle;
	JSON bars;
	JSON restos;
	JSON boites;
	int compteurRecherche = 1;
	
	double definitionPerimetreMax(typeTransport t) {
		if (t == typeTransport.Marche) {
			this.perimetre = 1;
		} 
		else if (t == typeTransport.Velo) {
			this.perimetre = 3;
		}  
		else if (t == typeTransport.Voiture) {
			this.perimetre = 5;
		} 
		else if (t == typeTransport.TransportCommun) {
			this.perimetre = 5;
		} 
		return this.perimetre;
	}
	
	JSON convertirStringToJSON (Utilisateur u) {
		
	}
	
	
}
