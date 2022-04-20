package it.jdbccinemamanager;

import it.jdbccinemamanager.model.Film;
import it.jdbccinemamanager.model.FilmDao;

public class MainClass {

	public static void main(String[] args) {
		FilmDao filmDAO = new FilmDao();

		Film film = readFilmFromScanner();

		filmDAO.insertFilm(film);

		for (Film f : filmDAO.getAllFilm())
			System.out.println(f.toString());

		System.out.println("* * * * UPDATE * * * * ");

		film.setAttori("ATTORI MODIFICATI PER UPDATE");
		filmDAO.updateFilm(film, 4);

		System.out.println("* * * * DELETE * * * * ");
		filmDAO.deleteFilm(4);

		System.out.println("* * * * GET ALL (AGAIN) * * * * ");

		for (Film f : filmDAO.getAllFilm())
			System.out.println(f.toString());

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