package model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Proizvod {

	private final long id;
	private String sifra;
	private String naziv;
	private double cena;
	private boolean besplatnaDostava;
	
	final Set<Porudzbina> porudzbine = new HashSet<>();

	public Proizvod(long id, String sifra, String naziv, double cena, boolean besplatnaDostava) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.cena = cena;
		this.besplatnaDostava = besplatnaDostava;
	}
	public Proizvod(String sifra, String naziv, double cena, boolean besplatnaDostava) {
		super();
		this.id = 0;
		this.sifra = sifra;
		this.naziv = naziv;
		this.cena = cena;
		this.besplatnaDostava = besplatnaDostava;
	}
	public Proizvod() {
		super();
		this.id = 0;
		this.sifra = "";
		this.naziv = "";
		this.cena = 0;
		this.besplatnaDostava = false;
	}
	
	//object
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proizvod other = (Proizvod) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Proizvod [id=" + id + ", sifra=" + sifra + ", naziv=" + naziv + ", cena=" + cena + ", dostava="
				+ (besplatnaDostava ? "besplatna" : "1000 RSD") + "]";
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
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public boolean isBesplatnaDostava() {
		return besplatnaDostava;
	}
	public void setBesplatnaDostava(boolean besplatnaDostava) {
		this.besplatnaDostava = besplatnaDostava;
	}
	public long getId() {
		return id;
	}
	
	//povezivanje
	public Collection<Porudzbina> getPorudzbine() {
		return Collections.unmodifiableCollection(porudzbine);
	}
	public void dodajJednuPorudzbinu (Porudzbina porudzbina) {
		this.porudzbine.add(porudzbina);
	}
	public void dodajSvePorudzbine (Collection<Porudzbina> porudzbine) {
		this.porudzbine.addAll(porudzbine);
	}
	
	
	
}
