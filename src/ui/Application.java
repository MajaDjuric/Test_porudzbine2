package ui;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.PorudzbinaDAO;
import dao.ProizvodDAO;
import impl.database.DatabasePorudzbinaDAO;
import impl.database.DatabaseProizvodDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/porudzbine?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade", 
				"root", 
				"root");

		ProizvodDAO proizvodDAO = new DatabaseProizvodDAO(conn);
		ProizvodiUI.setProizvodDAO(proizvodDAO);
		PorudzbinaDAO porudzbinaDAO = new DatabasePorudzbinaDAO(conn);
		PorudzbinaUI.setPorudbinaDAO(porudzbinaDAO);
		PorudzbinaUI.setProizvodDAO(proizvodDAO);
		IzvestajUI.setPorudzbinaDAO(porudzbinaDAO);
		IzvestajUI.setProizvodDAO(proizvodDAO);
		Izvestaj2.setPorudzbinadao(porudzbinaDAO);
		Izvestaj2.setProizvoddao(proizvodDAO);
		
	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");
			
			System.exit(1);
		}
	}
	public static void main(String[] args) {
		Meni.pokreni("Porudzbine", new StavkaMenija[] {
				new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih proizvoda") {

					@Override
					public void izvrsi() {  ProizvodiUI.prikazSvihProizvoda();}
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz svih porudzbina") {

					@Override
					public void izvrsi() { PorudzbinaUI.prikazSvihPorudzbina(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Dodavanje porudzbine") {

					@Override
					public void izvrsi() { PorudzbinaUI.dodajPorudzbinu(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Izvestavanje") {

					@Override
					public void izvrsi() { Izvestaj2.izvestaj();}
					
				}
			});
	}
}
