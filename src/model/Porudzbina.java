package model;

import java.time.LocalDateTime;
import java.util.Objects;

import util.Konzola;

public class Porudzbina {

	private final long id;
	private LocalDateTime datumIVreme;
	private String ulica;
	private int broj;
	
	Proizvod proizvod;

	//konstr
	public Porudzbina(long id, LocalDateTime datumIVreme, String ulica, int broj, Proizvod proizvod) {
		super();
		this.id = id;
		this.datumIVreme = datumIVreme;
		this.ulica = ulica;
		this.broj = broj;
		this.proizvod = proizvod;
	}
	public Porudzbina(LocalDateTime datumIVreme, String ulica, int broj, Proizvod proizvod) {
		super();
		this.id = 0;
		this.datumIVreme = datumIVreme;
		this.ulica = ulica;
		this.broj = broj;
		this.proizvod = proizvod;
	}
	
	//object metode
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
		Porudzbina other = (Porudzbina) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Porudzbina [id=" + id + ", datumIVreme=" + Konzola.formatiraj(datumIVreme) + ", ulica=" + ulica + ", broj=" + broj
				+ ", proizvod=" + proizvod.getNaziv() + "]";
	}
	
	//get set
	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}
	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public Proizvod getProizvod() {
		return proizvod;
	}
	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
	public long getId() {
		return id;
	}
	
	public static int compareIdPorudzbine (Porudzbina porudzbina1, Porudzbina porudzbina2) {
		return Long.compare(porudzbina1.id, porudzbina2.id);
	}
	
	
	
	
	
	
	
	
	
	
	
}
