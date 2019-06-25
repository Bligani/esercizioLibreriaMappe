package prestitoLibri.collection.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import prestitoLibri.collection.classes.Libro;
import prestitoLibri.collection.classes.Metodi;
import prestitoLibri.collection.classes.Prestito;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//System.out.println("Libri");		
			// creazione catalogoLibri
			
//			Collection<Libro> catalogoLibri = new ArrayList<Libro>();
//			Path percorsoCatalogo = Paths.get("Catalogo.txt");
//			Collection<String> lineeLibri;
//						
//			try {
//				lineeLibri = Files.lines(percorsoCatalogo).collect(Collectors.toList());
//				
//				for(String l : lineeLibri) {
//					String[] parole= l.split(";");
//									
//					int numeroAutori = Integer.parseInt(parole[2]);
//					ArrayList<String> autori = new ArrayList<>(); //potrebbe dare problemi perché dentro al for
//				
//					for(int i = 0; i< numeroAutori; i++) {
//						autori.add(parole[3+i]);
//					}
//					
//					Libro libro = new Libro(parole[0], parole[1], numeroAutori, autori);
//					catalogoLibri.add(libro);
//					
//					//System.out.println(libro.getTitoloLibro()+ ", "+ libro.getNumeroAutori());
//				}
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
			HashMap<String,Libro> catalogoLibri = new HashMap<String,Libro>();
			Path percorsoCatalogo = Paths.get("Catalogo.txt");
			
			Collection<String> lineeLibri;			
			try {
				lineeLibri = Files.lines(percorsoCatalogo).collect(Collectors.toList());
				
				for(String l : lineeLibri) {
					String[] parole= l.split(";");				
					int numeroAutori = Integer.parseInt(parole[2]);
					ArrayList<String> autori = new ArrayList<>(); 
					for(int i = 0; i< numeroAutori; i++) {
						autori.add(parole[3+i]);
					}
					Libro libro = new Libro(parole[0], parole[1].toLowerCase(), numeroAutori, autori);
					catalogoLibri.put(parole[0],libro);
					
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
//		Collection<Prestito> elencoPrestiti = new ArrayList<Prestito>();
//			
//		Path percorsoPrestiti = Paths.get("Prestiti2.txt");
//		Collection<String> lineePrestiti;
//			
//			try {
//				lineePrestiti = Files.lines(percorsoPrestiti).collect(Collectors.toList());
//				
//				for(String l : lineePrestiti) {
//					String[] parole= l.split(";");
//					Date inizioPrestito;
//					
//					inizioPrestito = new SimpleDateFormat("dd/MM/yyyy").parse(parole[3]);
//					
//					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//					/*
//					formatter.setLenient(false);
//					usato per trovare un errore nee dati in input su Prestiti
//					*/
//					Date finePrestito = new Date();
//					
//					if(parole[4].contentEquals("0")) {
//						finePrestito = formatter.parse("01/01/1800");
//					}
//					else {
//						finePrestito = formatter.parse(parole[4]);
//					}
//					
//					Prestito prestito = new Prestito(parole[0], parole[1], parole[2], inizioPrestito, finePrestito);
//					elencoPrestiti.add(prestito);
//					
//					//System.out.println(prestito.getNomeUtente()+ ", "+ prestito.getCognomeUtente());
//					
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
		HashMap<Integer,Prestito> elencoPrestiti = new HashMap<Integer,Prestito>();
		Path percorsoPrestiti = Paths.get("Prestiti2.txt");
		Collection<String> lineePrestiti;
			
			try {
				lineePrestiti = Files.lines(percorsoPrestiti).collect(Collectors.toList());
				
				for(String l : lineePrestiti) {
					String[] parole= l.split(";");
					Date inizioPrestito;
					inizioPrestito = new SimpleDateFormat("dd/MM/yyyy").parse(parole[4]);
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					/*
					formatter.setLenient(false);
					usato per trovare un errore nee dati in input su Prestiti
					*/
		Date finePrestito = new Date();
		if(parole[5].contentEquals("0")) {
		finePrestito = formatter.parse("01/01/1800");
		}
		else {
			finePrestito = formatter.parse(parole[5]);
		}
					Prestito prestito = new Prestito(Integer.parseInt(parole[0]), parole[1], parole[2], parole[3], inizioPrestito, finePrestito);
					elencoPrestiti.put(Integer.parseInt(parole[0]),prestito);
				}
			} catch (IOException|ParseException e) {
				e.printStackTrace();
			}
			
			
			
		HashMap<String, Prestito> prestitiNonResi = new HashMap<String, Prestito>();
		Metodi metodo = new Metodi();
		for(Integer key : elencoPrestiti.keySet()) {
			Prestito prestito = elencoPrestiti.get(key);
				if(metodo.inPrestito(prestito.getFinePrestito())) {
				prestitiNonResi.put(prestito.getIdLibro(),prestito);
				}
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("Scegliere un'opzione");

		System.out.println("1. verificare se un dato libro è in prestito\r\n" + 
				"2. ritrovare i in libri attualmente in prestito ad un utente\r\n" + 
				"3. ritrovare i libri che un utente ha preso in prestito nel tempo\r\n" + 
				"4. identificare il cliente che ha in prestito un libro\r\n" + 
				"5. calcolare il periodo più lungo in cui un libro è rimasto in prestito ad un cliente");
		 String inputString = scanner.nextLine();
		 if(inputString.contentEquals("1")) {
			 System.out.println("Inserire titolo o parte di esso: ");
			 String titoloLibro = scanner.nextLine();
			 scanner.close();
			 metodo.daRestituire(titoloLibro, catalogoLibri , prestitiNonResi);
		 }
		 else if (inputString.contentEquals("2")) {
			 System.out.println("Inserire nome utente: ");
			 String nome = scanner.nextLine();
			 System.out.println("Inserire cognome utente: ");
			 String cognome = scanner.nextLine();
			 scanner.close();
			 metodo.libriUtente(nome, cognome,catalogoLibri,prestitiNonResi);

		 }
		 else if (inputString.contentEquals("3")) {
			 System.out.println("Inserire nome utente: ");
			 String nome = scanner.nextLine();
			 System.out.println("Inserire cognome utente: ");
			 String cognome = scanner.nextLine();
			 scanner.close();
			 metodo.storicoUtente(nome, cognome,catalogoLibri,elencoPrestiti);

		 }
		 else if (inputString.contentEquals("4")) {
			 System.out.println("Inserire titolo o parte di esso: ");
			 String titoloLibro = scanner.nextLine();
			 scanner.close();
			 metodo.utenteInPrestito(titoloLibro,catalogoLibri, elencoPrestiti);
		 }
		 else if (inputString.contentEquals("5")) {
			 System.out.println("Inserire id libro: ");
			 String idLibro = scanner.nextLine();
			 scanner.close();
			 metodo.tempoPrestitoMassimo(idLibro, elencoPrestiti);
		 }
		 else {
			 System.out.println("Scelta non valida.");

		 }


//		metodo.daRestituire("a", catalogoLibri , prestitiNonResi);
//		
//		System.out.println(" ");
//		metodo.libriUtente("Giulio", "Mattei",catalogoLibri,prestitiNonResi);
//		
//		System.out.println(" ");
//		metodo.storicoUtente("Giulio", "Mattei", catalogoLibri, elencoPrestiti);
//		System.out.println(" ");
//		metodo.storicoUtente("Mario", "Bianchi", catalogoLibri, elencoPrestiti);
//		
//		System.out.println(" ");
//		metodo.utenteInPrestito("Gli anni", catalogoLibri,elencoPrestiti);
//		System.out.println(" ");
//		metodo.utenteInPrestito("Moby Dick",catalogoLibri, elencoPrestiti);
//		
//		System.out.println(" ");
//		metodo.tempoPrestitoMassimo("45", elencoPrestiti);
//		System.out.println(" ");
//		metodo.tempoPrestitoMassimo("4", elencoPrestiti);
//		System.out.println(" ");
//		metodo.tempoPrestitoMassimo("100", elencoPrestiti);
//		
	}

}
