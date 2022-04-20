package it.jdbccinemamanager;

import it.jdbccinemamanager.model.Film;
import it.jdbccinemamanager.model.FilmDao;

public class MainClass {

	public static void main(String[] args) {
		FilmDao filmDAO = new FilmDao();
		
	}

	public static Film readFilmFromScanner() {
		Film f = new Film();

		f.setAnnoproduzione(2022);
		f.setAttori("Attore 7");
		f.setGenere("Genere 4");
		f.setNazionalita("Nazionalita 2");
		f.setRegista("Regista 3");
		f.setTitolo("Titolo 1");

		return f;
	}
}