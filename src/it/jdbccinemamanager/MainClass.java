package it.jdbccinemamanager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.jdbccinemamanager.model.Film;
import it.jdbccinemamanager.model.FilmDAO;
import it.jdbccinemamanager.model.Proiezioni;
import it.jdbccinemamanager.model.ProiezioniDAO;
import it.jdbccinemamanager.model.Sala;
import it.jdbccinemamanager.model.SalaDAO;

public class MainClass {

	public static void main(String[] args) {
		FilmDAO filmDAO = new FilmDAO();
		ProiezioniDAO proiezioniDAO = new ProiezioniDAO();
		SalaDAO salaDAO = new SalaDAO();

		Scanner keyboard = new Scanner(System.in);
		boolean result;

		printMenu();

		int userCommand = keyboard.nextInt();

		switch (userCommand) {
		case 1:
			result = filmDAO.insertFilmAll(readFilmsFromData());
			System.out.println("Film inserito: " + result);
			break;
		case 2:
			Proiezioni p = readProiezioniFromScanner();
			result = proiezioniDAO.insertProiezioni(p);
			System.out.println("Proiezione inserita: " + result);
			break;
		case 3:
			result = salaDAO.insertSalaAll(readSaleFromData());
			System.out.println("Sala inserito: " + result);
			break;
		case 4:
			System.out.println("Inserisci nome citta per la ricerca");
			keyboard.nextLine();
			List<Sala> sale = salaDAO.getSalaByNomeCitta(keyboard.nextLine());
			for (Sala s_by_citta : sale)
				System.out.println(s_by_citta.toString());
			break;
		case 5:
			System.out.println("Inserisci codice film per ricercare proiezioni");
			keyboard.nextLine();
			List<Proiezioni> proiezioni = proiezioniDAO.getProiezioniByCodFilm(keyboard.nextInt());
			for (Proiezioni p_by_film : proiezioni)
				System.out.println(p_by_film.toString());

			break;
		case 6:
			System.out.println("Inserisci codice proiezione da eliminare");
			keyboard.nextLine();
			proiezioniDAO.deleteProiezioni(keyboard.nextInt());
			break;
		case 7:
			break;
		default:
			printMenu();
			break;
		}

		keyboard.close();
	}

	public static List<Film> readFilmsFromData() {
		List<Film> films = new ArrayList<Film>();

		films.add(new Film(2022, "Titolo 1", "Nazionalita 2", "Regista 1", "Attore 1,Attore 2", "Genere 1"));
		films.add(new Film(2021, "Titolo 2", "Nazionalita 2", "Regista 3", "Attore 3,Attore 4", "Genere 1"));
		films.add(new Film(2015, "Titolo 3", "Nazionalita 3", "Regista 1", "Attore 2,Attore 1", "Genere 1"));
		films.add(new Film(2015, "Titolo 4", "Nazionalita 3", "Regista 3", "Attore 1,Attore 2", "Genere 3"));
		films.add(new Film(2015, "Titolo 5", "Nazionalita 4", "Regista 5", "Attore 6,Attore 2", "Genere 3"));
		films.add(new Film(2010, "Titolo 6", "Nazionalita 5", "Regista 1", "Attore 9,Attore 4", "Genere 5"));

		return films;
	}

	public static List<Sala> readSaleFromData() {
		List<Sala> sale = new ArrayList<Sala>();

		sale.add(new Sala(1000, "Sala 2", "Pescara"));
		sale.add(new Sala(1000, "Sala 4", "Pescara"));
		sale.add(new Sala(1000, "Sala 6", "Pescara"));
		sale.add(new Sala(1000, "Sala 8", "Roma"));
		sale.add(new Sala(1000, "Sala 9", "Milano"));

		return sale;
	}

	/*
	 * Proiezioni contieni riferimenti esterni verso Sala e Film, non so quali ID
	 * però sono presenti nel DB. Devo leggere il SET di chiavi da Sala e Film cosi
	 * posso usare degli ID esisenti nel DB. (Ricorda sono presenti autonumeranti
	 * nei codici)
	 */
	public static Proiezioni readProiezioniFromScanner() {
		Proiezioni p = new Proiezioni();
		FilmDAO filmDAO = new FilmDAO();
		SalaDAO salaDAO = new SalaDAO();

		p.setDataproiezione(new Date(1650460632352L)); // millisecondi dal 1 gennaio 1970 al 20 aprile 2022
		p.setIncasso(78956.99);
		p.setFilm(filmDAO.getFilmByID(8));
		p.setSala(salaDAO.getSalaByID(4));

		return p;
	}

	public static void printMenu() {
		System.out.println("------------------------------");
		System.out.println("1 - Inserisci Film");
		System.out.println("2 - Inserisci Proiezione");
		System.out.println("3 - Inserisci Sala");
		System.out.println("4 - Cerca sala per città");
		System.out.println("5 - Cerca proiezione per codice film");
		System.out.println("6 - Elimina proiezione per codice proiezione");
		System.out.println("7 - Backup DataBase");
		System.out.println("------------------------------");
	}
}