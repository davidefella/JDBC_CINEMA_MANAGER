package it.jdbccinemamanager;

import java.sql.Date;
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
			Film f = readFilmFromScanner();
			result = filmDAO.insertFilm(f);
			System.out.println("Film inserito: " + result);
			break;
		case 2:
			Proiezioni p = readProiezioniFromScanner();
			result = proiezioniDAO.insertProiezioni(p);
			System.out.println("Proiezione inserita: " + result);
			break;
		case 3:
			Sala s = readSalaFromScanner();
			result = salaDAO.insertSala(s);
			System.out.println("Sala inserito: " + result);
			break;
		case 4:
			System.out.println("Inserisci nome citta per la ricerca");
			keyboard.nextLine(); 
			List<Sala> sale = salaDAO.getSalaByNomeCitta(keyboard.nextLine()); 
			for(Sala s_by_citta : sale)
				System.out.println(s_by_citta.toString()); 
			break;
		case 5:
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