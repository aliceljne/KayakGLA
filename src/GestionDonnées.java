import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.*;

public class GestionDonnees {

	Utilisateur utilisateur;
	String jsonToGoogle;
	static double[] coordLat = new double[5];
	static double[] coordLng = new double[5];
	static double centerLat;
	static double centerLng;
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
		double bounds = new google.maps.LatLngBounds();
		var i;

		// The Bermuda Triangle
		var polygonCoords = [
		  new google.maps.LatLng(25.774252, -80.190262),
		  new google.maps.LatLng(18.466465, -66.118292),
		  new google.maps.LatLng(32.321384, -64.757370),
		  new google.maps.LatLng(25.774252, -80.190262)
		];

		for (i = 0; i < polygonCoords.length; i++) {
		  bounds.extend(polygonCoords[i]);
		}

		// The Center of the Bermuda Triangle - (25.3939245, -72.473816)
		console.log(bounds.getCenter());
	}
	

	public static void main(String[] args) throws Exception {
		// Interface fenetre = new Interface();

		// Proxy du PUIO
		System.setProperty("https.proxyHost", "cache.u-psud.fr");
		System.setProperty("https.proxyPort", "8080");

		/**
		 * String addr = "213 rue de Versailles, 92410, Ville-d'Avray";
		 * addrToCoord(addr, 3);
		 * 
		 * for(int i=0; i<coordLat.length; i++){ System.out.println("Lat : " +
		 * coordLat[i]); System.out.println("Lng : " + coordLng[i]); }
		 **/
	}

}
