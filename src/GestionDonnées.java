import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.*;

public class GestionDonnees {

	Utilisateur utilisateur;
	String jsonToGoogle;
	static double[] coordLat = new double[5];
	static double[] coordLng = new double[5];
	static double centerLat = 48.85340329999999;
	static double centerLng = 2.3487835999999334;
	JSONObject bars;
	JSONObject restos;
	JSONObject boites;
	int compteurRecherche = 1;
	private static String GooglePlacesKey = "AIzaSyCTkWzHYYQvnC4no8p54GI2vXEqO5IljoE";

	public GestionDonnees() {
	}

	// 1600+Amphitheatre+Parkway,+Mountain+View,
	public static String parseAddr(String addr) {
		return addr.replace(' ', '+');
	}

	// Enregistre les coordonnées lat et lng de l'adresse addr numéro numAddr
	public static void addrToCoord(String addr, int numAddr) throws Exception {

		String s = "http://maps.google.com/maps/api/geocode/json?" + "sensor=false&address=";
		String addrParsed = parseAddr(addr);
		s += URLEncoder.encode(addrParsed, "UTF-8");
		URL url = new URL(s);

		// read from the URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		// build a JSON object
		JSONObject obj = new JSONObject(str);
		if (!obj.getString("status").equals("OK"))
			return;

		// get the first result
		JSONObject res = obj.getJSONArray("results").getJSONObject(0);
		System.out.println(res.getString("formatted_address"));
		JSONObject loc = res.getJSONObject("geometry").getJSONObject("location");

		coordLat[numAddr - 1] = loc.getDouble("lat");
		coordLng[numAddr - 1] = loc.getDouble("lng");

		System.out.println("lat: " + loc.getDouble("lat") + ", lng: " + loc.getDouble("lng"));
	}
	
	public static void getCenterCoord(){
		int sommeLat = 0;
		int sommeLng = 0;
		int countLat = 0;
		int countLng = 0;
		
		for (int i=0; i<5; i++){
			if (coordLat[i] != 0 || coordLng[i] != 0){
				sommeLat += coordLat[i];
				countLat ++;
				sommeLng += coordLng[i];
				countLng ++;
			} 
		}
		if (countLat != 0 && countLng != 0){
			centerLat = sommeLat / countLat;
			centerLng = sommeLng / countLng;
		}
	}
	

	public static void main(String[] args) throws Exception {
		 //Interface fenetre = new Interface();

		// Proxy du PUIO
		System.setProperty("https.proxyHost", "cache.u-psud.fr");
		System.setProperty("https.proxyPort", "8080");

		
		  String addr = "213 rue de Versailles, 92410, Ville-d'Avray";
		  addrToCoord(addr, 3);
		  
		  for(int i=0; i<coordLat.length; i++){ System.out.println("Lat : " +
		  coordLat[i]); System.out.println("Lng : " + coordLng[i]); }
		 
	}

}
