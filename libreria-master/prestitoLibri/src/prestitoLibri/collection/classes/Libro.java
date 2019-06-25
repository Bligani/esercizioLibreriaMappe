package prestitoLibri.collection.classes;

import java.util.ArrayList;


public class Libro {
	
	private String idLibro;
	private String titoloLibro;
	private int numeroAutori;
	private ArrayList<String> autori;
	
	public Libro(){
		super();
	}
	
	public Libro(String idLibro, String titoloLibro, int numeroAutori, ArrayList<String> autori){
		super();
		this.idLibro = idLibro;
		this.titoloLibro = titoloLibro;
		this.numeroAutori=numeroAutori;
		this.autori=autori;
	}
	
	
		public String getIdLibro() {
			return idLibro;
		}
		
		public String getTitoloLibro() {
			return titoloLibro;
		}
		
		public int  getNumeroAutori() {
			return numeroAutori;
		}
		
		public ArrayList<String> getAutori() {
			return autori;
		}
		
	

	
}
