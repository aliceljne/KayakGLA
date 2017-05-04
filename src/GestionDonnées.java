import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.*;

public class GestionDonnees {
	public static String[] tripletBar = new String[3] ;
	public static String[] tripletResto = new String[3] ;
	public static String[] tripletBoite = new String[3] ;
	public static boolean boolBar = false;
	public static boolean boolresto = false;
	public static boolean boolBoite = false;
	public static Utilisateur u = new Utilisateur();
	public static double[] coordLat = new double[5];
	public static double[] coordLng = new double[5];
	public static double centerLat = 48.85340329999999;
	public static double centerLng = 2.3487835999999334;
	JSONObject bars;
	JSONObject restos;
	JSONObject boites;
	static int compteurRecherche = 0;
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

	public static void getCenterCoord() {
		double sommeLat = 0;
		double sommeLng = 0;
		double countLat = 0;
		double countLng = 0;

		for (int i = 0; i < 5; i++) {
			if (coordLat[i] != 0 || coordLng[i] != 0) {
				sommeLat += coordLat[i];
				countLat++;
				sommeLng += coordLng[i];
				countLng++;
			}
		}
		if (countLat != 0 && countLng != 0) {
			centerLat = sommeLat / countLat;
			centerLng = sommeLng / countLng;
		}

		System.out.println("centerLat : " + centerLat);
		System.out.println("centerLng : " + centerLng);
	}
	
	public static void getBarycentre(){
		int numAddr = 1;
		for(String addr : u.adresses){
			try {
				GestionDonnees.addrToCoord(addr, numAddr);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			numAddr++;
		}
		for (int i = 0; i < GestionDonnees.coordLat.length; i++) {
			System.out.println("Lat : " + coordLat[i]);
			System.out.println("Lng : " + coordLng[i]);
		}
		getCenterCoord();
	}

	public static void NearbySearchBar(double Lat, double Lng) throws Exception {

		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius=500&type=bar&key=" + GooglePlacesKey;
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

		
			JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(compteurRecherche);
			tripletBar[0] =   lieu.getString("name");
			tripletBar[1] =   lieu.getString("vicinity");
			tripletBar[2] =   lieu.getString("photo_reference");
			compteurRecherche++;
		
	}
	
	public static void NearbySearchResto(double Lat, double Lng) throws Exception {
		compteurRecherche = 0;
		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius="+u.perimetre+"&type=restaurant&name="+u.preferences+"key=" + GooglePlacesKey;
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


			JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(compteurRecherche);
			//JSONObject photo = (lieu.getJSONArray("photos")).getJSONObject (compteurRecherche);
			tripletBar[0] =   lieu.getString("name");
			tripletBar[1] =   lieu.getString("vicinity");
			//tripletBar[2] =   photo.getJSONArray("html_attributions");

		
		
	}

	public static void main(String[] args) throws Exception {
		Interface fenetre = new Interface();
		
		// Proxy du PUIO
		System.setProperty("https.proxyHost", "cache.u-psud.fr");
		System.setProperty("https.proxyPort", "8080");

		/**
		 * String addr1 = "116 rue Réaumur, 75002, Paris"; addrToCoord(addr1,
		 * 1); String addr2 = "Rue de l'Inquisition 2, 1000 Bruxelles, Belgique"
		 * ; addrToCoord(addr2,2); String addr3 =
		 * "Calle Rio Azuer, 45007 Toledo, Espagne"; addrToCoord(addr3,3);
		 **/

		/**
		 * int numAddr = 0; for(String addr : fenetre.u.adresses){
		 * addrToCoord(addr, numAddr); numAddr++; }
		 **/

		/**
		 * for (int i = 0; i < coordLat.length; i++) { System.out.println(
		 * "Lat : " + coordLat[i]); System.out.println("Lng : " + coordLng[i]);
		 * }
		 **/

		// getCenterCoord();
		

	}

}
