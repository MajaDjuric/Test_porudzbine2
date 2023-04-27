package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dao.PorudzbinaDAO;
import dao.ProizvodDAO;
import model.Porudzbina;
import model.Proizvod;
import model.StavkaIzvestaja;
import util.Konzola;

public class Izvestaj2 {

	private static ProizvodDAO proizvoddao;
	private static PorudzbinaDAO porudzbinadao;
	
	

	public static void setProizvoddao(ProizvodDAO proizvoddao) {
		Izvestaj2.proizvoddao = proizvoddao;
	}

	public static void setPorudzbinadao(PorudzbinaDAO porudzbinadao) {
		Izvestaj2.porudzbinadao = porudzbinadao;
	}


	public static void izvestaj () {
		
		try {
			Collection<Proizvod> proizvodi = proizvoddao.getAll();
			Set<String> nazivi = new LinkedHashSet<String>();
			List<StavkaIzvestaja> izvestaji = new ArrayList<StavkaIzvestaja>();
			
			for (Proizvod proizvod : proizvodi) {
				nazivi.add(proizvod.getNaziv());
			}
			
			for (String naziv : nazivi) {
				String sifra = "";
				double ukupanPrihod = 0;
				LocalDateTime datumPoslednjeProdaje = null;
				
				for (Proizvod proizvod : proizvodi) {
					Collection<Porudzbina> porudzbineUOpsegu = new HashSet<Porudzbina>();
					
					for (Porudzbina porudzbina: proizvod.getPorudzbine()) {
						if (porudzbina.getProizvod().getNaziv().equals(naziv)) {
							
							sifra = proizvod.getSifra();
							porudzbineUOpsegu.add(porudzbina);
							ukupanPrihod += porudzbina.getProizvod().getCena();
							
							for (Porudzbina porudzbinaopseg : porudzbineUOpsegu) {
								if (porudzbina.getDatumIVreme().compareTo(porudzbinaopseg.getDatumIVreme()) > 0){
									datumPoslednjeProdaje = porudzbina.getDatumIVreme();{
								}
									
								}
							}
						}
						
					}
					
				}
				
				StavkaIzvestaja stavka = new StavkaIzvestaja(sifra, naziv, ukupanPrihod, datumPoslednjeProdaje);
				izvestaji.add(stavka);
				izvestaji.sort(StavkaIzvestaja :: compareUkupanPrihod);
			
			}
			
			String naslov = String.format("%-15s %-15s %-18s %-30s", "Sifra", "Naziv", "Ukupan prihod", "Datum poslednje prodaje\n"
					+ "=============== =============== ================= ==============================");
	
			System.out.println(naslov);

			for (StavkaIzvestaja stavka : izvestaji) {
				String rezultat = String.format("%-15s %-15s %-18s %-30s", stavka.getSifra(), stavka.getNaziv(), stavka.getUkupanPrihod(), Konzola.formatiraj(stavka.getDatumPoslednjeProdaje()));
				System.out.println(rezultat);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
