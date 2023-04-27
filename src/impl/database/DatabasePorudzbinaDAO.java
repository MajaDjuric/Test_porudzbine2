package impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import dao.PorudzbinaDAO;
import model.Porudzbina;

public class DatabasePorudzbinaDAO implements PorudzbinaDAO{

	private final Connection conn;

	public DatabasePorudzbinaDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void add(Porudzbina porudzbina) throws Exception {
		String sql = "INSERT INTO porudzbine (datumIVreme, ulica, broj, proizvodId) VALUES (?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setTimestamp(++param, Timestamp.valueOf(porudzbina.getDatumIVreme()));
			stmt.setString(++param, porudzbina.getUlica());
			stmt.setInt(++param, porudzbina.getBroj());
			stmt.setLong(++param, porudzbina.getProizvod().getId());
			
			stmt.executeUpdate();
		}
		
	}
	
}
