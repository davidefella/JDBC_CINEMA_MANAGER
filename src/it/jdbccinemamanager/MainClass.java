package it.jdbccinemamanager;

import java.sql.Date;

import it.jdbccinemamanager.model.Film;
import it.jdbccinemamanager.model.FilmDAO;
import it.jdbccinemamanager.model.Proiezioni;
import it.jdbccinemamanager.model.ProiezioniDAO;
import it.jdbccinemamanager.model.Sala;
import it.jdbccinemamanager.model.SalaDAO;

public class MainClass {

	public static void main(String[] args) {
		FilmDAO filmDAO = new FilmDAO();
		SalaDAO salaDAO = new SalaDAO();
		ProiezioniDAO proiezioniDAO = new ProiezioniDAO();
		
		filmDAO.insertFilm(readFilmFromScanner()); 
		salaDAO.insertSala(readSalaFromScanner());
		proiezioniDAO.insertProiezioni(readProiezioniFromScanner());

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

	public static Sala readSalaFromScanner() {
		Sala s = new Sala();

		s.setCitta("Pescara");
		s.setNome("Sala 3 from scanner");
		s.setNumero_posti(1000);

		return s;
	}

	public static Proiezioni readProiezioniFromScanner() {
		Proiezioni p = new Proiezioni();
		FilmDAO filmDAO = new FilmDAO();
		SalaDAO salaDAO = new SalaDAO();

		// 1650460632352L --> millisecondi dal 1 gennaio 1970 al 20 aprile 2022
		p.setDataproiezione(new Date(1650460632352L));
		p.setIncasso(78956.99);
		p.setFilm(filmDAO.getFilmByID(6));
		p.setSala(salaDAO.getSalaByID(1));

		return p;
	}
}