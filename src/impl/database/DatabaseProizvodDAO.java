package impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dao.ProizvodDAO;
import model.Porudzbina;
import model.Proizvod;

public class DatabaseProizvodDAO implements ProizvodDAO {

	private final Connection conn;
	
	public DatabaseProizvodDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	

	@Override
	public Collection<Proizvod> getAll() throws Exception {
		Map<Long, Proizvod> proizvodi = new HashMap<>();
		String sql = "SELECT pr.id, pr.sifra, pr.naziv, pr.cena, pr.besplatnaDostava, "
				+ "po.id, po.datumIVreme, po.ulica, po.broj \r\n"
				+ "FROM proizvodi pr LEFT JOIN porudzbine po ON pr.id = po.proizvodId";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long prid = rset.getLong(++kolona);
					String prsifra = rset.getString(++kolona);
					String prnaziv = rset.getString(++kolona);
					double prcena = rset.getDouble(++kolona);
					boolean prbesplatno = rset.getBoolean(++kolona);
					
					Proizvod proizvod = proizvodi.get(prid);
					if (proizvod == null) {
						proizvod = new Proizvod(prid, prsifra, prnaziv, prcena, prbesplatno);
						proizvodi.put(proizvod.getId(), proizvod);
					}
					
					long poid = rset.getLong(++kolona);
					if (poid != 0) {
						LocalDateTime datumIVreme= rset.getTimestamp(++kolona).toLocalDateTime();
						String ulica = rset.getString(++kolona);
						int broj = rset.getInt(++kolona);
						
						Porudzbina porudzbina = new Porudzbina(poid, datumIVreme, ulica, broj, proizvod);
						proizvod.dodajJednuPorudzbinu(porudzbina);
					}
					
				}
				
			}
		}
		return proizvodi.values();
	}



	@Override
	public Proizvod get(String sifra) throws Exception {
		Proizvod proizvod = null;
		String sql = "SELECT pr.id, pr.naziv, pr.cena, pr.besplatnaDostava, "
				+ "po.id, po.datumIVreme, po.ulica, po.broj \r\n"
				+ "FROM proizvodi pr LEFT JOIN porudzbine po ON pr.id = po.proizvodId WHERE pr.sifra = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, sifra);
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long prid = rset.getLong(++kolona);
					String prnaziv = rset.getString(++kolona);
					double prcena = rset.getDouble(++kolona);
					boolean prbesplatno = rset.getBoolean(++kolona);
					
					if (proizvod == null) {
						proizvod = new Proizvod(prid, sifra, prnaziv, prcena, prbesplatno);
					}
					
					long poid = rset.getLong(++kolona);
					if (poid != 0) {
						LocalDateTime datumIVreme= rset.getTimestamp(++kolona).toLocalDateTime();
						String ulica = rset.getString(++kolona);
						int broj = rset.getInt(++kolona);
						
						Porudzbina porudzbina = new Porudzbina(poid, datumIVreme, ulica, broj, proizvod);
						proizvod.dodajJednuPorudzbinu(porudzbina);
					}
					
				}
				
			}
		}
		return proizvod;
	}

	
}
