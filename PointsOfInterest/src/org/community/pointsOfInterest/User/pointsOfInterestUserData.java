package org.community.pointsOfInterest.User;

import java.util.ArrayList;
import java.util.List;

public class pointsOfInterestUserData {
	private int stufe, anzahlBesuchterPOIs;
	private String rang, spielerName;
	private long cooldown;
	private List<String> besuchtePOIs;
	
	public pointsOfInterestUserData(String nameOfPlayer){
		setStufe(0); 
		setAnzahlBesuchterPOIs(0);
		setRang("Frischling");
		setSpielerName(nameOfPlayer);
		setCooldown(System.currentTimeMillis() - 1000000);
		setBesuchtePOIs(new ArrayList<String>());
	}
	
	public pointsOfInterestUserData(String nameOfPlayer, int anzahlBesuchterPOIs, long cooldown){
		setAnzahlBesuchterPOIs(anzahlBesuchterPOIs);
		setStufe(anzahlBesuchterPOIs / 10);
		updateRang();
		setCooldown(cooldown);
		setSpielerName(nameOfPlayer);
		setBesuchtePOIs(new ArrayList<String>());
	}
	
	public pointsOfInterestUserData(String nameOfPlayer, int stufe, int anzahlPOI, String rang, long cooldown, List<String> besuchtePOIs){
		setStufe(stufe); 
		setAnzahlBesuchterPOIs(anzahlPOI);
		setRang(rang);
		setSpielerName(nameOfPlayer);
		setCooldown(cooldown);
		setBesuchtePOIs(besuchtePOIs);
	}

	public int getStufe() {
		return stufe;
	}

	private void setStufe(int stufe) {
		this.stufe = stufe;
	}

	public int getAnzahlBesuchterPOIs() {
		return anzahlBesuchterPOIs;
	}

	public void setAnzahlBesuchterPOIs(int anzahlBesuchterPOIs) {
		this.anzahlBesuchterPOIs = anzahlBesuchterPOIs;
	}

	public String getRang() {
		return rang;
	}
	
	private void setRang(String rang){
		this.rang = rang;
	}

	public String getSpielerName() {
		return spielerName;
	}

	public void setSpielerName(String spielerName) {
		this.spielerName = spielerName.trim();
	}

	public long getCooldown() {
		return cooldown;
	}

	public void setCooldown(long cooldown) {
		this.cooldown = cooldown;
	}

	public List<String> getBesuchtePOIs() {
		return besuchtePOIs;
	}

	public void setBesuchtePOIs(List<String> besuchtePOIs) {
		this.besuchtePOIs = besuchtePOIs;
	}
	
	public void updateRang() {
		String rang = "";
		switch (stufe) {
		case 0:
		case 1:
			rang = "Frischling";
			break;
		case 2:
		case 3:
			rang = "Sucher";
			break;
		case 4:
		case 5:
			rang = "Abenteurer";
			break;
		case 6:
		case 7:
			rang = "Geschichtenerzähler";
			break;
		case 8:
		case 9:
			rang = "Entdecker";
			break;
		case 10:
		case 11:
			rang = "Poi-Meister";
			break;
		default:
			rang = "Rang fehlt. Bitte bei Staff melden";
			break;
		}
		setRang(rang);
	}

	public boolean besuchtenPOIhinzufuegen(String name){
		if(besuchtePOIs.contains(name))
			return false;
		besuchtePOIs.add(name);
		anzahlBesuchterPOIs += 1;
		setStufe(anzahlBesuchterPOIs / 10);
		updateRang();
		return true;
	}
	
	public boolean besuchtenPOIentfernen(String name){
		if(!besuchtePOIs.contains(name))
			return false;
		besuchtePOIs.remove(name);
		anzahlBesuchterPOIs -= 1;
		setStufe(anzahlBesuchterPOIs / 10);
		updateRang();
		return true;
	}
}
