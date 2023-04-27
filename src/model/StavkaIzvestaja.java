package model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import util.Konzola;

public class StavkaIzvestaja {

	private String sifra;
	private String naziv;
	private double ukupanPrihod;
	private LocalDateTime datumPoslednjeProdaje;
	
	public StavkaIzvestaja(String sifra, String naziv, double ukupanPrihod, LocalDateTime datumPoslednjeProdaje) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.ukupanPrihod = ukupanPrihod;
		this.datumPoslednjeProdaje = datumPoslednjeProdaje;
	}

	
	//get set
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getUkupanPrihod() {
		return ukupanPrihod;
	}
	public void setUkupanPrihod(double ukupanPrihod) {
		this.ukupanPrihod = ukupanPrihod;
	}
	public LocalDateTime getDatumPoslednjeProdaje() {
		return datumPoslednjeProdaje;
	}
	public void setDatumPoslednjeProdaje(LocalDateTime datumPoslednjeProdaje) {
		this.datumPoslednjeProdaje = datumPoslednjeProdaje;
	}
	
	
	public static int compareUkupanPrihod(StavkaIzvestaja stavka1, StavkaIzvestaja stavka2) {
		return -Double.compare(stavka1.getUkupanPrihod(), stavka2.getUkupanPrihod());
	}
}
