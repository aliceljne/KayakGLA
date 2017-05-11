import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.*;

public class GestionDonnees {
	public static Utilisateur u = new Utilisateur();
	public static String[] tripletBar = new String[3];
	public static String[] tripletResto = new String[3];
	public static String[] tripletBoite = new String[3];
	public static double[] coordLat = new double[5];
	public static double[] coordLng = new double[5];
	public static double[] coordBar = new double[2];
	public static double[] coordResto = new double[2];
	public static double centerLat = 48.85340329999999;
	public static double centerLng = 2.3487835999999334;
	JSONObject bars;
	JSONObject restos;
	JSONObject boites;
	static int compteurRecherche = 0;
	private static String GooglePlacesKey = "AIzaSyCTkWzHYYQvnC4no8p54GI2vXEqO5IljoE";

	public GestionDonnees() {
	}

	// remplace les " " d'une adresse par un +
	public static String parseAddr(String addr) {
		return addr.replace(' ', '+');
	}


	// Enregistre les coordonnées lat et lng de l'adresse addr numéro numAddr
	public static void addrToCoord(String addr, int numAddr) throws Exception {
		String s = "http://maps.google.com/maps/api/geocode/json?" + "sensor=false&address=";
		String addrParsed = parseAddr(addr);
		s += URLEncoder.encode(addrParsed, "UTF-8");
		URL url = new URL(s);

		// lit l'URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		// fabrique l'objet JSON, arrête la fonction en cas de problème
		JSONObject obj = new JSONObject(str);
		if (!obj.getString("status").equals("OK"))
			return;

		// prend le premier résultat
		JSONObject res = obj.getJSONArray("results").getJSONObject(0);
		System.out.println(res.getString("formatted_address"));
		JSONObject loc = res.getJSONObject("geometry").getJSONObject("location");

		coordLat[numAddr - 1] = loc.getDouble("lat");
		coordLng[numAddr - 1] = loc.getDouble("lng");

		System.out.println("lat: " + loc.getDouble("lat") + ", lng: " + loc.getDouble("lng"));
	}

	// retourne le milieu d'un nuage de points
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

	// retourne le barycentre de plusieurs adresses
	public static void getBarycentre() {
		int numAddr = 1;
		for (String addr : u.adresses) {
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

	// trouve le bar le plus proche des coordonnées fournies en paramètre
	public static void NearbySearchBar(double Lat, double Lng) throws Exception {
		//"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
		//+ "+&rankby=distance "&type=bar&key=" + GooglePlacesKey;
		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius=" + u.perimetre + "&type=bar&key=" + GooglePlacesKey;
		URL url = new URL(s);
		// lit l'URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		// Fabrique le JSON contenant les résultats. arrête la fonction en cas
		// d'erreur ou s'il n'y a pas de résultats
		JSONObject obj = new JSONObject(str);
		if (obj.getString("status").equals("ZERO_RESULTS")) {
			tripletBar[0] = "Pas de bar";
			tripletBar[1] = "  ";
			tripletBar[2] = "  ";
			coordBar[0] = centerLat;
			coordBar[1] = centerLng;
			return;
		} else if (!obj.getString("status").equals("OK")) {
			tripletBar[0] = "Erreur";
			tripletBar[1] = "  ";
			tripletBar[2] = "  ";
			coordBar[0] = centerLat;
			coordBar[1] = centerLng;
			return;
		}
		// donne au tableau tripletBar[] le nom du bar, son adresse courte et
		// le lien google maps
		JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(compteurRecherche % obj.length());
		tripletBar[0] = lieu.getString("name");
		System.out.println(tripletBar[0]);
		tripletBar[1] = lieu.getString("vicinity");
		String tripletunparse = parseAddr(tripletBar[1]);
		System.out.println(tripletBar[1]);
		coordBar[0] = lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
		coordBar[1] = lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
		tripletBar[2] = "https://www.google.com/maps/?q=" + tripletunparse;
		compteurRecherche++;
	}

	// trouve le restaurant le plus proche des coordonnées fournies en paramètre
	public static void NearbySearchResto(double Lat, double Lng) throws Exception {
		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius=" + u.perimetre + "&type=restaurant&name=" + u.preference + "&key=" + GooglePlacesKey;
		//"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
		//		+ "+&rankby=distance&type=restaurant&name=" + u.preference + "&key=" + GooglePlacesKey;
		URL url = new URL(s);
		// lit l'URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		// Fabrique le JSON contenant les résultats. arrête la fonction en cas
		// d'erreur ou s'il n'y a pas de résultats
		JSONObject obj = new JSONObject(str);
		if (obj.getString("status").equals("ZERO_RESULTS")) {
			tripletResto[0] = "Pas de restaurant";
			tripletResto[1] = "  ";
			tripletResto[2] = "  ";
			coordResto[0] = coordBar[0];
			coordResto[1] = coordBar[1];
			return;
		} else if (!obj.getString("status").equals("OK")) {
			tripletResto[0] = "Erreur";
			tripletResto[1] = "  ";
			tripletResto[2] = "  ";
			coordResto[0] = coordBar[0];
			coordResto[1] = coordBar[1];
			return;
		}
		// donne au tableau tripletResto[] le nom du resto, son adresse courte et
		// le lien google maps
		JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(compteurRecherche % obj.length());
		tripletResto[0] = lieu.getString("name");
		tripletResto[1] = lieu.getString("vicinity");
		String tripletunparse = parseAddr(tripletResto[1]);
		tripletResto[2] = "https://www.google.com/maps/?q=" + tripletunparse;
		System.out.println(tripletResto[0]);
		coordResto[0] = lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
		System.out.println(tripletResto[1]);
		coordResto[1] = lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
		compteurRecherche++;
	}

	// trouve la boite de nuit le plus proche des coordonnées fournies en paramètre
	public static void NearbySearchBoite(double Lat, double Lng) throws Exception {
		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius=" + u.perimetre + "&type=night_club&key=" + GooglePlacesKey;
		//"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
		//		+ "+&rankby=distance&type=night_club&key=" + GooglePlacesKey;
		URL url = new URL(s);
		// lit l'URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		// Fabrique le JSON contenant les résultats. arrête la fonction en cas
		// d'erreur ou s'il n'y a pas de résultats
		JSONObject obj = new JSONObject(str);
		if (obj.getString("status").equals("ZERO_RESULTS")) {
			tripletBoite[0] = "Pas de boîte de nuit";
			tripletBoite[1] = "  ";
			tripletBoite[2] = "  ";
			return;
		} else if (!obj.getString("status").equals("OK")) {
			tripletBoite[0] = "Erreur";
			tripletBoite[1] = "  ";
			tripletBoite[2] = "  ";
			return;
		}
		// donne au tableau tripletBoite[] le nom de la boite, son adresse courte et
		// le lien google maps
		JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(compteurRecherche % obj.length());
		tripletBoite[0] = lieu.getString("name");
		System.out.println(tripletBoite[0]);
		tripletBoite[1] = lieu.getString("vicinity");
		System.out.println(tripletBoite[1]);
		String tripletunparse = parseAddr(tripletBoite[1]);
		tripletBoite[2] = "https://www.google.com/maps/?q=" + tripletunparse;
		compteurRecherche++;
	}

	public static void main(String[] args) throws Exception {
		Interface fenetre = new Interface();

		// Proxy du PUIO
		System.setProperty("https.proxyHost", "cache.u-psud.fr");
		System.setProperty("https.proxyPort", "8080");


	}

}
