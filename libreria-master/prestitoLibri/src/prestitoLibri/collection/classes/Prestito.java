package prestitoLibri.collection.classes;

import java.util.Date;

public class Prestito {

	private Integer ordinalePrestito;
	private String nomeUtente;
	private String cognomeUtente;
	private String idLibro;
	private Date inizioPrestito;
	private Date finePrestito;
	
	public Prestito(Integer ordinalePrestito, String nomeUtente, String cognomeUtente, String idLibro, Date inizioPrestito, Date finePrestito){
		super();
		this.ordinalePrestito=ordinalePrestito;
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
		this.idLibro=idLibro;
		this.inizioPrestito=inizioPrestito;
		this.finePrestito=finePrestito;
	}
	
	public Integer getOrdinalePrestito() {
		return ordinalePrestito;
	}
	
	public String getNomeUtente() {
		return nomeUtente;
	}
	
	public String getCognomeUtente() {
		return cognomeUtente;
	}
	
	public String getIdLibro() {
		return idLibro;
	}
	
	public Date getInizioPrestito() {
		return inizioPrestito;
	}
	
	public Date getFinePrestito() {
		return finePrestito;
	}
}
