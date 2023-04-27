package ui;

import java.util.Collection;

import dao.ProizvodDAO;
import model.Proizvod;
import util.Konzola;

public class ProizvodiUI {

	private static ProizvodDAO proizvodDAO;

	public static void setProizvodDAO(ProizvodDAO proizvodDAO) {
		ProizvodiUI.proizvodDAO = proizvodDAO;
	}
	
	public static Proizvod pretragaProizvoda () throws Exception {
		prikazSvihProizvoda();
		String sifra = Konzola.ocitajString("Unesite sifru proizvoda");
		Proizvod proizvod = proizvodDAO.get(sifra);
		if (proizvod == null) {
			System.out.println("Proizvod nije pronadjen");
		}
		return proizvod;
	}
	
	public static void prikazSvihProizvoda () {
		try {
			Collection<Proizvod> proizvodi = proizvodDAO.getAll();
			for (Proizvod proizvod : proizvodi) {
				System.out.println(proizvod);
			}
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
	}
	
}
