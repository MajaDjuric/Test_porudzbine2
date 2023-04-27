package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dao.PorudzbinaDAO;
import dao.ProizvodDAO;
import model.Porudzbina;
import model.Proizvod;
import model.StavkaIzvestaja;
import util.Konzola;

public class IzvestajUI {

	private static ProizvodDAO proizvodDAO;
	private static PorudzbinaDAO porudzbinaDAO;
	
	public static void setPorudzbinaDAO(PorudzbinaDAO porudzbinaDAO) {
		IzvestajUI.porudzbinaDAO = porudzbinaDAO;
	}
	public static void setProizvodDAO(ProizvodDAO proizvodDAO) {
		IzvestajUI.proizvodDAO = proizvodDAO;
	}
	
	public static void izvestaj() {
		
		LocalDateTime pocetni = Konzola.ocitajDateTime("Unesite pocetni datum");
		LocalDateTime krajnji = Konzola.ocitajDateTime("Unesite krajnji datum");
		List<StavkaIzvestaja> izvestaji = new ArrayList<>();
		
		try {
			Collection<Proizvod> proizvodi = proizvodDAO.getAll();
			Set<String> nazivi = new LinkedHashSet<>();
			
			for (Proizvod proizvod : proizvodi) {
				nazivi.add(proizvod.getNaziv());
			}
			
			
			for (String naziv : nazivi) {
				Collection<Porudzbina> porudzbineUOpsegu = new ArrayList<>();
				double ukupanPrihod = 0;
				LocalDateTime datumPoslednjeProdaje = null;
				String sifra = "";
				
				for (Proizvod proizvod : proizvodi) {
					
					for (Porudzbina porudzbina : proizvod.getPorudzbine()) {
						if (porudzbina.getProizvod().getNaziv().equals(naziv) &&
							porudzbina.getDatumIVreme().compareTo(pocetni) >= 0 &&
							porudzbina.getDatumIVreme().compareTo(krajnji) <= 0) {
							porudzbineUOpsegu.add(porudzbina);
							
							double cenaDostave = 0;
						if (!proizvod.isBesplatnaDostava()) {
							cenaDostave = 1000.00;
						}
						
						ukupanPrihod += proizvod.getCena() + cenaDostave;
						
						sifra = porudzbina.getProizvod().getSifra();
						
						for (Porudzbina poruzbinaUOpsegu : porudzbineUOpsegu) {
							if (porudzbina.getDatumIVreme().compareTo(poruzbinaUOpsegu.getDatumIVreme()) >= 0) {
								datumPoslednjeProdaje = porudzbina.getDatumIVreme();
							}
						}
						}
						
						 
					}
				}
				StavkaIzvestaja stavka = new StavkaIzvestaja(sifra, naziv, ukupanPrihod, datumPoslednjeProdaje);
				izvestaji.add(stavka);
				
			//	System.out.println("Sifra:  " + sifra + "   Naziv: " + naziv + "   Ukupan prihod: " + ukupanPrihod + "   Datum poslednje prodaje: " + Konzola.formatiraj(datumPoslednjeProdaje));
			}
			
			izvestaji.sort(StavkaIzvestaja :: compareUkupanPrihod);
			
			for (StavkaIzvestaja stavka : izvestaji) {
				System.out.println(stavka);
			}
			
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}

		
		
	}
	
	
	
	
	
}
