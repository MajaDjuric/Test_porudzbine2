package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.PorudzbinaDAO;
import dao.ProizvodDAO;
import model.Porudzbina;
import model.Proizvod;
import util.Konzola;

public class PorudzbinaUI {

	private static PorudzbinaDAO porudbinaDAO;
	private static ProizvodDAO proizvodDAO;
	
	public static void setPorudbinaDAO(PorudzbinaDAO porudbinaDAO) {
		PorudzbinaUI.porudbinaDAO = porudbinaDAO;
	}
	public static void setProizvodDAO(ProizvodDAO proizvodDAO) {
		PorudzbinaUI.proizvodDAO = proizvodDAO;
	}
	
	
	public static void prikazSvihPorudzbina() {
		try {
			Collection<Proizvod> proizvodi = proizvodDAO.getAll();
			List<Porudzbina> porudzbine = new ArrayList<>();
			for (Proizvod proizvod : proizvodi) {
				for (Porudzbina porudzbina: proizvod.getPorudzbine()) {
					porudzbine.add(porudzbina);
				}
			}
			porudzbine.sort(Porudzbina :: compareIdPorudzbine);
			for (Porudzbina porudzbina : porudzbine) {
				System.out.println(porudzbina);
			}
			
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
		
	}
	
	
	public static void dodajPorudzbinu () {
		try {
			Proizvod proizvod = ProizvodiUI.pretragaProizvoda();
			if (proizvod == null) {
				return;
			}
			String ulica = Konzola.ocitajString("Unesite ulicu");
			int broj = Konzola.ocitajInt("Unesite broj");
			LocalDateTime datumIVreme = LocalDateTime.now();
			
			Porudzbina porudzbina = new Porudzbina(datumIVreme, ulica, broj, proizvod);
			porudbinaDAO.add(porudzbina);
			System.out.println();
			System.out.println("Uspesno dodavanje");
			
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
