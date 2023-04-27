package dao;

import java.util.Collection;

import model.Proizvod;

public interface ProizvodDAO {

	Proizvod get(String sifra) throws Exception;
	Collection<Proizvod> getAll () throws Exception;
}
